package com.shaverma.shavermaSite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.storage.ConverterJson;
import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ShavermaSiteApplication {

    public static void main(String[] args) throws IOException {

//		SpringApplication.run(ShavermaSiteApplication.class, args);
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext("com.shaverma.shavermaSite");
//        Storage storage = context.getBean(Storage.class);
//
    }
}