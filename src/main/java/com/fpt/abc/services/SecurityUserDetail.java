package com.fpt.abc.services;

import com.fpt.abc.pojos.AccountMember;
import com.fpt.abc.repos.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SecurityUserDetail implements UserDetailsService {
    @Autowired
    private IAccountRepo iAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountMember account = iAccountRepo.getAccountMemberByEmailAddress(email);
        var springUser = org.springframework.security.core.userdetails.User.withUsername(email)
                .password(account.getMemberPassword())
                .roles(account.getMemberRole() == 1 ? "ADMIN" : "USER");
        return springUser.build();
    }

}
