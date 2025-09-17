package com.aps2ArqObj.APS2.Controllers;

import com.aps2ArqObj.APS2.Models.User;
import com.aps2ArqObj.APS2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        String token = userService.register(user);
        return Map.of("token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");
        String token = userService.login(email, senha);
        return Map.of("token", token);
    }
}

