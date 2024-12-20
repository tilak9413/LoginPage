package com.example.LoginPage.Service;

import com.example.LoginPage.Entity.KycEntity;
import com.example.LoginPage.Entity.LoginEntity;
import com.example.LoginPage.Model.CommonRespModal;
import com.example.LoginPage.Model.LoginModelUser;
import com.example.LoginPage.Repository.KycRepository;
import com.example.LoginPage.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

  private final UserRepo userRepo;
  private final KycRepository kycRepository;

  @Autowired
  public LoginService(UserRepo userRepo, KycRepository kycRepository) {
    this.userRepo = userRepo;
    this.kycRepository = kycRepository;
  }

  public CommonRespModal loginUser(LoginModelUser loginModelUser) {
    CommonRespModal response = new CommonRespModal();
    try {
      // Validate input
      if (loginModelUser.getUserName() == null || loginModelUser.getPassword() == null) {
        response.setStatus("FAILED");
        response.setMessage("Username or Password cannot be null.");
        return response;
      }

      // Save user details
      LoginEntity loginEntity = new LoginEntity();
      loginEntity.setUserName(loginModelUser.getUserName());
      loginEntity.setPassword(loginModelUser.getPassword());
      loginEntity.setUserId(loginModelUser.getUserId());
      LoginEntity savedUser = userRepo.save(loginEntity);

      KycEntity kycEntity = new KycEntity();
      kycEntity.setFullName(loginModelUser.getUserName());
      kycEntity.setLoginEntity(savedUser);
      kycRepository.save(kycEntity);

      response.setStatus("SUCCESS");
      response.setMessage("User logged in successfully and KYC data saved.");
    } catch (Exception e) {
      response.setStatus("ERROR");
      response.setMessage("An error occurred: " + e.getMessage());
    }
    return response;
  }

  @Transactional
  public CommonRespModal updateUserDetails(LoginModelUser updateModel) {
    CommonRespModal response = new CommonRespModal();

    Optional<LoginEntity> existingUser = userRepo.findById(updateModel.getUserId());
    if (existingUser.isEmpty()) {
      response.setStatus("FAILED");
      response.setMessage("User not found.");
      return response;
    }

    // Update user details
    LoginEntity user = existingUser.get();
    if (updateModel.getUserName() != null) {
      user.setUserName(updateModel.getUserName());
    }
    if (updateModel.getPassword() != null) {
      user.setPassword(updateModel.getPassword());
    }

    try {
      userRepo.save(user);
      response.setStatus("SUCCESS");
      response.setMessage("User details updated successfully.");
    } catch (Exception e) {
      response.setStatus("ERROR");
      response.setMessage("An error occurred: " + e.getMessage());
      throw e;
    }

    return response;
  }

  public CommonRespModal deleteUser(LoginModelUser loginModelUser) {
    CommonRespModal response = new CommonRespModal();
    Optional<LoginEntity> userToDelete = userRepo.findById(loginModelUser.getUserId());

    if (userToDelete.isEmpty()) {
      response.setMessage("User not found.");
      response.setStatus("FAILED");
      return response;
    }

    userRepo.delete(userToDelete.get());
    response.setMessage("User deleted successfully.");
    response.setStatus("SUCCESS");
    return response;
  }


  public List<Map<String, Object>> getLoginWithKycDetails(Integer userId) {
    return userRepo.getLoginWithKyc(userId);
  }
}
