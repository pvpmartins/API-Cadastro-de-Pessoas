package com.example.Attornatus.teste.Controller;

import com.example.Attornatus.teste.DTO.EnderecoDTO;
import com.example.Attornatus.teste.Model.Endereco;
import com.example.Attornatus.teste.Model.Pessoa;
import com.example.Attornatus.teste.Service.EnderecoService;
import com.example.Attornatus.teste.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public List<EnderecoDTO> listarEnderecos() {
        return enderecoService.listEnderecos();
    }

    @GetMapping("/pessoa/{id}")
    public List<Endereco> buscarEnderecoPorPessoaId(@PathVariable Long id) {
        return enderecoService.listEnderecoByPessoaId(id);
    }

    @GetMapping("principal/pessoa/{id}")
    public EnderecoDTO buscarEnderecoPrincipalPorPessoaId(@PathVariable Long id) {
        return enderecoService.buscarEnderecoPrincipalByPessoaId(id);
    }

    @PostMapping("/pessoa/{id}")
    public Pessoa criarEnderecoParaPessoaById (@PathVariable Long id, @RequestBody Endereco endereco) {
        return enderecoService.createEnderecoByPessoaId(id, endereco);
    }
}
