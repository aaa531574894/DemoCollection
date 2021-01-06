package com.yifei.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2021/1/6.
 */

public class FileUtil {


    public static File getTempFile(String url) throws Exception {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("/")+1);
        File file = null;

        URL          urlfile;
        InputStream  inStream = null;
        OutputStream os       = null;
        try {
            file = File.createTempFile("net-", "."+fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
