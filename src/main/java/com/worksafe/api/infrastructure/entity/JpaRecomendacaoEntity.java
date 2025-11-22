package com.worksafe.api.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "recomendacao")
public class JpaRecomendacaoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private JpaUsuarioEntity jpaUsuarioEntity;

    private String tipoAtividade;

    private String titulo;

    private String descricao;

    private LocalDateTime createdAt;

    private Boolean consumido;

    public JpaRecomendacaoEntity() {
    }

    public JpaRecomendacaoEntity(JpaUsuarioEntity jpaUsuarioEntity, String tipoAtividade, String titulo, String descricao, LocalDateTime createdAt, Boolean consumido) {
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.tipoAtividade = tipoAtividade;
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.consumido = consumido;
    }

    public JpaUsuarioEntity getJpaUsuarioEntity() {
        return jpaUsuarioEntity;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getConsumido() {
        return consumido;
    }

    public Long getId() {
        return id;
    }
}
