package com.dongzj.snappy;

import org.xerial.snappy.Snappy;

import java.io.IOException;

/**
 * snappy 目标是非常高的速度和合理的压缩率
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 16:40
 */
public class SnappyUtil {

    /**
     * 解压
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) throws IOException {
        return Snappy.compress(data);
    }

    /**
     * 解压
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] uncompress(byte[] data) throws IOException {
        return Snappy.uncompress(data);
    }
}
