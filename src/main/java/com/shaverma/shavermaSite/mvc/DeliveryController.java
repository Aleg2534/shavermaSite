package com.shaverma.shavermaSite.mvc;

import com.shaverma.shavermaSite.models.delivery.Delivery;
import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeliveryController {
    Storage storage;

    @Autowired
    public DeliveryController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/Delivery")
    public String getDelivery(@RequestParam(name = "userId", required = false) String userId, Model model) {
        User user = storage.getUser(Integer.parseInt(userId)).clone();
        if (user.getCurrentOrder().getDelivery() == null) {
            user.getCurrentOrder().setDelivery(new Delivery(Storage.newId(Storage.getDeliveryMap()),
                    "",0, 0));
        }
        user.getCurrentOrder().getDelivery().setPrice(333);
        user.getCurrentOrder().getDelivery().setSum(user.getCurrentOrder().getBasket().finalSum());
        storage.setUser(Integer.parseInt(userId), user);
        model.addAttribute("userId", userId);
        model.addAttribute("delivery", user.getCurrentOrder().getDelivery());
        return "ordering/Delivery";
    }

    @PostMapping("/changeAddress")
    public String changeAddress(@RequestParam(name = "userId", required = false) String userId,
                                @RequestParam(name = "address", required = false) String address, Model model) {
        User user = storage.getUser(Integer.parseInt(userId)).clone();
        user.getCurrentOrder().getDelivery().setAddress(address);
        storage.setUser(Integer.parseInt(userId), user);
        model.addAttribute("userId", userId);
        return getDelivery(userId, model);
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam(name = "userId", required = false) String userId, Model model) {
        User user = storage.getUser(Integer.parseInt(userId)).clone();
        if (user.getOrderHistory() == null) {
            user.setOrderHistory(new ArrayList<Order>());
        }
        user.getOrderHistory().add(user.getCurrentOrder());
        storage.setOrder(storage.getUser(Integer.parseInt(userId)).getCurrentOrder());
        storage.setDelivery(storage.getUser(Integer.parseInt(userId)).getCurrentOrder().getDelivery());
        user.setCurrentOrder(null);
        storage.setUser(Integer.parseInt(userId), user);
        model.addAttribute("listProducts", Storage.getProductMap().values().stream().toList());
        model.addAttribute("userId", userId);
        return "/Menu";
    }
}
