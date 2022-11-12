package com.shaverma.shavermaSite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Basket;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.Consts;
import com.shaverma.shavermaSite.utils.storage.ConverterJson;
import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.constant.ConstantDescs.NULL;
import static java.lang.constant.ConstantDescs.ofConstantBootstrap;

@SpringBootApplication
public class ShavermaSiteApplication {

    public static void main(String[] args) throws IOException {

//		SpringApplication.run(ShavermaSiteApplication.class, args);
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext("com.shaverma.shavermaSite");
//        Storage storage = context.getBean(Storage.class);
//        HashMap<Integer, User> userHashMap= new HashMap<>();
//        Order order= new Order();
//        List<Order> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            userHashMap.put(i,new User(i,"asadczx","adfvd","assdc",order,list, Roles.ADMIN));
//        }
//        try {
//            ConverterJson.writeJson(userHashMap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        HashMap<Integer, Order> orderHashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Basket basket = new Basket(new HashMap<>());
            for(int j=0;j<3;j++)
            {
                basket.getBasket().put((int) (Math.random() * 10),1);
            }
            orderHashMap.put(i, new Order(i, basket, 123.23 * i, (Delivery) Objects.requireNonNull(ConverterJson.readJson("src/main/resources/dataBase/delivery.txt")).get(i)));
            String basketString = (new ObjectMapper()).writeValueAsString(basket);
            int a=0;
        }
        ConverterJson.writeJson(orderHashMap);
//        System.out.println(ConverterJson.readJson(Consts.ORDER_PATH));
    }
}