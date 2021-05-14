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

        // mmap 映射空间的逻辑地址映射到内存的pagecache上
        // 是mmap调用的一个进程和内核共享的内存区域且这个内存区域是pagecahce/到文件的映射
        FileChannel channel = file.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        map.put("ppp".getBytes());

        map.force(); // =flush()
        file.close();

        String s = "mat_1234";
        System.out.println(s.substring(4));

        // jvm堆内
        ByteBuffer bb1 = ByteBuffer.allocate(4096);
        // jvm堆外 java进程的堆里面
        ByteBuffer bb2 = ByteBuffer.allocateDirect(4096);
        bb1.flip(); // 读写之间需要反转
        // bytebuffer最终通过channel.read or write方法copy到内存的pagecache上
        // 堆内分配还会先copy到堆外 再copy到内存
        channel.read(bb1);
        channel.write(bb2);

        // 性能 on heap < off heap < mapped
    }
}
