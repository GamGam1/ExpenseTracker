package com.GamyA.expense_tracker.Service;

import com.GamyA.expense_tracker.Entities.UserInfo;
import com.GamyA.expense_tracker.Entities.UserPrincipal;
import com.GamyA.expense_tracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return new UserPrincipal(user);
    }
}
