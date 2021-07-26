package com.example.messagingstompwebsocket.adapter.controller;

import com.example.messagingstompwebsocket.adapter.readmodel.HelloMessage;
import com.example.messagingstompwebsocket.adapter.viewmodel.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.logging.Logger;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage msg) throws Exception {
        Logger logger = Logger.getLogger(this.getClass().getName());

        logger.info("Get Message! " + msg.getName());
        Thread.sleep(1000); // Simulate sleep, the server can take as long as it need to asynchronously process
                                  // the message. The client will keep working on other things  without waiting for the
                                  // response.
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(msg.getName()) + "!");
    }
}
