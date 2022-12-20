package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
//    private final BookValidator bookValidator;



    @ GetMapping
    public String index(Model model) {
        //Получим все книги из DAO и передадим в представление
        model.addAttribute("books",bookDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //Получим книгу из DAO по id и передадим в представление
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people",personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
//        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book) {

//        personValidator.validate(person,bindingResult);
//        if (bindingResult.hasErrors()) return "people/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));

        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book,
						 @ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
//        if (bindingResult.hasErrors()) return "people/edit";
		if (person != null) {
			bookDAO.assign(id, person);
			return "redirect:/books/{id}";
		}
        bookDAO.update(id, book);
        return "redirect:/books";
    }

   /* @GetMapping("/{id}")
    public String assign(@ModelAttribute("person") Person person, @PathVariable("id") int id, Model model) {
        model.addAttribute("people",personDAO.index());
        model.addAttribute("book",bookDAO.show(id));
        bookDAO.assign(id, person);
        return "redirect:/books/{id}";
    }*/

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
