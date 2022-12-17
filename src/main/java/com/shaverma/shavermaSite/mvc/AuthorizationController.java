package com.shaverma.shavermaSite.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {
    @GetMapping("/gTA")
    public String getLogin()
    {
        return "Authorization";
}
}
