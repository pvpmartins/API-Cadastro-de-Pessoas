package com.example.Attornatus.teste.Repository;

import com.example.Attornatus.teste.Model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public List<Pessoa> findByNome(String nome);
}
