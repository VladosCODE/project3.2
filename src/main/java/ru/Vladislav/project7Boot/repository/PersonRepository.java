package ru.Vladislav.project7Boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vladislav.project7Boot.model.Book;
import ru.Vladislav.project7Boot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findPersonByBookListContains(Book book);
    Person findPersonByFio(String fio);

}
