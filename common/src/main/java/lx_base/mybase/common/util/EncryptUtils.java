package lx_base.mybase.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/*********************************************
 * author: Blankj on 2016/8/2 21:22
 * blog:   http://blankj.com
 * e-mail: blankj@qq.com
 *
 * MD5加密 encryptMD5
 * SHA加密 encryptSHA
 *********************************************/
public class EncryptUtils {

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * MD5加密
     */
    public static String encryptMD5(String data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return new BigInteger(md5.digest(data.getBytes())).toString(16);
    }

    /**
     * SHA加密
     */
    public static String encryptSHA(String data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        return new BigInteger(sha.digest(data.getBytes())).toString(32);
    }
}