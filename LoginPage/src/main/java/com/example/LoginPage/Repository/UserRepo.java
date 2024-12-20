package com.example.LoginPage.Repository;

import com.example.LoginPage.Entity.LoginEntity;
import com.example.LoginPage.Model.LoginModelUser;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<LoginEntity, Integer> {
    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT u FROM LoginEntity u WHERE u.userId = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<LoginEntity> findById(Integer userId);
}
