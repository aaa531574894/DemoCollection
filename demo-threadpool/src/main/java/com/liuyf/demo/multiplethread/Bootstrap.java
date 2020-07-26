package com.liuyf.demo.multiplethread;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/26.
 */

public class Bootstrap {


    public static void main(String[] args) {
        //当i<=14时，用的全是core thread，当i>= 14时，开始启动额外的线程，这是因为队列还没满。
        // 当i>=19时， 报错了，因为任务队列满了； 且最大线程数也超出了限制，执行reject策略。

        ExecutorService threadPool = new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        for (int i = 0; i < 19; i++) {
            threadPool.submit(new TimerTask(i));
        }


    }


    // 输出当前时间， 然后休眠1s
    static class TimerTask implements Runnable{

        int id;
        public TimerTask(int i) {
            this.id = i;
        }

        @Override
        public void run() {
            System.out.println("task id : " + id + " thread id :" + Thread.currentThread().getId() + "-------" + LocalDateTime.now());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
