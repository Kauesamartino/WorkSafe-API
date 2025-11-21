package com.worksafe.api.infrastructure.entity;

import com.worksafe.api.domain.entity.Endereco;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class JpaUsuarioEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "credenciais_id", nullable = false)
    private JpaCredenciaisEntity credenciais;

    private String cargo;

    private String departamento;

    private LocalDateTime createdAt;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public JpaUsuarioEntity() {
    }

    public JpaUsuarioEntity(String nome, String sobrenome, String cpf, String email, String telefone, String cargo, String departamento, LocalDateTime createdAt, Boolean ativo, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.departamento = departamento;
        this.createdAt = createdAt;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public JpaUsuarioEntity(Long id, String nome, String sobrenome, String cpf, String email, String telefone, String cargo, String departamento, LocalDateTime createdAt, Boolean ativo, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.departamento = departamento;
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


}
