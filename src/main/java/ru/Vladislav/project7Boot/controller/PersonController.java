package ru.Vladislav.project7Boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Vladislav.project7Boot.model.Person;
import ru.Vladislav.project7Boot.service.BookService;
import ru.Vladislav.project7Boot.service.PersonService;
import ru.Vladislav.project7Boot.util.PersonValidator;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final BookService bookService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, BookService bookService, PersonValidator personValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personService.findAll());
        return "person/index_person";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personService.findOne(id));
        model.addAttribute("book",bookService.show_books_user(id));
        model.addAttribute("date",new Date());
        return "person/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "person/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person people, BindingResult bindingResult){
        personValidator.validate(people,bindingResult);
        if (bindingResult.hasErrors())
            return "person/new";
        personService.save(people);
        return "redirect:/person";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",personService.findOne(id));
        return "person/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors())
            return "person/edit";
        personService.update(id,person);
        return "redirect:/person";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:/person";
    }

}
