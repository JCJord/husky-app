package com.HuskyBank.HuskyBank.api.repository;

import com.HuskyBank.HuskyBank.api.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCpf(BigInteger cpf);
}
