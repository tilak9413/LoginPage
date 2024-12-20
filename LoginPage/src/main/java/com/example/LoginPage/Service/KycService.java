package com.example.LoginPage.Service;

import com.example.LoginPage.Entity.KycEntity;
import com.example.LoginPage.Entity.LoginEntity;
import com.example.LoginPage.Model.CommonRespModal;
import com.example.LoginPage.Model.KycModel;
import com.example.LoginPage.Repository.KycRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Optional;

@Service
public class KycService {

    @Autowired
    private KycRepository kycRepository;

    // Fetch all KYC records
    public List<KycEntity> getAll() {
        return kycRepository.findAll();
    }
    @Transactional
    public CommonRespModal saveUserKyc(KycModel kycModel) {
        CommonRespModal response = new CommonRespModal();
        try {
            // Fetch the LoginEntity associated with the user
            Optional<KycEntity> loginEntityOpt = kycRepository.findById(kycModel.getId());
            if (loginEntityOpt.isEmpty()) {
                response.setMessage("User not found.");
                response.setStatus("FAILED");
                return response;
            }


             LoginEntity  loginEntity = new LoginEntity();

            KycEntity kycEntity = new KycEntity();
            kycEntity.setAadharCard(kycModel.getAadharCard());
            kycEntity.setPanCard(kycModel.getPanCard());
            kycEntity.setFullName(kycModel.getFullName());
            kycEntity.setAccountNumber(kycModel.getAccountNumber());


            kycRepository.save(kycEntity);

            response.setMessage("KYC details saved successfully.");
            response.setStatus("SUCCESS");
        } catch (OptimisticLockingFailureException e) {
            response.setMessage("Concurrency conflict. Please try again.");
            response.setStatus("FAILED");
            // Optionally, mark the transaction for rollback manually:
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setStatus("FAILED");
            // Mark the transaction for rollback manually:
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;  // Rethrow exception to ensure rollback
        }

        return response;
    }
    // Update KYC details
    public CommonRespModal updateKyc(KycModel kycModel) {
        CommonRespModal response = new CommonRespModal();

        Optional<KycEntity> findUser = kycRepository.findById(kycModel.getId());

        if (findUser.isEmpty()) {
            response.setMessage("User not available.");
            response.setStatus("FAILED");
        } else {
            KycEntity kycEntity = findUser.get();
            kycEntity.setAadharCard(kycModel.getAadharCard());
            kycEntity.setPanCard(kycModel.getPanCard());
            kycEntity.setFullName(kycModel.getFullName());

            kycRepository.save(kycEntity);

            response.setMessage("KYC details updated successfully.");
            response.setStatus("SUCCESS");
        }

        return response;
    }

    // Delete KYC user
    public CommonRespModal kycUserDelete(KycModel kycModel) {
        CommonRespModal response = new CommonRespModal();

        Optional<KycEntity> findUser = kycRepository.findById(kycModel.getId());

        if (findUser.isEmpty()) {
            response.setMessage("User not available.");
            response.setStatus("FAILED");
        } else {

            // Delete the user
            kycRepository.delete(findUser.get()); // Corrected this line
            response.setMessage("User deleted successfully.");
            response.setStatus("SUCCESS");
        }

        return response;
    }
}
