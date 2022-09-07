package br.com.ruan.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ruan.petshop.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{
    
}
