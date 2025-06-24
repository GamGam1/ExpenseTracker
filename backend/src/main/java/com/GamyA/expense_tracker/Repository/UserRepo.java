package com.GamyA.expense_tracker.Repository;

import com.GamyA.expense_tracker.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepo extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);
}
