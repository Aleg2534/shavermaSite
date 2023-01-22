package com.shaverma.shavermaSite.mvc;

import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MenuController {
    Storage storage;
    @GetMapping("/Menu")
    public String getMenu(@RequestParam(name = "userId", required = false) String id, Model model) {
        System.out.println(id);
        model.addAttribute("userId",id);
        return "Menu";
    }
}
