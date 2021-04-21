package com.hhd.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by huhengda on 2021/3/22.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\test2.txt", "rw");
//        FileChannel channel = file.getChannel();

        file.seek(0);
        file.write("hhd".getBytes());
        file.seek(1);
        file.write("test".getBytes());

        FileChannel channel = file.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        map.put("ppp".getBytes());

        file.close();

        String s = "mat_1234";
        System.out.println(s.substring(4));
    }
}
