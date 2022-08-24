package ru.Vladislav.project7Boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.Vladislav.project7Boot.model.Admin;
import ru.Vladislav.project7Boot.repository.PeopleRepository;
import ru.Vladislav.project7Boot.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin person = peopleRepository.findAdminByLogin(username);
        if(person == null)
            throw new UsernameNotFoundException("User not found");
        System.out.println(person);
        return new PersonDetails(person);
    }
}
