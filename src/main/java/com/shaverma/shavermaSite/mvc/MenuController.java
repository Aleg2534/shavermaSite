package com.shaverma.shavermaSite.mvc;

import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {
    Storage storage;

    @Autowired
    public MenuController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/Menu")
    public String getMenu(@RequestParam(name = "userId", required = false) String userId, Model model) {
//        System.out.println(storage.getProductMap().values().stream().toList().toString());
        model.addAttribute("listProducts", storage.getProductMap().values().stream().toList());
        model.addAttribute("userId", userId);
        return "Menu";
    }

    @PostMapping("/addProductMenu")
    public String addProduct(@RequestParam(name = "userId", required = false) String userId,
                             @RequestParam(name = "productId", required = false) String productId, Model model) {
//        System.out.println(userId);
//        System.out.println(productId);
        storage.addProduct(userId, productId);
        return getMenu(userId, model);
    }

    @PostMapping("/deleteProductMenu")
    public String deleteProduct(@RequestParam(name = "userId", required = false) String userId,
                                @RequestParam(name = "productId", required = false) String productId, Model model) {
        if (storage.getUser(Integer.parseInt(userId)).getCurrentOrder() == null) {
            return getMenu(userId, model);
        }
        storage.deleteProduct(userId, productId);
        return getMenu(userId, model);
    }
}
