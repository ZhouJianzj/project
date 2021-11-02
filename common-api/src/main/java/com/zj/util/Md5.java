package com.zj.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhoujian
 */
public class Md5 {
    /**
     * 使用md5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密完成
        return base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
    }

    /**
     * 比对密码
     * @param newpasswd
     * @param oldpasswd
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else {
            return false;
        }
    }


}
