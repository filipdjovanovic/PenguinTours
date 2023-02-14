package com.penguins.project.model.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select l from Location l where l.city = ?1 and l.country = ?2 and l.continent = ?3")
    List<Location> findByCityAndCountryAndContinent(String city, String country, String continent);



}
