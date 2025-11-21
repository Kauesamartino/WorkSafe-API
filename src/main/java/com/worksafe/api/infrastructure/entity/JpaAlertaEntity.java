package com.worksafe.api.infrastructure.entity;

import com.worksafe.api.domain.enums.Severidade;
import com.worksafe.api.domain.enums.TipoAlerta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
public class JpaAlertaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private JpaUsuarioEntity jpaUsuarioEntity;

    @Enumerated(EnumType.STRING)
    private TipoAlerta tipoAlerta;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Severidade severidade;

    private LocalDateTime data;

    private Boolean resolvido;

    public JpaAlertaEntity() {
    }

    public JpaAlertaEntity(Long id, JpaUsuarioEntity jpaUsuarioEntity, TipoAlerta tipoAlerta, String descricao, Severidade severidade, LocalDateTime data, Boolean resolvido) {
        this.id = id;
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.tipoAlerta = tipoAlerta;
        this.descricao = descricao;
        this.severidade = severidade;
        this.data = data;
        this.resolvido = resolvido;
    }

    public JpaAlertaEntity(JpaUsuarioEntity jpaUsuarioEntity, TipoAlerta tipoAlerta, String descricao, Severidade severidade, LocalDateTime data, Boolean resolvido) {
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.tipoAlerta = tipoAlerta;
        this.descricao = descricao;
        this.severidade = severidade;
        this.data = data;
        this.resolvido = resolvido;
    }

    public Long getId() {
        return id;
    }

    public JpaUsuarioEntity getJpaUsuarioEntity() {
        return jpaUsuarioEntity;
    }

    public TipoAlerta getTipoAlerta() {
        return tipoAlerta;
    }

    public String getDescricao() {
        return descricao;
    }

    public Severidade getSeveridade() {
        return severidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Boolean getResolvido() {
        return resolvido;
    }
}
