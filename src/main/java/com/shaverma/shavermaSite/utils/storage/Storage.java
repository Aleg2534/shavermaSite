package com.shaverma.shavermaSite.utils.storage;

import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@ComponentScan(basePackages = "java.util.HashMap")
@Getter
public class Storage {
    private static Map<Integer, Delivery> deliveryMap;
    private static Map<Integer, Order> orderMap;
    private static Map<Integer, Product> productMap;
    private static Map<Integer, User> userMap;

    public static Map<Integer, Delivery> getDeliveryMap() {
        return deliveryMap;
    }

    public static Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public static Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public static Map<Integer, User> getUserMap() {
        return userMap;
    }

    @Autowired
    public Storage(Map<Integer, Delivery> deliveryMap, Map<Integer, Order> orderMap,
                   Map<Integer, Product> productMap, Map<Integer, User> userMap) {
        Storage.deliveryMap = deliveryMap;
        Storage.orderMap = orderMap;
        Storage.productMap = productMap;
        Storage.userMap = userMap;
    }

    @PostConstruct
    public void init() throws IOException {
        deliveryMap = (Map<Integer, Delivery>) ConverterJson.readJson(Consts.DELIVERY_PATH);
        orderMap = (Map<Integer, Order>) ConverterJson.readJson(Consts.ORDER_PATH);
        productMap = (Map<Integer, Product>) ConverterJson.readJson(Consts.PRODUCT_PATH);
        userMap = (Map<Integer, User>) ConverterJson.readJson(Consts.USER_PATH);
    }

    @PreDestroy
    public void destroy() {
        try {
            ConverterJson.writeJson(deliveryMap);
            ConverterJson.writeJson(orderMap);
            ConverterJson.writeJson(productMap);
            ConverterJson.writeJson(userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDelivery(Delivery newDelivery) {
        deliveryMap.put(newDelivery.getClassId(), newDelivery);
    }

    public Delivery getDelivery(int deliveryId) {
        return deliveryMap.get(deliveryId);
    }

    public void setOrder(Order newOrder) {
        orderMap.put(newOrder.getClassId(), newOrder);
    }

    public Order getOrder(int orderId) {
        return orderMap.get(orderId);
    }

    public void setProduct(Product newProduct) {
        productMap.put(newProduct.getClassId(), newProduct);
    }

    public Product getProduct(int productId) {
        return productMap.get(productId);
    }

    public void setUser(int i, User newUser) {
        userMap.put(newUser.getClassId(), newUser);
    }

    public User getUser(int userId) {
        return userMap.get(userId);
    }

    public boolean checkEmailUser(String email) {
        return Storage.userMap.values().stream().anyMatch(user ->
                user.getEmailAddress().equals(email));
    }

    public User checkLoginAndPasswordUser(String email, String password) {
//        return getUserMap().values().stream().anyMatch(user ->
//                user.getEmailAddress().equals(email) && user.getPassword().equals(password));
        return Storage.userMap.values().stream().filter(user ->
                user.getEmailAddress().equals(email) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    public Integer registrationUser(String login, String email, String password) {
        int userId = newId(userMap);
        userMap.put(userId, new User(userId, login, password, email, null, null, Roles.BASE_USER));
        return userId;
    }

    public static  <T> int newId(Map<Integer, T> objectMap) {
        return (objectMap.keySet().size() > 0 ?
                objectMap.keySet().stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax() : 0) + 1;
    }

}
