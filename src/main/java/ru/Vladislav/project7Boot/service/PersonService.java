package ru.Vladislav.project7Boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Vladislav.project7Boot.model.Book;
import ru.Vladislav.project7Boot.model.Person;
import ru.Vladislav.project7Boot.repository.BookRepository;
import ru.Vladislav.project7Boot.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }


    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }
    public Person findOne(String fio){
        return personRepository.findPersonByFio(fio);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Person show_have_person(int id_book) {
        Book book = bookRepository.findById(id_book).orElse(null);
        return personRepository.findPersonByBookListContains(book);
    }

}
