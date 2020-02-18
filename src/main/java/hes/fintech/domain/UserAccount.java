package hes.fintech.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class UserAccount implements UserDetails {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Please, fill username")
    @Column(unique=true)
    @Length(max = 16,min = 3,message = "To long or short")
    private String username;
    @NotBlank(message = "Please, fill password")
    private String password;
    @NotBlank(message = "Please, fill firstname")
    @Length(max = 16,min = 1,message = "To long or short")
    private String firstname;
    @NotBlank(message = "Please, fill lastname")
    @Length(max = 16,min = 1,message = "To long or short")
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date createdAt;

    public UserAccount(String username, String password, String firstname, String lastname, Role role, Status status, Date createdAt) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }
    public void editUser(String username, String firstname, String lastname, Role role, Status status)
    {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }
    public UserAccount() {
    }
    public boolean isAdmin()
    {
        boolean result=this.role==Role.USER;
        return result;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status==Status.ACTIVE;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(getRole().name()));
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
