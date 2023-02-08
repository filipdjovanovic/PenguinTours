package com.penguins.project.model.Arrangement;

import com.penguins.project.model.Program.Program;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement,Long> {
    @Transactional
    @Modifying
    @Query("delete from Arrangement a where a.programs = ?1")
    int deleteByPrograms(Program programs);
    @Transactional
    @Modifying
    @Query("update Arrangement a set a.programs = ?1")
    int updateProgramsBy(Program programs);
    @Transactional
    @Modifying
    @Query("update Arrangement a set a.programs = ?1 where a.programs = ?2")
    void updateProgramsByPrograms(Program programs, Program programs1);


    /*
    KONACNA, OVA RADI
    @Query(value ="SELECT a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a, (SELECT t.arrangement_id, t.location_id, t.city, t.country,\n" +
            "t.continent from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t where t.location_id = (SELECT t2.location_id from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id;\n" ,nativeQuery = true)

     */

    /*
    @Query(value ="CREATE VIEW full AS select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id; \n" +
            "SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date " +
            "from arrangement a, " +
            "(SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent " +
            "from full as t where t.location_id = (SELECT t2.location_id from full as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id;" , nativeQuery = true)


     */

        /*
    SELECT t.arrangement_id, t.location_id, COUNT(*) as c from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id =
    p.id and pl.location_id = l.id) as t where t.location_id = (SELECT t2.location_id from (select arrangement_id, program_id, location_id, city, continent, country, picture from program p join program_location pl join location l where pl.program_id = p.id and pl.location_id = l.id) as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id;
    */


    @Query(value ="SELECT a FROM Arrangement a WHERE a.id = :id")
    Arrangement getArrangementById(Long id);
    @Query(value = "SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a,(SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from full as t where t.location_id = (SELECT t2.location_id from full as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id and a.id in (select arrangement_id from full as f where f.city=:city) GROUP BY a.id;", nativeQuery = true)
    List<Object[]> findArrangementByCity(String city,Pageable pageable);

    @Query(value = "SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a,(SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from full as t where t.location_id = (SELECT t2.location_id from full as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id and a.id in (select arrangement_id from full as f where f.country=:country) GROUP BY a.id;", nativeQuery = true)
    List<Object[]> findArrangementByCountry(String country,Pageable pageable);

    @Query(value = "SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a,(SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from full as t where t.location_id = (SELECT t2.location_id from full as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id and a.id in (select arrangement_id from full as f where f.continent=:continent) GROUP BY a.id;", nativeQuery = true)
    List<Object[]> findArrangementByContinent(String continent,Pageable pageable);

    @Query(value = "SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a,(SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from full as t where t.location_id = (SELECT t2.location_id from full as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id HAVING start_date = :startDate", nativeQuery = true)
    List<Object[]> findArrangementByStartDate(Date startDate,Pageable pageable);

    @Query(value = "SELECT a.id, a.name, a.price, a.transportation, tab.city, tab.country, tab.continent, count(p.id) as number_of_days, min(date) as start_date, max(date) as end_date from arrangement a,(SELECT t.arrangement_id, t.location_id, t.city, t.country, t.continent from full as t where t.location_id = (SELECT t2.location_id from full as t2 where t2.arrangement_id = t.arrangement_id GROUP BY t2.location_id ORDER BY COUNT(*) DESC LIMIT 1) GROUP BY t.arrangement_id, t.location_id) as tab, program p where a.id = tab.arrangement_id AND a.id = p.arrangement_id GROUP BY a.id HAVING start_date >= :startDate and end_date <= :endDate", nativeQuery = true)
    List<Object[]> findArrangementByStartDateAndEndDate(Date startDate, Date endDate,Pageable pageable);

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
