package com.dongzj.lzo;

import org.anarres.lzo.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * lzo
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 16:20
 */
public class LzoUtil {

    /**
     * 压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(LzoAlgorithm.LZO1X, null);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        LzoOutputStream cs = new LzoOutputStream(bos, compressor);
        try {
            cs.write(data);
            cs.close();
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
        if (data == null) {
            return null;
        }
        LzoDecompressor decompressor = LzoLibrary.getInstance().newDecompressor(LzoAlgorithm.LZO1X, null);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        LzoInputStream us = new LzoInputStream(bis, decompressor);
        try {
            int count;
            final byte[] buffer = new byte[2048];
            while ((count = us.read(buffer)) >= 0) {
                bos.write(buffer, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
