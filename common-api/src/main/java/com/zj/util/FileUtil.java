package com.zj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoujian
 */
public class FileUtil {
    /**
     * 文件存放的动态地址
     * @param rootPath 磁盘位置
     * @param originalFileName 文件原始名字
     * @return 动态生成的最终存放在磁盘的地址
     */
    public static String generateFileName(String rootPath,String originalFileName){
        String uuid = MyUUID.getUUID();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
        return new StringBuffer(150).append(rootPath).append("/")
                .append(format).append("/")
                .append(uuid).append("/")
                .append(originalFileName).toString();
    }

}
