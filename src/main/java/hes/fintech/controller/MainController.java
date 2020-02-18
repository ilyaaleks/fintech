package hes.fintech.controller;

import hes.fintech.domain.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping
    public String login(Map<String,Object> model)
    {
        return "login";
    }

//    @PostMapping
//    public String addUser(UserAccount userAccount,Map<String,Object> model) {
//
//        return "redirect:/list";
//    }
}
