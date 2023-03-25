package com.shaverma.shavermaSite.mvc.identificationControllers;

import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    Storage storage;

    @Autowired
    public RegistrationController(Storage storage) {
        this.storage = storage;
    }


    @GetMapping("/Registration")
    public String getRegistration() {
        return "identification/Registration";
    }

    @PostMapping("/Registration")
    public String postRegistration(@RequestParam(name = "login") String login,
                                   @RequestParam(name = "email") String email,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "repeat_password") String repeatPassword,
                                   Model model) {
        if (!password.equals(repeatPassword)) {
            model.addAttribute("error", "password and repeatPassword don't equal");
            return "identification/Registration";
        } else if (storage.checkEmailUser(email)) {
            model.addAttribute("error", "user with this email already exists");
            return "identification/Registration";
        }
        int userId = storage.registrationUser(login, email, password);
        model.addAttribute("userId",userId);
        return "redirect:/Menu";
    }
}
