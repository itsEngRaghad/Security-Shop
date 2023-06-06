package com.example.security1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;


    private String username;
    private String password;
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
        //every time generate authority depending on role they give me by this.role
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;//changed to true
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
        return true;
    }



    //------------------Relation-------------------------//

    //define one-many relation with blogs
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "myUser")
    private Set<MyUser> myUsers;

}
