package hes.fintech.controller;

import hes.fintech.domain.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class ViewController {
    @GetMapping("/{user}")
    public String viewUser(@PathVariable UserAccount user, Map<String, Object> model)
    {
        model.put("user", user);
        return "view";
    }
}
