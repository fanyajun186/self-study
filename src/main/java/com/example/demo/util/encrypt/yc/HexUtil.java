package com.example.demo.util.encrypt.yc;

/**
 * @author 韩锐
 * @date 2019/6/11
 */
class HexUtil {

    /**
     * 将十六进制字符串转换成相应的字节数组形式
     *
     * @param data 十六进制字符串
     * @return 字节数组或null
     */
    static byte[] hexToBinary(String data) {
        // 因为在将字节数组转换成十六进制时，每个字节都会分解成2个字符
        // 所以字符串的长度必须是偶数
        if (data == null || data.length() % 2 != 0) {
            return null;
        }

        byte[] binary = new byte[data.length() / 2];

        for (int i = 0; i < binary.length; i++) {
            int highNibble = hexToInt(data.charAt(2 * i));
            int lowNibble = hexToInt(data.charAt(2 * i + 1));

            if (highNibble == -1 || lowNibble == -1) {
                return null;
            }
            binary[i] = (byte) ((highNibble << 4) | lowNibble);
        }

        return binary;
    }

    private static int hexToInt(char h) {
        return (h >= '0' && h <= '9') ? h - '0' :
                (h >= 'a' && h <= 'f') ? h - 'a' + 10 :
                        (h >= 'A' && h <= 'F') ? h - 'A' + 10 :
                                -1;
    }

    /**
     * 将二进制字节数组转换成十六进制字符
     *
     * @param data 二进制数组
     * @return 与二进制等同的十六进制字符串（大写）
     */
    static String binaryToHex(byte[] data) {
        if (data == null) {
            return null;
        }

        char[] hex = new char[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            byte thisByte = data[i];
            // 将一个字节分成2个半字节(nibble)
            // 第一个半字节称为 high nibble，第二个称为 low nibble

            // high nibble
            hex[2 * i] = nibbleToHex((byte) ((thisByte & 0xff) >> 4));
            // low nibble
            hex[2 * i + 1] = nibbleToHex((byte) (thisByte & 0xf));
        }

        return new String(hex);
    }

    /**
     * 将半字节转换成十六进制大写字符，范围[0-9, A-F]
     */
    private static char nibbleToHex(byte nibble) {
        return (char) ((nibble < 10) ? (nibble + '0') : (nibble - 10 + 'A'));
    }
}
