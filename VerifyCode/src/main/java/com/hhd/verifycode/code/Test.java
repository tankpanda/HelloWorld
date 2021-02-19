package com.hhd.verifycode.code;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by huhengda on 2021/2/8.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ImageCode instance = ImageCode.getInstance();
        ByteArrayInputStream image = instance.getImage();
        OutputStream outputStream = new FileOutputStream(new File("D://a.jpg"));
        byte[] b = new byte[1024];

        while(image.read(b) != -1) {
            outputStream.write(b);
        }
        outputStream.flush();
        System.out.println(instance.getCode());
    }
}
