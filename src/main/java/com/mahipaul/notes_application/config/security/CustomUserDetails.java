package com.mahipaul.notes_application.config.security;

import com.mahipaul.notes_application.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(com.mahipaul.notes_application.model.User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }


}
