package com.shaverma.shavermaSite.utils.storage;

import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Basket;
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
    private Map<Integer, Delivery> deliveryMap;
    private Map<Integer, Order> orderMap;
    private Map<Integer, Product> productMap;
    private Map<Integer, User> userMap;

    private ProductsLogic productsLogic;

    private class ProductsLogic {
        public void addProduct(String userId, String productId) {
            User user = userMap.get(Integer.parseInt(userId)).clone();
            if (user.getCurrentOrder() == null) {
                user.setCurrentOrder(new Order(newId(orderMap),null,0,null));
            }
            if (user.getCurrentOrder().getBasket()==null)
            {
                user.getCurrentOrder().setBasket(new Basket());
                user.getCurrentOrder().getBasket().setBasket(new HashMap<Integer,Integer>());
            }
            if (user.getCurrentOrder().getBasket().getBasket()==null)
            {
                user.getCurrentOrder().getBasket().setBasket(new HashMap<Integer,Integer>());
            }
            user.getCurrentOrder().getBasket().getBasket().put(Integer.parseInt(productId),
                    user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) == null ? 1 :
                            user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) + 1);
            userMap.put(Integer.parseInt(userId), user);
        }

        public void deleteProduct(String userId, String productId) {
            User user = userMap.get(Integer.parseInt(userId)).clone();
            if (user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) > 1) {
                user.getCurrentOrder().getBasket().getBasket().put(Integer.parseInt(productId),
                        user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) - 1);
            } else {
                user.getCurrentOrder().getBasket().getBasket().remove(Integer.parseInt(productId));
            }
            userMap.put(Integer.parseInt(userId), user);
        }
    }


    public Map<Integer, Delivery> getDeliveryMap() {
        return deliveryMap;
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

//    @Autowired
//    public Storage(Map<Integer, Delivery> deliveryMap, Map<Integer, Order> orderMap,
//                   Map<Integer, Product> productMap, Map<Integer, User> userMap) {
//        Storage.deliveryMap = deliveryMap;
//        Storage.orderMap = orderMap;
//        Storage.productMap = productMap;
//        Storage.userMap = userMap;
//    }

    @PostConstruct
    public void init() throws IOException {
        deliveryMap = (Map<Integer, Delivery>) ConverterJson.readJson(Consts.DELIVERY_PATH);
        orderMap = (Map<Integer, Order>) ConverterJson.readJson(Consts.ORDER_PATH);
        productMap = (Map<Integer, Product>) ConverterJson.readJson(Consts.PRODUCT_PATH);
        userMap = (Map<Integer, User>) ConverterJson.readJson(Consts.USER_PATH);
        productsLogic = new ProductsLogic();
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
        return userMap.values().stream().anyMatch(user ->
                user.getEmailAddress().equals(email));
    }

    public User checkLoginAndPasswordUser(String email, String password) {
//        return getUserMap().values().stream().anyMatch(user ->
//                user.getEmailAddress().equals(email) && user.getPassword().equals(password));
        return userMap.values().stream().filter(user ->
                user.getEmailAddress().equals(email) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    public Integer registrationUser(String login, String email, String password) {
        int userId = newId(userMap);
        userMap.put(userId, new User(userId, login, password, email, null, null, Roles.BASE_USER));
        return userId;
    }

    public <T> int newId(Map<Integer, T> objectMap) {
        return (objectMap.keySet().size() > 0 ?
                objectMap.keySet().stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax() : 0) + 1;
    }

    public void addProduct(String userId, String productId) {
        productsLogic.addProduct(userId, productId);
    }

    public void deleteProduct(String userId, String productId) {
        productsLogic.deleteProduct(userId, productId);
    }
}
