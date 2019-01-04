package com.dongzj.lz4;

import net.jpountz.lz4.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * lz4 着重于压缩和解压缩速度
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 16:30
 */
public class Lz4Util {

    /**
     * 压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        LZ4Factory factory = LZ4Factory.fastestInstance();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        LZ4Compressor compressor = factory.fastCompressor();
        LZ4BlockOutputStream compressedOutput = new LZ4BlockOutputStream(bos, 2048, compressor);
        try {
            compressedOutput.write(data);
            compressedOutput.close();
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
        LZ4Factory factory = LZ4Factory.fastestInstance();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        LZ4BlockInputStream lzis = new LZ4BlockInputStream(bis, decompressor);
        int count;
        final byte[] buffer = new byte[2048];
        try {
            while ((count = lzis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
            lzis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
