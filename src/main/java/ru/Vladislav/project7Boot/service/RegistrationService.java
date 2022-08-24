package ru.Vladislav.project7Boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Vladislav.project7Boot.model.Admin;
import ru.Vladislav.project7Boot.repository.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Admin person){
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        peopleRepository.save(person);
    }
}
