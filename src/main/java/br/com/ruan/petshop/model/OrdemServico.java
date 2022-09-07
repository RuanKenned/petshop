package br.com.ruan.petshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ordem_servico")
public class OrdemServico {

    @EmbeddedId
    private OrdemServicoId id = new OrdemServicoId();

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @MapsId("idOrdem")
    private Ordem ordem;

    @ManyToOne
    @MapsId("idServico")
    private Servico servico;
}
