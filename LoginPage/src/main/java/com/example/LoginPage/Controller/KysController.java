package com.example.LoginPage.Controller;
import com.example.LoginPage.Entity.KycEntity;
import com.example.LoginPage.Model.KycModel;
import com.example.LoginPage.Service.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kyc")
public class KysController {

   @Autowired
   private KycService kycService;


   @GetMapping
 public List<KycEntity> GetAll ()
 {
     return  kycService.getAll();
 }
@PostMapping
 public  void  AddKyc(@RequestBody KycModel kycModel)
 {
     kycService.saveUserKyc(kycModel);
 }

 @PutMapping
   public  void PutDataKyc ( @RequestBody  KycModel kycModel )
 {
     kycService.updateKyc(kycModel);
 }

// @DeleteMapping
//    public  void  kycUserDelete ( @RequestBody KycModel kycModel )
// {
//     kycService.kycUserDelete(kycModel);
// }


}
