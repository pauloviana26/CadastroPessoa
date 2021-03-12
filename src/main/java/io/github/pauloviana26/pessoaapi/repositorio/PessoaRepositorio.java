package io.github.pauloviana26.pessoaapi.repositorio;

import io.github.pauloviana26.pessoaapi.entidade.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {

    @Query("SELECT COUNT(p.id) FROM Pessoa p" +
            " WHERE (p.cpf = :cpf)" +
            " and (p.email = :email)")
    Integer verificarCpfEmail(String cpf, String email);
}
