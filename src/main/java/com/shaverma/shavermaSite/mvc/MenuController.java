package com.shaverma.shavermaSite.mvc;

import com.shaverma.shavermaSite.models.order.Order;
import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MenuController {
    Storage storage;

    @Autowired
    public MenuController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/Menu")
    public String getMenu(@RequestParam(name = "userId", required = false) String userId, Model model) {
        System.out.println(storage.getProductMap().values().stream().toList().toString());
        model.addAttribute("listProducts", storage.getProductMap().values().stream().toList());
        model.addAttribute("userId", userId);
        return "Menu";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(name = "userId", required = false) String userId,
                             @RequestParam(name = "productId", required = false) String productId, Model model) {
        System.out.println(userId);
        System.out.println(productId);
        User user = storage.getUser(Integer.parseInt(userId)).clone();
        if (user.getCurrentOrder() == null) {
            user.setCurrentOrder(new Order());
        }

        user.getCurrentOrder().getBasket().getBasket().put(Integer.parseInt(productId),
                user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) == null ? 1 :
                        user.getCurrentOrder().getBasket().getBasket().get(Integer.parseInt(productId)) + 1);
        storage.setUser(Integer.parseInt(userId),user);
        return "Menu";
    }
}
