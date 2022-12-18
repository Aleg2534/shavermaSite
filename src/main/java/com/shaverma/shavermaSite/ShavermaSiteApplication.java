package com.shaverma.shavermaSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
//import javax.servlet.*;
@SpringBootApplication
public class ShavermaSiteApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ShavermaSiteApplication.class, args);
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext();
//        context.register(AnnotationConfig.class);
//        context.refresh();
//        Storage storage = context.getBean(Storage.class);
//        context.close();
    }
}