package com.example.Attornatus.teste.Service;

import com.example.Attornatus.teste.DTO.EnderecoDTO;
import com.example.Attornatus.teste.DTO.PessoaDTO;
import com.example.Attornatus.teste.DTO.PessoaInserirDTO;
import com.example.Attornatus.teste.ErrorHandles.NaoEncontradoException;
import com.example.Attornatus.teste.Model.Endereco;
import com.example.Attornatus.teste.Model.Pessoa;
import com.example.Attornatus.teste.Repository.EnderecoRepository;
import com.example.Attornatus.teste.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements PessoaServiceInterface {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void editarPessoa(Pessoa pessoa) {

    }

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        List<PessoaDTO> pessoasDTO = new ArrayList<>();

        pessoas.forEach(pessoa -> {
            PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
            pessoa.getEnderecos().forEach(endereco -> {
                if(endereco.getEnderecoPrincipal()) {
                    EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
                    pessoaDTO.setEnderecoPrincipal(enderecoDTO);
                }
            });
            pessoasDTO.add(pessoaDTO);

        });
         return pessoasDTO;
    }

    @Override
    public Pessoa consultarPessoaPorId(Long id) {
        Optional<Pessoa> buscaPessoa = pessoaRepository.findById(id);

        if(buscaPessoa.isPresent()) {
            return buscaPessoa.get();
        }else {
            throw new NaoEncontradoException("Pessoa não existe com esse ID.");
        }
    }

    public Pessoa editarPessoa(PessoaInserirDTO pessoaInserirDTO, Long id) {
        var pessoa = consultarPessoaPorId(id);

        if(!pessoaInserirDTO.getNome().isEmpty()){
            pessoa.setNome(pessoaInserirDTO.getNome());
        }
        if(!pessoaInserirDTO.getEnderecos().isEmpty()) {
            pessoa.setEnderecos(pessoaInserirDTO.getEnderecos());
        }
        if(!pessoaInserirDTO.getDataDeNascimento().isEmpty()) {
            pessoa.setDataDeNascimento(pessoaInserirDTO.getDataDeNascimento());
        }

        return pessoaRepository.save(pessoa);

    }


}
