package com.shaverma.shavermaSite.mvc;

import com.shaverma.shavermaSite.models.product.Product;
import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.storage.ProductsLogic;
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
public class CurrentOrderController {
    Storage storage;

    @Autowired
    public CurrentOrderController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/CurrentOrder")
    public String getCurrentOrder(@RequestParam(name = "userId", required = false) String userId, Model model) {
        List<Product> listProducts = new ArrayList<>();
//        List<Integer> listCount = new ArrayList<>();
        for (Integer i : storage.getUser(Integer.parseInt(userId)).getCurrentOrder().getBasket().getBasket().keySet()) {
            listProducts.add(storage.getProduct(i));
//            listCount.add(storage.getUser(Integer.parseInt(userId)).getCurrentOrder().getBasket().getBasket().get(i));
        }
//        System.out.println(storage.getProductMap().values().stream().toList().toString());
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("basket",
                storage.getUser(Integer.parseInt(userId)).getCurrentOrder().getBasket());
        model.addAttribute("userId", userId);
        return "ordering/CurrentOrder";
    }

    @PostMapping("/addProductCurrentOrder")
    public String addProductCurrentOrder(@RequestParam(name = "userId", required = false) String userId,
                                         @RequestParam(name = "productId", required = false) String productId, Model model) {
        ProductsLogic.addProduct(userId, productId);
        return getCurrentOrder(userId, model);
    }

    @PostMapping("/deleteProductCurrentOrder")
    public String deleteProductCurrentOrder(@RequestParam(name = "userId", required = false) String userId,
                                            @RequestParam(name = "productId", required = false) String productId, Model model) {
        if (storage.getUser(Integer.parseInt(userId)).getCurrentOrder() == null) {
            return getCurrentOrder(userId, model);
        }
        ProductsLogic.deleteProduct(userId, productId);
        return getCurrentOrder(userId, model);
    }
}
