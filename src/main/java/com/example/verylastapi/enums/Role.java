package com.example.verylastapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
@AllArgsConstructor
@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            Permissions.ADMIN_CREATE,
            Permissions.ADMIN_READ,
            Permissions.ADMIN_UPDATE,
            Permissions.ADMIN_DELETE,
            Permissions.MANAGER_CREATE,
            Permissions.MANAGER_DELETE,
            Permissions.MANAGER_UPDATE,
            Permissions.MANAGER_READ,
            Permissions.USER_CREATE,
            Permissions.USER_READ,
            Permissions.USER_UPDATE,
            Permissions.USER_DELETE
    )),
    MANAGER(Set.of(
            Permissions.MANAGER_CREATE,
            Permissions.MANAGER_DELETE,
            Permissions.MANAGER_UPDATE,
            Permissions.MANAGER_READ,
            Permissions.USER_CREATE,
            Permissions.USER_READ,
            Permissions.USER_UPDATE,
            Permissions.USER_DELETE
    )),
    USER(Set.of(
            Permissions.USER_CREATE,
            Permissions.USER_READ,
            Permissions.USER_UPDATE,
            Permissions.USER_DELETE
    ));
    @Getter
    public Set<Permissions> permissions;
    public List<SimpleGrantedAuthority> getAuthorities()
    {
        List<SimpleGrantedAuthority> authorities= new java.util.ArrayList<>(getPermissions()
                .stream()
                .map(x -> new SimpleGrantedAuthority(x.name()))
                .toList());//Added permissions to our list of authorities
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));//added name of role to tell spring about role name by using prefix "ROLE_"
        return authorities;
    }

}
