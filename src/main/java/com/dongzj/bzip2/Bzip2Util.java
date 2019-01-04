package com.dongzj.bzip2;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * bzip2压缩算法
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 16:13
 */
public class Bzip2Util {

    /**
     * 压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(bos);
            bcos.write(data);
            bcos.close();
        } catch (IOException e) {
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
            BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(bis);
            final byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                bos.write(buffer, 0, n);
            }
            ungzip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
