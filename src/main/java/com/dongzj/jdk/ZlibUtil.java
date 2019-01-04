package com.dongzj.jdk;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * 压缩Deflater和解压类Inflater
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 15:35
 */
public class ZlibUtil {

    /**
     * 压缩
     * Deflater可以指定压缩级别（0-9）
     * 0 不压缩
     * 1 快速压缩
     * 0 慢速压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(9);
        try {
            compressor.setInput(data);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    /**
     * 解压缩
     *
     * @param data
     * @return
     */
    public static byte[] uncompress(byte[] data) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(data);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();
    }
}
