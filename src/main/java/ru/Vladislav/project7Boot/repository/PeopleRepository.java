package ru.Vladislav.project7Boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vladislav.project7Boot.model.Admin;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Admin,Integer> {
    Admin findAdminByLogin(String login);
}
