package hes.fintech.repository;

import hes.fintech.domain.Role;
import hes.fintech.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserAccount, Integer> {
    UserAccount findByUsername(String username);
    Page<UserAccount> findByRole(Pageable pageable, Role role);
    Page<UserAccount> findAll(Pageable pageable);
}
