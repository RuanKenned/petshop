package br.com.ruan.petshop.controller.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import br.com.ruan.petshop.model.Ordem;
import br.com.ruan.petshop.service.OrdemService;

@Controller
@RequestMapping("api/v1/ordem")
public class OrdemController {
    private OrdemService ordemService;
    
    public OrdemController(OrdemService ordemService){
        this.ordemService= ordemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordem> getOrdem(@PathVariable Long id){
        Optional<Ordem> ordemOp = ordemService.findById(id);

        if(ordemOp.isPresent()){
            return ResponseEntity.ok().body(ordemOp.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Ordem>> getOrdems(){
        List<Ordem> ordemList = ordemService.findAll();

        if(ordemList.size() > 0){
            return ResponseEntity.ok().body(ordemList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Ordem> update(@RequestBody Ordem ordem){
        if(ordem.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id da ordem é inválida, id = null");
        }else{
            ordem = ordemService.save(ordem);
            return ResponseEntity.ok().body(ordem);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Ordem> create(@RequestBody Ordem ordem){
        if(ordem.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id da ordem é inválida, id = null");
        }else{
            Ordem newOrdem = ordemService.save(ordem);
            return ResponseEntity.ok().body(newOrdem);
        }
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdem(@PathVariable Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do ordem é inválido, id = null");
        }else{            
            ordemService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }*/
}
