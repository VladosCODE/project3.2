package ru.Vladislav.project7Boot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.Vladislav.project7Boot.model.Admin;
import ru.Vladislav.project7Boot.service.RegistrationService;
import ru.Vladislav.project7Boot.util.AdminValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AuthController {


    private final AdminValidator adminValidator;

    private final RegistrationService registrationService;

    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthController(AdminValidator adminValidator,
                          RegistrationService registrationService, AuthenticationManager authenticationManager) {
        this.adminValidator = adminValidator;
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("admin") Admin admin){
        return "auth/registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("admin") @Valid Admin admin, BindingResult bindingResult){

        adminValidator.validate(admin,bindingResult);
        if (bindingResult.hasErrors()){
            return "auth/registration";
        }
        registrationService.register(admin);
        return "redirect:login";
    }
}
