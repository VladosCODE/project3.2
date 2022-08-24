package ru.Vladislav.project7Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.Vladislav.project7Boot.model.Admin;
import ru.Vladislav.project7Boot.model.Person;
import ru.Vladislav.project7Boot.service.PersonDetailsService;

@Component
public class AdminValidator implements Validator {

    private PersonDetailsService personDetailsService;

    @Autowired
    public AdminValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        try{
            personDetailsService.loadUserByUsername(admin.getLogin());}
        catch(UsernameNotFoundException ignored){return;}
        errors.rejectValue("login","","Человек с таким именем уже существует");
    }
}
