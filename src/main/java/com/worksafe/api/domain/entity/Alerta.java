package com.worksafe.api.domain.entity;

import com.worksafe.api.domain.enums.Severidade;
import com.worksafe.api.domain.enums.TipoAlerta;

import java.time.LocalDateTime;

public class Alerta {

    private Long id;

    private Long usuarioId;

    private TipoAlerta tipoAlerta;

    private String descricao;

    private Severidade severidade;

    private LocalDateTime data;

    private Boolean resolvido;

    // request -> model
    public Alerta(Long usuarioId, TipoAlerta tipoAlerta, String descricao, Severidade severidade) {
        this.usuarioId = usuarioId;
        this.tipoAlerta = tipoAlerta;
        this.descricao = descricao;
        this.severidade = severidade;
        this.data = LocalDateTime.now().minusHours(3);
        this.resolvido = false;
    }

    public Alerta(Long id, Long usuarioId, TipoAlerta tipoAlerta, String descricao, Severidade severidade, LocalDateTime data, Boolean resolvido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipoAlerta = tipoAlerta;
        this.descricao = descricao;
        this.severidade = severidade;
        this.data = data;
        this.resolvido = resolvido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public TipoAlerta getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(TipoAlerta tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Severidade getSeveridade() {
        return severidade;
    }

    public void setSeveridade(Severidade severidade) {
        this.severidade = severidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Boolean getResolvido() {
        return resolvido;
    }

    public void setResolvido(Boolean resolvido) {
        this.resolvido = resolvido;
    }
}
