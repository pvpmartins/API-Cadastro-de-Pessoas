package com.example.Attornatus.teste.Service;

import com.example.Attornatus.teste.Model.Pessoa;

import java.util.List;

public interface PessoaServiceInterface {

    public Pessoa criarPessoa(Pessoa pessoa);

    public void editarPessoa(Pessoa pessoa);

    public Pessoa consultarPessoaPorId(Long id);

//    public List<Pessoa> consultarPessoa(Pessoa pessoa);
}
