package com.worksafe.api.infrastructure.entity;

import com.worksafe.api.domain.entity.Endereco;
import com.worksafe.api.domain.enums.Sexo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class JpaUsuarioEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String email;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "credenciais_id", nullable = false)
    private JpaCredenciaisEntity credenciais;

    private String cargo;

    private String departamento;

    private LocalDate dataNascimento;

    private LocalDateTime createdAt;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public JpaUsuarioEntity() {
    }

    public JpaUsuarioEntity(Long id, String nome, String sobrenome, String cpf, Sexo sexo, String email, String telefone, String cargo, String departamento, LocalDate dataNascimento, LocalDateTime createdAt, Boolean ativo, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.departamento = departamento;
        this.dataNascimento = dataNascimento;
        this.createdAt = createdAt;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public JpaUsuarioEntity(String nome, String sobrenome, String cpf, Sexo sexo, String email, String telefone, String cargo, String departamento, LocalDate dataNascimento, LocalDateTime createdAt, Boolean ativo, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.departamento = departamento;
        this.dataNascimento = dataNascimento;
        this.createdAt = createdAt;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }


    public void setCredenciais(JpaCredenciaisEntity credenciais) {
        this.credenciais = credenciais;
    }

    public JpaCredenciaisEntity getCredenciais() {
        return credenciais;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
