package com.penguins.project.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("""
            select p from Person p
            where p.name = ?1 and p.lastName = ?2 and p.email = ?3 and p.contact = ?4 and p.address = ?5""")
    List<Person> findByNameAndLastNameAndEmailAndContactAndAddress(String name, String lastName, String email, String contact, String address);

}
