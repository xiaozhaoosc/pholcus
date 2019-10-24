package com.kenz.pholcus.pholcus.mqs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Description: test
 *
 * @author kenzhao
 * @date 2019/10/24 10:05
 */
public interface HelloBinding {
    String MSGCONSUMER = "msgMqConsumer";
    String MSGPROD = "msgMqProducer";

    @Input(MSGCONSUMER)
    SubscribableChannel greetingin();
    @Output(MSGPROD)
    MessageChannel greetingout();
}
