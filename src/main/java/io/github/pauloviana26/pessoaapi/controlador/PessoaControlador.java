package io.github.pauloviana26.pessoaapi.controlador;

import io.github.pauloviana26.pessoaapi.entidade.Pessoa;
import io.github.pauloviana26.pessoaapi.servico.PessoaServico;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaControlador {

    private final PessoaServico servico;

    public PessoaControlador(PessoaServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Pessoa> buscarTodas() {
        return servico.buscarTodas();
    }

    @GetMapping("/buscarPorId/{id}")
    public Optional<Pessoa> buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody @Valid Pessoa entidade) {
        Pessoa pessoa = servico.salvar(entidade);
        if (pessoa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pessoa;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa atualizar(@PathVariable Long id, @RequestBody @Valid Pessoa entidade) {
        Pessoa pessoa = servico.atualizar(id, entidade);
        if (pessoa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pessoa;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long id) throws Exception {
        servico.deletar(id);
    }

}
