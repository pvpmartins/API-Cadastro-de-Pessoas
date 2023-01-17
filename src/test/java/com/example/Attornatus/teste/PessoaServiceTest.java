package com.example.Attornatus.teste;

import com.example.Attornatus.teste.DTO.PessoaInserirDTO;
import com.example.Attornatus.teste.Model.Endereco;
import com.example.Attornatus.teste.Model.Pessoa;
import com.example.Attornatus.teste.Repository.PessoaRepository;
import com.example.Attornatus.teste.Service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PessoaServiceTest {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeEach


    @Test
    void addPessoa() {
        List<Endereco> enderecos = new ArrayList<>();
        var endereco = new Endereco();
        endereco.setCep("123458489");
        endereco.setNumero(150);
        endereco.setLogradouro("Rua Duque de Caxiass");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEnderecoPrincipal(true);

        enderecos.add(endereco);
        var pessoa = new Pessoa();

        pessoa.setNome("carlos");
        pessoa.setEnderecos(enderecos);
        pessoa.setDataDeNascimento("14/05/2001");

        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
        Pessoa pessoaRepo = pessoaRepository.findById(pessoa.getId()).get();

//        Assertions.assertArrayEquals(pessoaCriada.getEnderecos().toArray(), pessoaRepo.getEnderecos().toArray());

//        Assertions.assertEquals(pessoa.getDataDeNascimento(), pessoaRepo.getDataDeNascimento());
//        Assertions.assertEquals(pessoa.getNome(), pessoaRepo.getNome());
        Assertions.assertEquals(pessoa.getId(), pessoaRepo.getId());
    }

    @Test
    void editarPessoa() {
        var pessoa = new Pessoa();

        pessoa.setNome("Joao");
        pessoa.setDataDeNascimento("14/05/2001");

        pessoaService.criarPessoa(pessoa);

        PessoaInserirDTO pessoaInserirDTO = new PessoaInserirDTO();

        pessoaInserirDTO.setNome("Carlos");
        pessoaInserirDTO.setDataDeNascimento("23/11/1987");

        var pessoaEditada = pessoaService.editarPessoa(pessoaInserirDTO, 1L);

        var pessoaRepo = pessoaRepository.findById(1L);

        Assertions.assertEquals(pessoaInserirDTO.getNome(), pessoaRepo.get().getNome());
    }


}
