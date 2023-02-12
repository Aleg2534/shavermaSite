package com.shaverma.shavermaSite.mvc;

import com.shaverma.shavermaSite.utils.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonalAccountController {
    Storage storage;

    @Autowired
    public PersonalAccountController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/PersonalAccount")
    public String getPersonalAccount(@RequestParam(name = "userId", required = false) String userId, Model model) {
        String login = storage.getUser(Integer.parseInt(userId)).getLogin();
        String password = storage.getUser(Integer.parseInt(userId)).getPassword();
        String email = storage.getUser(Integer.parseInt(userId)).getEmailAddress();
        model.addAttribute("userId", userId);
        model.addAttribute("login", login);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "PersonalAccount";
    }

    @PostMapping("/PersonalAccount")
    public String postPersonalAccount(@RequestParam(name = "userId") String userId,
                                      @RequestParam(name = "login", required = false) String login,
                                      @RequestParam(name = "email", required = false) String email,
                                      @RequestParam(name = "password", required = false) String password, Model model) {
        if ((login != null)&&!("".equals(login))) {
            storage.getUser(Integer.parseInt(userId)).setLogin(login);
        }
        if ((email != null)&&!("".equals(email))) {
            storage.getUser(Integer.parseInt(userId)).setEmailAddress(email);
        }
        if ((password != null)&&!("".equals(password))) {
            storage.getUser(Integer.parseInt(userId)).setPassword(password);
        }
        login = storage.getUser(Integer.parseInt(userId)).getLogin();
        password = storage.getUser(Integer.parseInt(userId)).getPassword();
        email = storage.getUser(Integer.parseInt(userId)).getEmailAddress();
        model.addAttribute("userId", userId);
        model.addAttribute("login", login);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "PersonalAccount";
    }
}