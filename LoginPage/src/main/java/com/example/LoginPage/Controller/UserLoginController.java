package com.example.LoginPage.Controller;

import com.example.LoginPage.Model.CommonRespModal;
import com.example.LoginPage.Model.LoginModelUser;
import com.example.LoginPage.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private final LoginService loginService;

    public UserLoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public CommonRespModal userLogin(@RequestBody LoginModelUser loginModelUser) {
        return loginService.loginUser(loginModelUser);
    }

    @PutMapping("/updateUser")
    public CommonRespModal updateUser(@RequestBody LoginModelUser loginModelUser) {
        return loginService.updateUserDetails(loginModelUser);
    }

    @DeleteMapping("/deleteUser")
    public CommonRespModal deleteUser(@RequestBody LoginModelUser loginModelUser) {
        return loginService.deleteUser(loginModelUser);
    }
}
