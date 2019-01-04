package com.dongzj;

import com.dongzj.jdk.ZlibUtil;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/14
 * Time: 15:33
 */
public class Main {

    /**
     * 压缩算法：
     *  Bzip2Util
     *  GzipUtil
     *  ZlibUtil
     *  Lz4Util
     *  LzoUtil
     *  SnappyUtil
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/dongzj/Downloads/123.png");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate((int) channel.size());
        channel.read(bb);
        byte[] beforeBytes = bb.array();

        int times = 2000;
        System.out.println("压缩前大小：" + beforeBytes.length + " bytes");
        long startTime1 = System.currentTimeMillis();
        byte[] afterBytes = null;
        for (int i = 0; i < times; i++) {
            afterBytes = ZlibUtil.compress(beforeBytes);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("压缩后大小： " + afterBytes.length + " bytes");
        System.out.println("压缩次数：" + times + ", 时间：" + (endTime1 - startTime1) + "ms");

        byte[] resultBytes = null;
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            resultBytes = ZlibUtil.uncompress(afterBytes);
        }
        System.out.println("解压缩后大小：" + resultBytes.length + " bytes");
        long endTime2 = System.currentTimeMillis();
        System.out.println("解压缩次数：" + times + ", 时间： " + (endTime2 - startTime2) + "ms");
    }

}
