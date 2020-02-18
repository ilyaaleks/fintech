package hes.fintech.controller;

import hes.fintech.domain.UserAccount;
import hes.fintech.repository.UserRepository;
import hes.fintech.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class NewController {
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    @Qualifier("personValidator") // spring validator
    private Validator personValidator;

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String newUser()
    {

        return "new";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(
            @Valid UserAccount userAccount,BindingResult bindingResult, Map<String, Object> model) {
        personValidator.validate(userAccount, bindingResult);
        if(bindingResult.hasErrors())
        {
            List<String> errors= bindingResult.getFieldErrors().stream().map((el)->el.getField()+" Error")
                    .collect(Collectors.toList());
            model.put("error",errors);
            model.put("hasError",true);
            return "new";
        }else {
            userAccountService.saveUserAccount(userAccount);
            return "redirect:/user";
        }
    }
}
