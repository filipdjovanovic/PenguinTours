package com.penguins.project.service;

import com.penguins.project.model.Arrangement.Arrangement;
import com.penguins.project.model.Arrangement.ArrangementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrangementService {

    private final ArrangementRepository arrangementRepository;

    public ArrangementService(ArrangementRepository arrangementRepository) {
        this.arrangementRepository = arrangementRepository;
    }

    @Transactional
    public List<Arrangement> getAllArrangements(){
        return arrangementRepository.findAll();
    }

    @Transactional
    public void addArrangement(Arrangement arrangement){
        arrangementRepository.save(arrangement);
    }

    @Transactional
    public Arrangement getArrangementById(Long id){
        return arrangementRepository.getArrangementById(id);
    }


}
