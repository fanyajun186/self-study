package com.example.demo.util.encrypt.yc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 序列化/反序列化Ticket。
 * <p>
 * Current (v1) ticket format
 * ==========================
 * <p>
 * Serialized ticket format version number: 1 byte
 * Ticket.Version: 1 byte
 * Ticket.IssueDateUtc: 8 bytes
 * {spacer}: 1 byte
 * Ticket.ExpirationUtc: 8 bytes
 * Ticket.IsPersistent: 1 byte
 * Ticket.Name: 1+ bytes (1+ length prefix, 0+ payload)
 * Ticket.UserData: 1+ bytes (1+ length prefix, 0+ payload)
 * Ticket.CookiePath: 1+ bytes (1+ length prefix, 0+ payload)
 * {footer}: 1 byte
 */
class TicketSerializer {
    private static final byte CURRENT_TICKET_SERIALIZED_VERSION = 0x01;

    private static final long TICKS_AT_EPOCH = 621355968000000000L;
    private static final long TICKS_PER_MILLISECOND = 10000;

    private TicketSerializer() {
    }

    /**
     * 反序列化(解密)，对于不合法的、不符合指定要求的数据将直接返回null。注意，本方法不校验过期时间
     */
    static Ticket deserialize(byte[] serializedTicket) {
        try {
            ByteBuffer ticketReader = ByteBuffer.wrap(serializedTicket);

            // Step 1: Read the serialized format version number from the stream.
            // Currently the only supported format is 0x01.
            // LENGTH: 1 byte
            byte serializedFormatVersion = ticketReader.get();
            if (serializedFormatVersion != CURRENT_TICKET_SERIALIZED_VERSION) {
                // unexpected value
                return null;
            }

            // Step 2: Read the ticket version number from the stream.
            // LENGTH: 1 byte
            int ticketVersion = ticketReader.get();

            // Step 3: Read the ticket issue date from the stream.
            // LENGTH: 8 bytes
            long ticketIssueDateUtcTicks = BufferUtil.getLongLittleEndian(ticketReader);

            LocalDateTime ticketIssueDateUtc = toUtcDate(ticketIssueDateUtcTicks);

            // Step 4: Read the spacer from the stream.
            // LENGTH: 1 byte
            byte spacer = ticketReader.get();
            if ((spacer & 0xFF) != 0xfe) {
                // unexpected value
                return null;
            }

            // Step 5: Read the ticket expiration date from the stream.
            // LENGTH: 8 bytes
            long ticketExpirationDateUtcTicks = BufferUtil.getLongLittleEndian(ticketReader);
            LocalDateTime ticketExpirationDateUtc = toUtcDate(ticketExpirationDateUtcTicks);

            // Step 6: Read the ticket persistence field from the stream.
            // LENGTH: 1 byte
            byte ticketPersistenceFieldValue = ticketReader.get();
            boolean ticketIsPersistent;
            switch (ticketPersistenceFieldValue) {
                case 0:
                    ticketIsPersistent = false;
                    break;
                case 1:
                    ticketIsPersistent = true;
                    break;
                default:
                    // unexpected value
                    return null;
            }

            // Step 7: Read the ticket username from the stream.
            // LENGTH: 1+ bytes (7-bit encoded integer char count + UTF-16LE payload)
            String ticketName = BufferUtil.readBinaryString(ticketReader);

            // Step 8: Read the ticket custom data from the stream.
            // LENGTH: 1+ bytes (7-bit encoded integer char count + UTF-16LE payload)
            String ticketUserData = BufferUtil.readBinaryString(ticketReader);

            // Step 9: Read the ticket cookie path from the stream.
            // LENGTH: 1+ bytes (7-bit encoded integer char count + UTF-16LE payload)
            String ticketCookiePath = BufferUtil.readBinaryString(ticketReader);

            // Step 10: Read the footer from the stream.
            // LENGTH: 1 byte
            byte footer = ticketReader.get();
            if ((footer & 0xFF) != 0xff) {
                // unexpected value
                return null;
            }

            // Step 11: Verify that we have consumed the entire payload.
            // We don't expect there to be any more information after the footer.
            // The caller is responsible for telling us when the actual payload
            // is finished, as he may have handed us a byte array that contains
            // the payload plus signature as an optimization, and we don't want
            // to misinterpet the signature as a continuation of the payload.
            if (ticketReader.remaining() > 0) {
                return null;
            }

            // Success.
            return Ticket.fromUtc(
                    ticketVersion,
                    ticketName,
                    ticketIssueDateUtc,
                    ticketExpirationDateUtc,
                    ticketIsPersistent,
                    ticketUserData,
                    ticketCookiePath);

        } catch (Exception ex) {
            // If anything goes wrong while parsing the token, just treat the token as invalid.
            return null;
        }
    }

