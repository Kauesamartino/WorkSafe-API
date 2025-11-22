package com.worksafe.api.domain.entity;

import java.time.LocalDate;

public class Autoavaliacao {

    private Long id;

    private Long usuarioId;

    private LocalDate data;

    private Integer estresse;

    private Integer humor;

    private Integer energia;

    private Integer qualidadeSono;

    private String comentarios;

    public Autoavaliacao(Long usuarioId, Integer estresse, Integer humor, Integer energia, Integer qualidadeSono, String comentarios) {
        this.usuarioId = usuarioId;
        this.data = LocalDate.now();
        this.estresse = estresse;
        this.humor = humor;
        this.energia = energia;
        this.qualidadeSono = qualidadeSono;
        this.comentarios = comentarios;
    }

    public Autoavaliacao(Long id, Long usuarioId, LocalDate data, Integer estresse, Integer humor, Integer energia, Integer qualidadeSono, String comentarios) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.data = data;
        this.estresse = estresse;
        this.humor = humor;
        this.energia = energia;
        this.qualidadeSono = qualidadeSono;
        this.comentarios = comentarios;
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setEstresse(Integer estresse) {
        isEstresseValido(estresse);
        this.estresse = estresse;
    }

    private void isEstresseValido(Integer estresse) {
        if (estresse == null || estresse < 1 || estresse > 10) {
            throw new IllegalArgumentException("Nível de estresse inválido. Deve ser um inteiro entre 1 e 10.");
        }
    }

    public void setHumor(Integer humor) {
        isHumorValido(humor);
        this.humor = humor;
    }

    private void isHumorValido(Integer humor) {
        if (humor == null || humor < 1 || humor > 10) {
            throw new IllegalArgumentException("Nível de humor inválido. Deve ser um inteiro entre 1 e 10.");
        }
    }

    public void setEnergia(Integer energia) {
        isEnergiaValida(energia);
        this.energia = energia;
    }

    private void isEnergiaValida(Integer energia) {
        if (energia == null || energia < 1 || energia > 10) {
            throw new IllegalArgumentException("Nível de energia inválido. Deve ser um inteiro entre 1 e 10.");
        }
    }

    public void setQualidadeSono(Integer qualidadeSono) {
        isQualidadeSonoValida(qualidadeSono);
        this.qualidadeSono = qualidadeSono;
    }

    private void isQualidadeSonoValida(Integer qualidadeSono) {
        if (qualidadeSono == null || qualidadeSono < 1 || qualidadeSono > 10) {
            throw new IllegalArgumentException("Nível de qualidade do sono inválido. Deve ser um inteiro entre 1 e 10.");
        }
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getData() {
        return data;
    }

    public Integer getEstresse() {
        return estresse;
    }

    public Integer getHumor() {
        return humor;
    }

    public Integer getEnergia() {
        return energia;
    }

    public Integer getQualidadeSono() {
        return qualidadeSono;
    }

    public String getComentarios() {
        return comentarios;
    }
}
