package com.example.Attornatus.teste.DTO;

import com.example.Attornatus.teste.Model.Endereco;
import com.example.Attornatus.teste.Model.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class PessoaInserirDTO {

    public PessoaInserirDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.dataDeNascimento = pessoa.getDataDeNascimento();
        this.enderecos = pessoa.getEnderecos();
    }

    private String nome;

    private String dataDeNascimento;
    private List<Endereco> enderecos = new ArrayList<>();
}
