package com.example.rtilogin.repository;

import com.example.rtilogin.entity.NetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NetUserRepository extends JpaRepository<NetUser, Long> {
    Optional<NetUser> findByUserName(String userName);
}
