package org.imooc.controller.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;


public class TestController {
    public static void main(String[] args) {
        ExecutorService threadPool=new ThreadPoolExecutor(3,5,
                3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        threadPool.execute(()->{

        });
    }

}
