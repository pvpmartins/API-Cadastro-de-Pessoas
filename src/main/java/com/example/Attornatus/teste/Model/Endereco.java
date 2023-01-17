package com.example.Attornatus.teste.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name= "enderecos")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String logradouro;

    @Column
    @Size(min = 9, max = 9, message = "CEP deve possuir 9 digitos")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "CEP deve ter apenas números")
    private String cep;

    @Column
    private Integer numero;

    @Column
    private String cidade;

    @Column
    private Boolean enderecoPrincipal = false;

}
