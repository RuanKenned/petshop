package br.com.ruan.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ruan.petshop.model.Ordem;

@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Long>{
    
}
