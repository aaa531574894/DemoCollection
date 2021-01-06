package com.yifei.demo.service;

import com.yifei.demo.dao.ImageRepository;
import com.yifei.demo.entity.Image;
import com.yifei.demo.util.CompressionUtil;
import com.yifei.demo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@Slf4j
public class CompressServiceImpl {

    @Autowired
    private RestTemplate    restTemplate;
    @Autowired
    private ImageRepository imageRepository;

    final int MAX_SIZE = 20 * 1024 * 1024; //20MB
    String          targetDirectory = "/Users/yifeiliu/Desktop/compress/target";
    File            destDir         = new File(targetDirectory);
    ExecutorService threadPool      = Executors.newFixedThreadPool(8);  //负责压缩的线程池

    @Async
    public void compressIfNecessary() {
        List<Image> imageList = imageRepository.findAll();
        log.info("准备开始....");
        imageList.forEach(this::doCompress);
    }

    public void doCompress(Image image) {
        String url = image.getUrl();
        log.info("");
        log.info(url);
        try {
            HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
            int length = Optional.of(httpHeaders)
                                 .map(h -> h.get(HttpHeaders.CONTENT_LENGTH))
                                 .map(l -> l.get(0))
                                 .map(Integer::valueOf)
                                 .orElse(-1);
            if (length >= MAX_SIZE) {
                log.info("此图片大小为 " + length + "字节，需要压缩，先下载");
                threadPool.submit(() -> {
                    try {
                        File tempFileFromUrl = FileUtil.getTempFile(url);
                        CompressionUtil.commpressPicCycle(tempFileFromUrl, MAX_SIZE, destDir);
                        log.info("压缩结束");
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                });
            } else {
                log.info("此图片大小为 " + length + "字节，不需要压缩");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    public static void main(String[] args) {
        // do rename
        String          targetDirectory = "/Users/yifeiliu/Desktop/compress/target";
        File            destDir         = new File(targetDirectory);

        File[] images = destDir.listFiles();

        for (File image : images) {
            String fromName = image.getName();
            String[] arr = fromName.split("\\.");
            String targetName = arr[arr.length - 2] + "." + arr[arr.length - 1];
            image.renameTo(new File(targetDirectory + "/" + targetName));
            System.out.println(fromName + "  to " + targetName);
        }

    }

}
