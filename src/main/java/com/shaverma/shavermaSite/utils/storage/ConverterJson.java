package com.shaverma.shavermaSite.utils.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaverma.shavermaSite.models.baseModel.BaseModel;
import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConverterJson {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Object readJson(String path) throws IOException {
        switch (path) {
            case ("src/main/resources/dataBase/delivery.txt") -> {
                BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dataBase/delivery.txt"));
                Map<Integer, Delivery> deliveryMap = new HashMap<>();
                while (fileReader.ready()) {
                    Delivery delivery = objectMapper.readValue(fileReader.readLine(), Delivery.class);
                    deliveryMap.put(delivery.getClassId(), delivery);
                }
                return deliveryMap;
            }
            case ("src/main/resources/dataBase/product.txt") -> {
                BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dataBase/product.txt"));
                Map<Integer, Product> productMap = new HashMap<>();
                while (fileReader.ready()) {
                    Product product = objectMapper.readValue(fileReader.readLine(), Product.class);
                    productMap.put(product.getClassId(), product);
                }
                return productMap;
            }
            case ("src/main/resources/dataBase/order.txt") -> {
                BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dataBase/order.txt"));
                Map<Integer, Order> orderMap = new HashMap<>();
                while (fileReader.ready()) {
                    Order order = objectMapper.readValue(fileReader.readLine(), Order.class);
                    orderMap.put(order.getClassId(), order);
                }
                return orderMap;
            }
            case ("src/main/resources/dataBase/user.txt") -> {
                BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/dataBase/user.txt"));
                Map<Integer, User> userMap = new HashMap<>();
                while (fileReader.ready()) {
                    User user = objectMapper.readValue(fileReader.readLine(), User.class);
                    userMap.put(user.getClassId(), user);
                }
                return userMap;
            }
        }
        return null;
    }

    public static <T> void writeJson(HashMap<Integer, T> models) throws IOException {
        String path;
        if (models.get(models.keySet().toArray()[0]).getClass() == Delivery.class) {
            path = "src/main/resources/dataBase/delivery.txt";
        } else if (models.get(models.keySet().toArray()[0]).getClass() == Product.class) {
            path = "src/main/resources/dataBase/product.txt";
        } else if (models.get(models.keySet().toArray()[0]).getClass() == Order.class) {
            path = "src/main/resources/dataBase/order.txt";
        } else {
            path = "src/main/resources/dataBase/user.txt";
        }
        try (FileWriter filewriter = new FileWriter(path)) {
            for (T model : models.values()) {
                String value = objectMapper.writeValueAsString(model);
                filewriter.write(value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
