package hes.fintech.controller;

import hes.fintech.domain.UserAccount;
import hes.fintech.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class EditController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/{user}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEdit(@PathVariable UserAccount user, Map<String, Object> model) {
        model.put("user", user);
        return "edit";
    }
    @PostMapping("/{user}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveUser(@PathVariable UserAccount user, @RequestParam String firstname,
                           @RequestParam String lastname, @RequestParam String username,
                           @RequestParam(name = "Status",required = false,defaultValue = "ACTIVE") String status,
                           @RequestParam(name = "Role") String role)
    {
        userAccountService.editUserAccount(user, firstname, lastname, username,
                status, role);
        return "redirect:/user";
    }
}
