package com.kenz.pholcus.pholcus.mqs;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Description:
 *
 * @author kenzhao
 * @date 2019/10/24 10:35
 */
@EnableBinding(HelloBinding.class)
public class HelloListener {

    @StreamListener(target = HelloBinding.GREETING)
    public void processHelloChannelGreeting(String msg) {
        System.out.println(msg);
    }
}
