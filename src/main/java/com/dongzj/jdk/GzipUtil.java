package com.dongzj.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 16:06
 */
public class GzipUtil {

    /**
     * 压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    /**
     * 解压缩
     *
     * @param data
     * @return
     */
    public static byte[] uncompress(byte[] data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(bis);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                bos.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
