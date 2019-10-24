package com.kenz.pholcus.pholcus.mqs;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author kenzhao
 * @date 2019/10/24 10:08
 */
@RestController
public class ProducerController {
    private MessageChannel greet;

    public ProducerController(HelloBinding binding) {
        greet = binding.greetingout();
    }

    @GetMapping("/greet/{name}")
    public void publish(@PathVariable String name) {
        String greeting = "Hello, " + name + "!";
        Message<String> msg = MessageBuilder.withPayload(greeting)
                .build();
        this.greet.send(msg);
    }
}
