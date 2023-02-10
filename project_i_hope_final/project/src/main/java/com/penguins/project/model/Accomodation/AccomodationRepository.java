package com.penguins.project.model.Accomodation;

import com.penguins.project.model.Arrangement.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
    @Transactional
    @Modifying
    @Query("delete from Accomodation a where ?1 member of a.arrangements")
    int deleteByArragements(Arrangement arranggement);

}
