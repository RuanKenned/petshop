package br.com.ruan.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.petshop.model.Servico;
import br.com.ruan.petshop.repository.ServicoRepository;

@Service
public class ServicoService {
    
    @Autowired
    ServicoRepository servicoRepository;

    public Optional<Servico> findById(Long id){
        return servicoRepository.findById(id);
    }

    public List<Servico> findAll(){
        return servicoRepository.findAll();
    }

    public void delete(Long id){
        servicoRepository.deleteById(id);
    }

    public Servico save(Servico servico){
        return servicoRepository.save(servico);
    }
}
