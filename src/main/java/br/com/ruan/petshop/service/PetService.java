package br.com.ruan.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.petshop.model.Pet;
import br.com.ruan.petshop.repository.PetRepository;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public Optional<Pet> findById(Long id){
        return petRepository.findById(id);
    }

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public void delete(Long id){
        petRepository.deleteById(id);
    }

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }
}
