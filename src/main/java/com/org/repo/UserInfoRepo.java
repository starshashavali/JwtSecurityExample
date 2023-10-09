package com.org.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}