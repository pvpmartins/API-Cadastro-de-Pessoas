package com.example.Attornatus.teste.Model;

import com.example.Attornatus.teste.DTO.PessoaDTO;
import com.example.Attornatus.teste.DTO.PessoaInserirDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name= "pessoas")
@NoArgsConstructor
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    public Pessoa(PessoaInserirDTO pessoaInserirDTO) {
        this.nome = pessoaInserirDTO.getNome();
        this.dataDeNascimento = pessoaInserirDTO.getDataDeNascimento();
        this.enderecos = pessoaInserirDTO.getEnderecos();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Por favor coloque informe o nome")
    private String nome;

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data de Nascimento deve ser no formato dd/mm/aaaa")

    private String dataDeNascimento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="pessoa_id", referencedColumnName = "id")
    private List<Endereco> enderecos;

}
