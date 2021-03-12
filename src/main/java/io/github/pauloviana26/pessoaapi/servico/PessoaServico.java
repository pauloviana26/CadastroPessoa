package io.github.pauloviana26.pessoaapi.servico;

import io.github.pauloviana26.pessoaapi.entidade.Pessoa;
import io.github.pauloviana26.pessoaapi.repositorio.PessoaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServico {

    private final PessoaRepositorio repositorio;

    public PessoaServico(PessoaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Pessoa> buscarTodas() {
        return repositorio.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    public Pessoa salvar(Pessoa pessoa) {
        Integer p = repositorio.verificarCpfEmail(pessoa.getCpf(), pessoa.getEmail());
        if (p != null) {
            return repositorio.save(pessoa);
        }
        return pessoa;
    }

    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        repositorio.findById(id)
                        .map(pessoa -> {
                            pessoaAtualizada.setId(pessoa.getId());
                            return repositorio.save(pessoaAtualizada);
                        });
        return pessoaAtualizada;
    }

    public void deletar(Long id) throws Exception {
        try {
            repositorio.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
