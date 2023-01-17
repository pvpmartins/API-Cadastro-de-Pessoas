package com.example.Attornatus.teste.DTO;

import com.example.Attornatus.teste.Model.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    public EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.cidade = endereco.getLogradouro();
    }

    private Long id;

    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

}
