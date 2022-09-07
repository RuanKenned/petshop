package br.com.ruan.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ruan.petshop.model.OrdemServico;
import br.com.ruan.petshop.model.OrdemServicoId;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, OrdemServicoId>{
    
}
