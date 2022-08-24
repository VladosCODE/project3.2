package ru.Vladislav.project7Boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Vladislav.project7Boot.model.Book;
import ru.Vladislav.project7Boot.model.Person;
import ru.Vladislav.project7Boot.repository.BookRepository;
import ru.Vladislav.project7Boot.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }
    public List<Book> findAll(int page, int itemPerPage){
        return bookRepository.findAll(PageRequest.of(page,itemPerPage)).getContent();
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public List<Book> findAll(int page, int itemPerPage,String s){
        return bookRepository.findAll(PageRequest.of(page,itemPerPage, Sort.by("year_release"))).getContent();
    }
    public Book findOne(int id){
        Optional<Book> foundPerson = bookRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save(Book book1){
        bookRepository.save(book1);
    }

    @Transactional
    public void update(int id,Book updateBook){
        updateBook.setId_book(id);
        bookRepository.save(updateBook);
    }

    public List<Book> findBooksByNameStartsWith(String nameStartsWidth){
        return bookRepository.findBooksByNameStartsWith(nameStartsWidth);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public List<Book> show_books_user(int id){
        return bookRepository.findBooksByPerson_Id(id);
    }
    @Transactional
    public void deleteHavePerson(int id){
        Book book = findOne(id);
        System.out.println(book.getPerson());
        book.getPerson().getBookList().remove(book);
        book.setPerson(null);
    }
    @Transactional
    public void addHavePerson(int id_book, Person person){
        Book book = findOne(id_book);
        book.setTime(new Date());
        if (person.getBookList() == null){
            person.setBookList(new ArrayList<>());
        }
        person.getBookList().add(book);
        book.setPerson(person);
    }
    public Book findOne(String fio){
        return bookRepository.findBookByAuthor(fio);
    }
}
