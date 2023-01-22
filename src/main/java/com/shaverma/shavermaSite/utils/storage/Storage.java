package com.shaverma.shavermaSite.utils.storage;

import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.enums.Roles;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Component
@ComponentScan(basePackages = "java.util.HashMap")
@Getter
public class Storage {
    private Map<Integer, Delivery> deliveryMap;
    private Map<Integer, Order> orderMap;
    private Map<Integer, Product> productMap;
    private Map<Integer, User> userMap;

    @Autowired
    public Storage(Map<Integer, Delivery> deliveryMap, Map<Integer, Order> orderMap,
                   Map<Integer, Product> productMap, Map<Integer, User> userMap) {
        this.deliveryMap = deliveryMap;
        this.orderMap = orderMap;
        this.productMap = productMap;
        this.userMap = userMap;
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

    public void setUser(User newUser) {
        userMap.put(newUser.getClassId(), newUser);
    }

    public User getUser(int userId) {
        return userMap.get(userId);
    }

    public boolean checkEmailUser(String email) {
        return getUserMap().values().stream().anyMatch(user ->
                user.getEmailAddress().equals(email));
    }

    public User checkLoginAndPasswordUser(String email, String password) {
//        return getUserMap().values().stream().anyMatch(user ->
//                user.getEmailAddress().equals(email) && user.getPassword().equals(password));
        return getUserMap().values().stream().filter(user ->
                user.getEmailAddress().equals(email) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    public void registrationUser(String login, String email, String password) {
        int id = (userMap.keySet().stream().max(Integer::compare).isPresent() ?
                userMap.keySet().stream().max(Integer::compare).get() : 0)
                + 1;

        userMap.put(id, new User(id, login, password, email, null, null, Roles.BASE_USER));
    }
}
