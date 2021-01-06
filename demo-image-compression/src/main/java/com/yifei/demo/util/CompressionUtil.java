package com.yifei.demo.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;

/**
 * 图片压缩工具类
 *
 * <p>
 * <p>
 * Created by liuyf on 2021/1/5.
 */

public class CompressionUtil {


    public static void commpressPicCycle(File originalFile,
                                         final long desFileSize,
                                         final File targetDir) throws IOException {
        long fileSize = originalFile.length();
        if (fileSize < desFileSize) {
            return;
        }
        Thumbnails.of(originalFile)
                  .scale(1.0)
                  .outputFormat("jpg")
                  .outputQuality(0.9f)
                  .toFiles(targetDir, Rename.NO_CHANGE);

        File resultFile = new File(targetDir.getAbsolutePath() + "/" + originalFile.getName());
        //如果不满足要求,递归直至满足小于1M的要求
        commpressPicCycle(resultFile, desFileSize, targetDir);
    }


}
