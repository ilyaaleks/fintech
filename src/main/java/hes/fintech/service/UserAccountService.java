package hes.fintech.service;

import hes.fintech.domain.Role;
import hes.fintech.domain.Status;
import hes.fintech.domain.UserAccount;
import hes.fintech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAccountService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserAccount editUserAccount(UserAccount userAccount, String firstname,
                                       String lastname, String username,
                                       String status, String role)
    {
        userAccount.editUser(username,firstname,lastname, Role.valueOf(role), Status.valueOf(status));
        return userRepository.save(userAccount);
    }
    public UserAccount saveUserAccount(UserAccount userAccount)
    {
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        userAccount.setCreatedAt(new Date());
        return userRepository.save(userAccount);
    }
}
