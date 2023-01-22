package com.shaverma.shavermaSite.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonalAccountController {
    @GetMapping("/PersonalAccount")
    public String getPersonalAccount(@RequestParam(name = "userId", required = false) String id, Model model){
        model.addAttribute("userId",id);
        return"PersonalAccount";
    }

}
