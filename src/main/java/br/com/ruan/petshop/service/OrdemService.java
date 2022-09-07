package br.com.ruan.petshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ruan.petshop.model.Ordem;
import br.com.ruan.petshop.model.OrdemServico;
import br.com.ruan.petshop.model.Servico;
import br.com.ruan.petshop.repository.OrdemRepository;
import br.com.ruan.petshop.repository.OrdemServicoRepository;

@Service
public class OrdemService {

    @Autowired
    OrdemRepository ordemRepository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public Ordem save(Ordem ordem){
        //Obtem os servicos
        List<Servico> servicoList = ordem.getServicos();

        //inserir a ordem sem os servicos
		ordem.setServicos(new ArrayList<>());
		ordemRepository.save(ordem);
        
		for(Servico servico: servicoList){
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setOrdem(ordem);
			ordemServico.setServico(servico);
			ordemServico.setValor(servico.getValor());

			ordemServicoRepository.save(ordemServico );
		}
        ordem.setServicos(servicoList);
        
        return ordem;
    }

    public Optional<Ordem> findById(Long id){
        return ordemRepository.findById(id);
    }

    public List<Ordem> findAll(){
        return ordemRepository.findAll();
    }
}
