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

import br.com.ruan.petshop.model.Pet;
import br.com.ruan.petshop.service.PetService;

@Controller
@RequestMapping("api/v1/pet")
public class PetController {

    private PetService petService;
    
    public PetController(PetService petService){
        this.petService= petService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id){
        Optional<Pet> petOp = petService.findById(id);

        if(petOp.isPresent()){
            return ResponseEntity.ok().body(petOp.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Pet>> getPets(){
        List<Pet> petList = petService.findAll();

        if(petList.size() > 0){
            return ResponseEntity.ok().body(petList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Pet> update(@RequestBody Pet pet){
        if(pet.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do pet é inválido, id = null");
        }else{
            pet = petService.save(pet);
            return ResponseEntity.ok().body(pet);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Pet> create(@RequestBody Pet pet){
        if(pet.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do pet é inválido, id = null");
        }else{
            Pet newPet = petService.save(pet);
            return ResponseEntity.ok().body(newPet);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do pet é inválido, id = null");
        }else{            
            petService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }
}
