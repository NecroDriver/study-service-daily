package com.xin.daily.controller.file;

import java.io.*;

/**
 * @author creator mafh 2019/11/12 9:43
 * @author updater
 * @version 1.0.0
 */
public class FileTest {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\mafh\\Desktop\\res\\test.mp4");
            System.out.println(file.toPath());
            InputStream is = new FileInputStream(file);
            File toFile = new File("C:\\Users\\mafh\\Desktop\\res\\test11111.mp4");
            System.out.println(toFile.toPath());
            OutputStream os = new FileOutputStream(toFile);
            Thread.sleep(5000);
            System.out.println("准备完成，进入循环！");
            int bytesRead = 0;
            byte[] bytes = new byte[1024];
            while ((bytesRead = is.read(bytes)) > 0) {
                System.out.println("开始写入文件，睡眠3秒");
                Thread.sleep(3000);
                os.write(bytes, 0, bytesRead);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
