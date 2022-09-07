package br.com.ruan.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ruan.petshop.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
    
}
