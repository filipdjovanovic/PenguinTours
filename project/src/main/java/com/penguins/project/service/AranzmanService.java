package com.penguins.project.service;

import com.penguins.project.model.Aranzman.Aranzman;
import com.penguins.project.model.Aranzman.AranzmanRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AranzmanService {

    private final AranzmanRepository aranzmanRepository;

    public AranzmanService(AranzmanRepository aranzmanRepository) {
        this.aranzmanRepository = aranzmanRepository;
    }

    @Transactional
    public List<Aranzman> getAllAranzmans(){
        return aranzmanRepository.findAll();
    }

    @Transactional
    public void addAranzman(Aranzman aranzman){
        aranzmanRepository.save(aranzman);
    }

    @Transactional
    public Optional<Aranzman> getAranzmanById(Long id){
        return aranzmanRepository.findById(id);
    }
}
