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
public class PessoaDTO {

    public PessoaDTO(Pessoa pessoa, EnderecoDTO enderecoPrincipal) {
        this.nome = pessoa.getNome();
        this.id = pessoa.getId();
        this.dataDeNascimento = pessoa.getDataDeNascimento();
        this.enderecoPrincipal = enderecoPrincipal;
        this.enderecos = pessoa.getEnderecos();
    }

    public PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.id = pessoa.getId();
        this.dataDeNascimento = pessoa.getDataDeNascimento();
        this.enderecos = pessoa.getEnderecos();
    }

    private Long id;
    private String nome;

    private String dataDeNascimento;

    private EnderecoDTO enderecoPrincipal;

    private List<Endereco> enderecos = new ArrayList<>();
}
