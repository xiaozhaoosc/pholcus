package com.kenz.pholcus.pholcus;

import com.kenz.pholcus.pholcus.services.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@SpringBootApplication
@RestController
public class PholcusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PholcusApplication.class, args);
    }

    private final static Logger logger = LoggerFactory.getLogger(PholcusApplication.class);
    @Autowired
    SyncService asyncService;


    @GetMapping("/hello")
    public String hello(){
        System.out.println("进入Controller。。。");
        asyncService.hello();
        Future<String> future=asyncService.asyncInvokeReturnFuture(300);
        String s = null;
        try {
            s = future.get();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("异步方法返回值 ： "+s);
        return s;
    }

}
