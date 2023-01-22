package com.shaverma.shavermaSite;
import com.shaverma.shavermaSite.utils.storage.AnnotationConfig;
import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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