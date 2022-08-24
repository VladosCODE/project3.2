package ru.Vladislav.project7Boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vladislav.project7Boot.model.Book;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBooksByPerson_Id(int person_id_user);
    Book findBookByAuthor(String author);
    List<Book> findBooksByNameStartsWith(String nameStartsWidth);
}
