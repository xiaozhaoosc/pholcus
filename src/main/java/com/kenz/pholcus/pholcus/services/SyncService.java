package com.kenz.pholcus.pholcus.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Description:
 *
 * @author kenzhao
 * @date 2019/9/30 16:45
 */
@Service
public class SyncService {

    @Async
    public void hello(){
        System.out.println("进入service。。。");
        try {
            Thread.sleep(3000);
            System.out.println("3S后数据开始处理中。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        System.out.println("进入asyncInvokeReturnFuture..." + Thread.currentThread().getName());
        Future<String> future;
        try {
            Thread.sleep(3000);
            System.out.println("3S后asyncInvokeReturnFuture数据开始处理中。。");
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        return future;
    }
}
