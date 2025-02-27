package org.shadcn.chatsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChatSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatSvcApplication.class, args);
    }
}
