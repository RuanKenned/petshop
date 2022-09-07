package br.com.ruan.petshop.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ordem")
public class Ordem {

    public Ordem(Instant dtOrdem, String observacao, Pet pet, List<Servico> servicos){
        this.dtOrdem = dtOrdem;
        this.observacao = observacao;
        this.pet = pet;
        this.servicos = servicos;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "dtOrdem", nullable = false)
    private Instant dtOrdem;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "idPet", nullable = false)
    private Pet pet;

    @ManyToMany
    @JoinTable(name = "ordem_servico", 
            joinColumns = {@JoinColumn(name = "ordem_id")}, 
            inverseJoinColumns = {@JoinColumn(name = "servico_id")})
    private List<Servico> servicos = new ArrayList<Servico>();
}
