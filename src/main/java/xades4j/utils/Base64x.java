package xades4j.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by vk on 19.12.16.
 */
public class Base64x {

    public static byte[] decode(String value) throws java.io.IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] decodeBuffer = base64Decoder.decodeBuffer(value);
            System.out.println(new String((decodeBuffer)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static String encodeBytes(byte[] source) {
        return new BASE64Encoder().encode(source);
    }


}
