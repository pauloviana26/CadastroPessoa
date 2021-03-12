package io.github.pauloviana26.pessoaapi.entidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @CPF(message = "Cpf inválido.")
    @NotNull(message = "O campo cpf não pode ser nulo.")
    private String cpf;

    @Column
    @NotEmpty(message = "O campo nome não pode ser nulo ou em branco.")
    private String nome;

    @Column(unique = true)
    @Email(message = "E-mail inválido.")
    @NotNull(message = "O campo e-mail não pode ser nulo.")
    private String email;

    @Column
    @NotNull(message = "O campo data de nascimento não pode ser nulo.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return cpf.equals(pessoa.cpf) && email.equals(pessoa.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, email);
    }
}