package com.penguins.project.model.Program;

import com.penguins.project.model.Arrangement.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    long deleteByArrangement(Arrangement arrangement);
}
