package com.example.LoginPage.Repository;

import com.example.LoginPage.Entity.KycEntity;

import com.example.LoginPage.Entity.LoginEntity;
import com.example.LoginPage.Model.KycModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KycRepository  extends JpaRepository<KycEntity  , Integer> {
//    List<KycEntity> findById(KycModel kycModel);

}


