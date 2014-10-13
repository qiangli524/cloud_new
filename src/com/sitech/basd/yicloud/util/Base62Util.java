package com.sitech.basd.yicloud.util;

import java.math.BigInteger;
import java.util.UUID;

/**
 *
 * @author liujhc <liujhc@si-tech.com.cn>
 */
public class Base62Util {

    private static final BigInteger divisor = BigInteger.valueOf(62);
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encode(byte[] bytes) {
        BigInteger bi = new BigInteger(bytes).shiftLeft(1);
        if (bi.signum() == -1) {//如果是负数，变为正数加1
            bi = bi.negate().add(BigInteger.ONE);
        }
        StringBuilder sb = new StringBuilder();
        while (bi.signum() != 0) {
            BigInteger[] arr = bi.divideAndRemainder(divisor);
            bi = arr[0];
            sb.append(ALPHABET.charAt(arr[1].intValue()));
        }
        return sb.reverse().toString();
    }

    public static byte[] decode(String str) {
        BigInteger bi = BigInteger.ZERO;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            String v = String.valueOf(ALPHABET.indexOf(str.charAt(len - 1 - i)));
            bi = new BigInteger(v).multiply(divisor.pow(i)).add(bi);
        }
        if (bi.getLowestSetBit() == 0) {
            bi = bi.subtract(BigInteger.ONE).negate();
        }
        return bi.shiftRight(1).toByteArray();
    }

    public static String encode(String str) {
        return encode(str.getBytes());
    }

    public static String encode(long num) {
        return encode(BigInteger.valueOf(num).toByteArray());
    }

    public static String decodeToString(String str) {
        return new String(decode(str));
    }

    public static BigInteger decodeToNum(String str) {
        return new BigInteger(decode(str));
    }

    /**
     * 通过此方法生成的随机UUID只有22位长（大小写敏感）
     *
     * @return
     */
    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        BigInteger integer = new BigInteger(uuid.replaceAll("[\\-]", ""), 16);
        return encode(integer.toByteArray());
    }
}
