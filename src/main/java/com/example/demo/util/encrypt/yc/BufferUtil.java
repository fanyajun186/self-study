package com.example.demo.util.encrypt.yc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author 韩锐
 * @date 2019/6/12
 */
class BufferUtil {

    /**
     * 以小端序的方式读取一个long类型。C#是以小端序方式写入的，所以也需要以同样的方式读
     */
    static long getLongLittleEndian(ByteBuffer buffer) {
        long value = buffer.getLong();
        return Long.reverseBytes(value);
    }

    static String readBinaryString(ByteBuffer buffer) {
        int charCount = read7BitEncodedInt(buffer);
        byte[] bytes = new byte[charCount * 2];
        buffer.get(bytes, 0, bytes.length);

        char[] chars = new char[charCount];
        for (int i = 0; i < chars.length; i++) {
            //先统一将byte转成int类型，避免位移异常
            int b1 = bytes[2 * i] & 0xFF;
            int b2 = bytes[2 * i + 1] & 0xFF;
            b2 = b2 << 8;
            chars[i] = (char) (b1 | b2);
        }

        return new String(chars);
    }

    private static int read7BitEncodedInt(ByteBuffer buffer) {
        int count = 0;
        int shift = 0;
        byte b;
        do {
            //如果shift最大超过5*7，那么可以认定数据非法
            if (shift == 5 * 7) {
                // 5 bytes max per Int32, shift += 7
                throw new IllegalArgumentException("Format_Bad7BitInt32");
            }

            b = buffer.get();
            count |= (b & 0x7F) << shift;
            shift += 7;
        } while ((b & 0x80) != 0);
        return count;
    }

    static void writeBinaryString(ByteArrayOutputStream buffer, String value) throws IOException {
        //写入字符长度
        //为了节省空间，对于长度值做了区分对待
        //如果字符串长度大于128，那么需要切分成多个字节组
        //同时将字节最高位设为开，以区分总共需要多去多少自己
        int v = value.length();
        while (v >= 0x80) {
            buffer.write((byte) (v | 0x80));
            v >>= 7;
        }
        buffer.write((byte) v);

        //写入字节内容
        int length = value.length();
        //char在java中是utf-16编码
        //所以一个char需要2个字节的空间存储
        byte[] bytes = new byte[length * 2];

        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            //将char转成byte时，只识别低位8个字节
            bytes[2 * i] = (byte) c;
            //存储高位8字节
            bytes[2 * i + 1] = (byte) (c >> 8);
        }

        buffer.write(bytes);
    }

}
