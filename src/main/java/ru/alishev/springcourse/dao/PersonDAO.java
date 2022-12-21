package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//Используем свой RowMapper (хотя можно стандартный)
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int person_id) {
        return jdbcTemplate.query(
                "SELECT * FROM Person WHERE person_id=?",new Object[]{person_id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String name) {
        return jdbcTemplate.query(
                        "SELECT * FROM Person WHERE name=?",new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name,year_of_birth) VALUES(?, ?)",
                person.getName(), person.getYear_of_birth());
    }
    public void update(int person_id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, year_of_birth=? WHERE person_id=?",
                person.getName(),person.getYear_of_birth(),person_id);
    }
    public void delete(int person_id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }

}
