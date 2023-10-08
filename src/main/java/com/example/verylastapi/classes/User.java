package com.example.verylastapi.classes;


import com.example.verylastapi.classes.requests.CocktailRequest;
import com.example.verylastapi.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")

public class User implements UserDetails {
  @Id
  @GeneratedValue
  @org.springframework.data.annotation.Id
    private int id;
    private  String username;
    private String password;
    private  String email;
    private String phoneNumber;
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Token> Tokens;
    @OneToMany(mappedBy = "user")
    private List<Cocktail> cocktails;

  //GetUsername and GetPassword from UserDetails is implemented by @Data in generated getter
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority((role.name())));
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
    return true;
  }
}
