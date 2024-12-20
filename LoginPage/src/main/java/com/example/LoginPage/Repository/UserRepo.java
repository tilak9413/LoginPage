package com.example.LoginPage.Repository;
import com.example.LoginPage.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<LoginEntity, Integer> {

    @Query("SELECT l.userName AS userName, l.password AS password, k.fullName AS kycFullName, k.aadharCard AS aadharCard " +
            "FROM LoginEntity l " +
            "JOIN l.kycEntities k " +
            "WHERE l.userId = :userId")
    List<Map<String, Object>> getLoginWithKyc(@Param("userId") Integer userId);
    Optional<LoginEntity> findById(Integer userId);
}