    /**
     * 将票据转换为序列化的Blob。生成的blob未加密或签名。
     */
    static byte[] serialize(Ticket ticket) throws IOException {
        ByteArrayOutputStream ticketWriter = new ByteArrayOutputStream(120);

        // Step 1: Write the ticket serialized format version number (currently 0x01) to the stream.
        // LENGTH: 1 byte
        ticketWriter.write(CURRENT_TICKET_SERIALIZED_VERSION);

        // Step 2: Write the ticket version number to the stream.
        // This is the developer-specified Ticket.Version property,
        // which is just ticket metadata. Technically it should be stored as a 32-bit
        // integer instead of just a byte, but we have historically been storing it
        // as just a single byte forever and nobody has complained.
        // LENGTH: 1 byte
        ticketWriter.write((byte) ticket.getVersion());

        // Step 3: Write the ticket issue date to the stream.
        // We store this value as UTC ticks. We can't use DateTime.ToBinary() since it
        // isn't compatible with .NET v1.1.
        // LENGTH: 8 bytes (64-bit little-endian in payload)
        long issueTicks = toCsTicks(ticket.getIssueDateUtc());
        byte[] issueBuf = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(issueTicks).array();
        ticketWriter.write(issueBuf);

        // Step 4: Write a one-byte spacer (0xfe) to the stream.
        // One of the old ticket formats (Framework40) expects the unencrypted payload
        // to contain 0x000000 (3 null bytes) beginning at position 9 in the stream.
        // Since we're currently at offset 10 in the serialized stream, we can take
        // this opportunity to purposely inject a non-null byte at this offset, which
        // intentionally breaks compatibility with Framework40 mode.
        // LENGTH: 1 byte
        if (ticketWriter.size() != 10) {
            throw new IllegalArgumentException("Critical that we be at position 10 in the stream at this point.");
        }
        ticketWriter.write((byte) 0xfe);

        // Step 5: Write the ticket expiration date to the stream.
        // We store this value as UTC ticks.
        // LENGTH: 8 bytes (64-bit little endian in payload)
        long expirationTicks = toCsTicks(ticket.getExpirationUtc());
        byte[] expirationBuf = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(expirationTicks).array();
        ticketWriter.write(expirationBuf);

        // Step 6: Write the ticket persistence field to the stream.
        // LENGTH: 1 byte
        ticketWriter.write(ticket.isPersistent() ? (byte) 1 : (byte) 0);

        // Step 7: Write the ticket username to the stream.
        // LENGTH: 1+ bytes (7-bit encoded integer char count + UTF-16LE payload)
        BufferUtil.writeBinaryString(ticketWriter, ticket.getName());

        // Step 8: Write the ticket custom data to the stream.
        // LENGTH: 1+ bytes (7-bit encoded integer char count + UTF-16LE payload)
        BufferUtil.writeBinaryString(ticketWriter, ticket.getUserData());

        // Step 9: Write the ticket cookie path to the stream.
        // LENGTH: 1+ bytes (7-bit encoded integer char count + UTF-16LE payload)
        BufferUtil.writeBinaryString(ticketWriter, ticket.getCookiePath());

        // Step 10: Write a one-byte footer (0xff) to the stream.
        // One of the old Ticket formats (Framework20) requires
        // that the payload end in 0x0000 (U+0000). By making the very last byte
        // of this format non-null, we can guarantee a compatiblity break between
        // this format and Framework20.
        // LENGTH: 1 byte
        ticketWriter.write((byte) 0xff);

        // Finished.
        return ticketWriter.toByteArray();
    }

    /**
     * 将LocalDateTime转换成ticks，仅精确到毫秒
     * <p>
     * 参考 https://stackoverflow.com/a/44028583/1965211
     * <p>
     * c#中DateTime.Ticks：表示0001年1月1日午夜 12:00:00 以来所经历的100纳秒数，即Ticks的属性为100纳秒（1Ticks = 0.0001毫秒）。
     * Unix时间戳：是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数，不考虑闰秒。
     * 1秒 = 1000毫秒，1毫秒 = 1000微妙，1微秒 = 1000纳秒
     * 因此， 1毫秒 = 10000纳秒
     *
     * @param ldt utc时间
     * @return ticks
     */
    private static long toCsTicks(LocalDateTime ldt) {
        long epochMilli = ldt.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        return (epochMilli * TICKS_PER_MILLISECOND) + TICKS_AT_EPOCH;
    }

    private static LocalDateTime toUtcDate(long utcTicks) {
        long ticks = (utcTicks - TICKS_AT_EPOCH) / TICKS_PER_MILLISECOND;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ticks), ZoneOffset.UTC);
    }

}
