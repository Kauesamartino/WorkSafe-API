package com.worksafe.api.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Recomendacao {

    private Long id;

    private Long usuarioId;

    private String tipoAtividade;

    private String titulo;

    private String descricao;

    private LocalDate createdAt;

    private Boolean consumido;

    public Recomendacao(Long usuarioId, String tipoAtividade, String titulo, String descricao) {
        this.usuarioId = usuarioId;
        this.tipoAtividade = tipoAtividade;
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = LocalDate.now();
        this.consumido = false;
    }

    public void setUsuarioId(Long usuarioId) {
        isUsuarioIdValido(usuarioId);
        this.usuarioId = usuarioId;
    }

    private void isUsuarioIdValido(Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
    }

    public void setTipoAtividade(String tipoAtividade) {
        isTipoAtividadeValido(tipoAtividade);
        this.tipoAtividade = tipoAtividade;
    }

    private void isTipoAtividadeValido(String tipoAtividade) {
        if (tipoAtividade == null || tipoAtividade.isEmpty()) {
            throw new IllegalArgumentException("Tipo de atividade inválido.");
        }
    }

    public void setTitulo(String titulo) {
        isTituloValido(titulo);
        this.titulo = titulo;
    }

    private void isTituloValido(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título inválido.");
        }
    }

    public void setDescricao(String descricao) {
        isDescricaoValida(descricao);
        this.descricao = descricao;
    }

    private void isDescricaoValida(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida.");
        }
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setConsumido(Boolean consumido) {
        this.consumido = consumido;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Boolean getConsumido() {
        return consumido;
    }
}
