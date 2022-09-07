package br.com.ruan.petshop.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import br.com.ruan.petshop.model.Servico;
import br.com.ruan.petshop.service.ServicoService;

@Controller
@RequestMapping("api/v1/servico")
public class ServicoController {
    private ServicoService servicoService;
    
    public ServicoController(ServicoService servicoService){
        this.servicoService = servicoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getServico(@PathVariable Long id){
        Optional<Servico> servicoOp = servicoService.findById(id);

        if(servicoOp.isPresent()){
            return ResponseEntity.ok().body(servicoOp.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Servico>> getServicos(){
        List<Servico> servicoList = servicoService.findAll();

        if(servicoList.size() > 0){
            return ResponseEntity.ok().body(servicoList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Servico> update(@RequestBody Servico servico){
        if(servico.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do servico é inválido, id = null");
        }else{
            servico = servicoService.save(servico);
            return ResponseEntity.ok().body(servico);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Servico> create(@RequestBody Servico servico){
        if(servico.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do servico é inválido, id = null");
        }else{
            Servico newServico = servicoService.save(servico);
            return ResponseEntity.ok().body(newServico);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do servico é inválido, id = null");
        }else{            
            servicoService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }
}
