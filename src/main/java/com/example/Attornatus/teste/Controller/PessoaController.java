package com.example.Attornatus.teste.Controller;

import com.example.Attornatus.teste.DTO.EnderecoDTO;
import com.example.Attornatus.teste.DTO.PessoaDTO;
import com.example.Attornatus.teste.DTO.PessoaInserirDTO;
import com.example.Attornatus.teste.Model.Endereco;
import com.example.Attornatus.teste.Model.Pessoa;
import com.example.Attornatus.teste.Service.EnderecoService;
import com.example.Attornatus.teste.Service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Object> cadastrarPessoa(@Valid @RequestBody PessoaInserirDTO pessoaInserirDTO) {
        Pessoa pessoa = new Pessoa(pessoaInserirDTO);
        Pessoa pessoaCadastrada = pessoaService.criarPessoa(pessoa);
        PessoaDTO pessoaDTO = new PessoaDTO(pessoaCadastrada);

        pessoaInserirDTO.getEnderecos().forEach(endereco -> {
            if(endereco.getEnderecoPrincipal().equals(true)) {
                EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
                pessoaDTO.setEnderecoPrincipal(enderecoDTO);
            }
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDTO);
    }

    @GetMapping ResponseEntity<List<PessoaDTO>> listarPessoas() {
        var pessoas = pessoaService.listarPessoas();

        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> consultarPessoa(@PathVariable Long id) {
        PessoaDTO pessoaDTO = new PessoaDTO(pessoaService.consultarPessoaPorId(id));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody PessoaInserirDTO pessoaInserirDTO) {
        var pessoaEditada = pessoaService.editarPessoa(pessoaInserirDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(pessoaEditada);
    }
}
