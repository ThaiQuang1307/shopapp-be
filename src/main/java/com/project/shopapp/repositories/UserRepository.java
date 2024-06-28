package com.project.shopapp.repositories;

import com.project.shopapp.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserModel> findByPhoneNumber(String phoneNumber);
}
