package com.shaverma.shavermaSite.utils.storage;

import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductsLogic {
    public static void addProduct(String userId, String productId) {
        User user = Storage.getUserMap().get(Integer.parseInt(userId)).clone();
        if (user.getCurrentOrder() == null) {
            user.setCurrentOrder(new Order());
        }

        user.getCurrentOrder().getBasket().getBasket().put(Integer.parseInt(productId),
                user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) == null ? 1 :
                        user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) + 1);
        Storage.getUserMap().put(Integer.parseInt(userId), user);
    }

    public static void deleteProduct(String userId, String productId) {
        User user = Storage.getUserMap().get(Integer.parseInt(userId)).clone();
        if (user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) > 1) {
            user.getCurrentOrder().getBasket().getBasket().put(Integer.parseInt(productId),
                    user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) - 1);
        } else {
            user.getCurrentOrder().getBasket().getBasket().remove(Integer.parseInt(productId));
        }
        Storage.getUserMap().put(Integer.parseInt(userId), user);
    }
}
