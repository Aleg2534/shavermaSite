package com.shaverma.shavermaSite.mvc.identificationControllers;

import com.shaverma.shavermaSite.models.user.User;
import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthorizationController {
    Storage storage;

    @Autowired
    public AuthorizationController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/gTA")
    public String getAuthorization() {
        return "identification/Authorization";
    }

    @PostMapping("/gTA")
    public String postLoginAndPassword(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
                                       Model model) {
        User user =storage.checkLoginAndPasswordUser(email, password);
        if (user!=null) {
            int userId=user.getClassId();
//            atrr.addFlashAttribute("userId",userId);
            return "redirect:/Menu?userId="+userId;
        }

        model.addAttribute("error", "Wrong login or password");
        return "identification/Authorization";
    }
}
