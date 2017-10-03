package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${WELCOME_MESSAGE:NOT SET}")
    String welcomeMessage;

    public WelcomeController(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public WelcomeController() {

    }

    @GetMapping("/")
    public String sayHello() {
        return welcomeMessage;
    }
}
