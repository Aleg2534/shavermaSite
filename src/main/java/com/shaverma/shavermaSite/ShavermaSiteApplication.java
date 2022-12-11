package com.shaverma.shavermaSite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Basket;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.storage.AnnotationConfig;
import com.shaverma.shavermaSite.utils.storage.Consts;
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

//@SpringBootApplication
public class ShavermaSiteApplication {

    public static void main(String[] args) throws IOException {

//        SpringApplication.run(ShavermaSiteApplication.class, args);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(AnnotationConfig.class);
        context.refresh();
        Storage storage = context.getBean(Storage.class);
//        Map<Integer,Order> orderMap = new HashMap<>();
//        Map<Integer, User> userMap = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            Basket basket = new Basket(new HashMap<>());
//            for (int j = 0; j < 3; j++) {
//                basket.getBasket().put(i,(2-i%2)*3);
//            }
//            storage.setOrder(new Order(i,basket,123124123.312,storage.getDelivery(i)));
//            orderMap.put(i,new Order(i,basket,123124123.312,storage.getDelivery(i)));
//            userMap.put(i,new User(i,"pup"+i,"abcdf","asadwq@amklsmd",storage.getOrder(i),null, Roles.BASE_USER));
//            storage.setUser(new User(i,"pup"+i,"abcdf","asadwq@amklsmd",storage.getOrder(i),null, Roles.BASE_USER));
//        }
//        ConverterJson.writeJson(orderMap);
//        ConverterJson.writeJson(userMap);
        User user = storage.getUser(9);
        user.setRole(Roles.ADMIN);
        storage.setUser(user);
        context.close();
        int i=0;
    }
}