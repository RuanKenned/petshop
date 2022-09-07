package br.com.ruan.petshop;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ruan.petshop.model.Ordem;
import br.com.ruan.petshop.model.Pet;
import br.com.ruan.petshop.model.Servico;
import br.com.ruan.petshop.repository.PetRepository;
import br.com.ruan.petshop.repository.ServicoRepository;
import br.com.ruan.petshop.service.OrdemService;

@SpringBootApplication
public class PetshopApplication implements ApplicationRunner{

	@Autowired
	PetRepository petRepository;

	@Autowired
	ServicoRepository servicoRepository;

	@Autowired
	OrdemService ordemService;

	public static void main(String[] args) {
		SpringApplication.run(PetshopApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args){
		Pet pet1 = new Pet("Dog", "Ruan");
		Pet pet2 = new Pet("Dog2", "Ruan2");
		pet1 = petRepository.save(pet1);
		pet2 = petRepository.save(pet2);

		Servico servico1 = new Servico("Banho", new BigDecimal(50.00));
		Servico servico2 = new Servico("Tosa", new BigDecimal(80.00));
		servico1 = servicoRepository.save(servico1);
		servico2 = servicoRepository.save(servico2);

		List<Servico> servicoList = new ArrayList<>();
		servicoList.add(servico1);
		servicoList.add(servico2);

		Ordem ordem = new Ordem(Instant.parse("2022-09-07T00:00:00Z"), "observacao", pet1, servicoList);
		ordemService.save(ordem);
	}
}
