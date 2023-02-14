package com.penguins.project.model.Accomodation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {

}
