package com.penguins.project.model.Arrangement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement,Long> {

    @Query(value ="SELECT a FROM Arrangement a WHERE a.id = :id")
    Arrangement getArrangementById(Long id);


    @Query(value ="SELECT a.id, a.name, a.price, a.transportation, a.status, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a, (SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t where t.location_id = (SELECT t2.location_id from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id ORDER BY a.status;"
            ,countQuery = "SELECT COUNT(*) from arrangement a, (SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t where t.location_id = (SELECT t2.location_id from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id ORDER BY a.status;",nativeQuery = true)
    Page<Object[]> findAllArrangements(Pageable pageable);

    @Query(value ="SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date " +
            "from arrangement a, (SELECT t.arrangement_id, t.location_id, t.city, t.country,\n" +
            "t.continent from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t where t.location_id = (SELECT t2.location_id from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id HAVING a.name REGEXP :name" ,nativeQuery = true)
    List<Object[]> findArrangementByName(String name, Pageable pageable);

    @Query(value ="SELECT a.id, a.name, a.price, a.transportation, a.status, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a, (SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t where t.location_id = (SELECT t2.location_id from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id HAVING a. status = 'Dostupno' ORDER BY a.status, start_date LIMIT 6", nativeQuery = true)
    List<Object[]> findTopArrangements();


}
