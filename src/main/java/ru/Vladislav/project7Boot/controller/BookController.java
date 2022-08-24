package ru.Vladislav.project7Boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Vladislav.project7Boot.model.Book;
import ru.Vladislav.project7Boot.model.Person;
import ru.Vladislav.project7Boot.service.BookService;
import ru.Vladislav.project7Boot.service.PersonService;
import ru.Vladislav.project7Boot.util.BookValidator;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/book")
public class BookController {
    private final PersonService personService;
    private final BookService bookService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(PersonService personService, BookService bookService, BookValidator bookValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(@RequestParam(value = "page",required = false,defaultValue="0") int page,
                        @RequestParam(value = "itemsPerPage",required = false,defaultValue="0") int itemsPerPage,
                        @RequestParam(value = "sort_by_year",required = false) boolean sort_by_year,Model model) {

        if ((page == 0) && (itemsPerPage == 0)){
            model.addAttribute("book", bookService.findAll());
        }
        else if (sort_by_year==true){
            model.addAttribute("book", bookService.findAll(page,itemsPerPage,"year"));
        }
        else{
            model.addAttribute("book", bookService.findAll(page,itemsPerPage));}
        return "book/index_book";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "book/new";
        book.setTime(new Date());
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("personNew") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        System.out.println(bookService.findOne(id).getPerson());
        model.addAttribute("person", bookService.findOne(id).getPerson());
        model.addAttribute("list", personService.findAll());
        return "book/show";
    }

    @PatchMapping("/add/{id}")
    public String addHavePerson(@PathVariable("id") int id_book, @ModelAttribute("personNew") Person person) {
        bookService.addHavePerson(id_book, person);
        return "redirect:/book";
    }

    @DeleteMapping("/release/{id}")
    public String deleteHavePerson(@PathVariable("id") int id) {
        bookService.deleteHavePerson(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "book/edit";
        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }
    @GetMapping("/search")
    public String search_book(Model model){
        model.addAttribute("BeforeSearch",true);
        return "book/search";
    }
    @PostMapping("/search")
    public String show_found_books(@RequestParam("name") String name, Model model){
        model.addAttribute("BeforeSearch",false);
        model.addAttribute("books",bookService.findBooksByNameStartsWith(name));
        return "book/search";
    }
}
