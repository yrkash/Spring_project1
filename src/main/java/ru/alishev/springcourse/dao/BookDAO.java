package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> showBooksForPerson(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int book_id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Book WHERE book_id=?",new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public Optional<Book> show(String name) {
        return jdbcTemplate.query(
                        "SELECT * FROM Book WHERE name=?",new Object[]{name}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_writing) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear_of_writing());
    }
    public void update(int book_id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_writing=? WHERE book_id=?",
                book.getName(),book.getAuthor(), book.getYear_of_writing(), book_id);
    }

    public void assign(int book_id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",
                person.getPerson_id(), book_id);
    }
    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);
    }


}
