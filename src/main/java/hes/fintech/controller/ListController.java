package hes.fintech.controller;

import hes.fintech.domain.Role;
import hes.fintech.domain.UserAccount;
import hes.fintech.repository.UserRepository;
import hes.fintech.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
public class ListController {
    @Autowired
    private UserRepository userRepository;



    @GetMapping
    public String getUserList(
            @AuthenticationPrincipal UserAccount userAccount,
            Map<String, Object> model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserAccount> usersPage = userRepository.findAll(pageable);
        return putModel(userAccount, model, usersPage);
    }
    @GetMapping("/filter")
    public String searchByUsername(@RequestParam String username,
                                   @AuthenticationPrincipal UserAccount userAccount,
                                   Map<String, Object> model)
    {
        UserAccount user=userRepository.findByUsername(username);
        model.put("currentUser", userAccount);
        model.put("isAdmin",userAccount.isAdmin());
        model.put("users", user);
        model.put("totalPage",1);
        return "list";
    }
    @GetMapping("/roleFilter")
    public String searchByRole(@RequestParam String role,
                               @AuthenticationPrincipal UserAccount userAccount,
                               Map<String, Object> model,@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<UserAccount> usersPage = userRepository.findByRole(pageable,Role.valueOf(role));
        return putModel(userAccount, model, usersPage);
    }

    private String putModel(UserAccount userAccount, Map<String, Object> model, Page<UserAccount> usersPage) {
        model.put("currentUser", userAccount);
        model.put("isAdmin",userAccount.isAdmin());
        model.put("users", usersPage.getContent());
        List<Integer> range= IntStream.range(0,usersPage.getTotalPages()).boxed().collect(Collectors.toList());
        model.put("totalPage",range);
        return "list";
    }
}
