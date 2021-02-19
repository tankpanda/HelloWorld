package com.hhd.verifycode.code;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by huhengda on 2021/2/8.
 */
public class ImageCode {
    private String code;
    private ByteArrayInputStream image;

    private int width = 400;
    private int height = 100;

    public static ImageCode getInstance() {
        return new ImageCode();
    }

    private ImageCode() {
        // 创建图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取绘图画笔
        Graphics graphics = image.getGraphics();
        // 设置绘图画笔颜色
        graphics.setColor(new Color(17, 147, 37));
        // 填充背景
        graphics.fillRect(0, 0, width, height);
        // 设置字体
        graphics.setFont(new Font("宋体", Font.PLAIN, 30));

        Random random = new Random();
        this.code = "";
        // 绘制验证码
        for (int i = 0; i < 6; i++) {
            String s = String.valueOf(random.nextInt(10));
            this.code += s;

            graphics.setColor(new Color(235, 152, 37));
            graphics.drawString(s, width / 6 * i + 30, 40);
        }
        // 绘制噪点
        for (int i = 0; i < 100; i++) {
            graphics.setColor(new Color(255, 255, 255));
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x1, y1, x1 + random.nextInt(100) - 50, y1 + random.nextInt(30) - 15);
        }
        graphics.dispose();


        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image, "jpeg", imageOutputStream);
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = inputStream;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ByteArrayInputStream getImage() {
        return image;
    }

    public void setImage(ByteArrayInputStream image) {
        this.image = image;
    }
}
