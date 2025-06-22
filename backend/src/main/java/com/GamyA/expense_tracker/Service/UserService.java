package com.GamyA.expense_tracker.Service;

import com.GamyA.expense_tracker.DTO.UserLogIn;
import com.GamyA.expense_tracker.DTO.UserRegister;
import com.GamyA.expense_tracker.Entities.UserInfo;
import com.GamyA.expense_tracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authManager;
    private JWTService jwtService;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public void registerUser(UserRegister userRegister){
        userRepo.save(
          new UserInfo(
                  passwordEncoder.encode(userRegister.getPassword()),
                  userRegister.getUsername()
                  )
        );
    }

    public String AuthAndGetToken(UserLogIn userLogIn){
        try{
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogIn.getUsername(), userLogIn.getPassword())
            );
            UserInfo userInfo = userRepo.findByUsername(userLogIn.getUsername()).orElseThrow();
            return jwtService.generateToken(userInfo);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    }
}
