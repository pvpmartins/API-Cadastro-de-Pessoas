package com.example.Attornatus.teste.Service;

import com.example.Attornatus.teste.DTO.EnderecoDTO;
import com.example.Attornatus.teste.DTO.PessoaDTO;
import com.example.Attornatus.teste.ErrorHandles.NaoEncontradoException;
import com.example.Attornatus.teste.Model.Endereco;
import com.example.Attornatus.teste.Model.Pessoa;
import com.example.Attornatus.teste.Repository.EnderecoRepository;
import com.example.Attornatus.teste.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    PessoaRepository pessoaRepository;

    public Optional<Endereco> retornarEnderecoPrincipal(List<Endereco> enderecos, String enderecoPrincipal) {
        Optional<Endereco> enderecoFinal = enderecos.stream().filter(endereco -> {
            return endereco.getLogradouro().equals(enderecoPrincipal);
        }).findFirst();

        return enderecoFinal;
    }

    public Endereco consultarEnderecoPorId(Long id) {
        Optional<Endereco> buscaEndereco = enderecoRepository.findById(id);

        if (buscaEndereco.isEmpty()) {
            throw new NaoEncontradoException("Endereço não existe com esse ID.");
        }

        return buscaEndereco.get();
    }

    public Endereco editarEndereco(Endereco endereco) {

        Endereco enderecoBusca = consultarEnderecoPorId(endereco.getId());

        if(endereco.getEnderecoPrincipal() != null) {
            enderecoBusca.setEnderecoPrincipal(endereco.getEnderecoPrincipal());
        }
        if(endereco.getCidade() != null) {
            enderecoBusca.setCidade(endereco.getCidade());
        }
        if(endereco.getNumero() != null){
            enderecoBusca.setNumero(endereco.getNumero());
        }
        if(endereco.getLogradouro() != null) {
            enderecoBusca.setLogradouro(endereco.getLogradouro());
        }
        if(endereco.getCep() != null) {
            enderecoBusca.setCep(endereco.getCep());
        }

        return enderecoRepository.save(enderecoBusca);
    }

    public List<Endereco> cadastrarEnderecos(List<Endereco> enderecos) {
        return enderecoRepository.saveAll(enderecos);
    }

    public List<EnderecoDTO> listEnderecos () {
        var enderecos = enderecoRepository.findAll();
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();

        if(enderecos.isEmpty()) {
            throw new NaoEncontradoException("Não há endereços cadastrados.");
        }
        enderecos.forEach(endereco -> {
            EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
            enderecosDTO.add(enderecoDTO);
        });

        return enderecosDTO;
    }

    public List<Endereco> listEnderecoByPessoaId(Long id) {
        var pessoa = pessoaRepository.findById(id);

        if (pessoa.isEmpty()) {
            throw new NaoEncontradoException("Não há nenhuma pessoa com este ID");
        }

        if(pessoa.get().getEnderecos().isEmpty()) {
            throw new NaoEncontradoException("Não há endereços cadastrados por essa pessoa.");
        }

        return pessoa.get().getEnderecos();
    }

    public Pessoa createEnderecoByPessoaId(Long id, Endereco endereco) {
        var pessoa = pessoaRepository.findById(id);

        if (pessoa.isEmpty()) {
            throw new NaoEncontradoException("Não há nenhuma pessoa com este ID");
        }

        if(pessoa.get().getEnderecos().isEmpty()) {
            throw new NaoEncontradoException("Não há endereços cadastrados por essa pessoa.");
        }

//        var enderecoCriado = enderecoRepository.save(endereco);

        pessoa.get().getEnderecos().add(endereco);

        var pessoaSalva = pessoaRepository.save(pessoa.get());

        return pessoaSalva;
    }

    public EnderecoDTO buscarEnderecoPrincipalByPessoaId (Long id) {
        var pessoaRepo = pessoaRepository.findById(id);

        if (pessoaRepo.isEmpty()) {
            throw new NaoEncontradoException("Não há nenhuma pessoa com este ID");
        }

        var pessoa = pessoaRepo.get();


        for (Endereco endereco : pessoa.getEnderecos()) {
            if (endereco.getEnderecoPrincipal()) {
                EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
                return enderecoDTO;
            }
        }

        throw new NaoEncontradoException("Essa pessoa não tem um endereço principal");
    }
}
