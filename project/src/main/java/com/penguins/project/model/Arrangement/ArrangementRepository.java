package com.penguins.project.model.Arrangement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement,Long> {
    @Query(value ="SELECT a FROM Arrangement a WHERE a.id = :id")
    Arrangement getArrangementById(Long id);


}
