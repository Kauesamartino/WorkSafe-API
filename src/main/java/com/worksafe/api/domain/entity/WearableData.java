package com.worksafe.api.domain.entity;

import java.time.LocalDateTime;

public class WearableData {

    private Long id;

    private Long usuarioId;

    private LocalDateTime data;

    private Double batimentosMedia;

    private Integer passos;

    private Double sonoTotal;

    private String rawData;

    public void setUsuarioId(Long usuarioId) {
        isUsuarioIdValido(usuarioId);
        this.usuarioId = usuarioId;
    }

    private void isUsuarioIdValido(Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setBatimentosMedia(Double batimentosMedia) {
        isBatimentosMediaValido(batimentosMedia);
        this.batimentosMedia = batimentosMedia;
    }

    private void isBatimentosMediaValido(Double batimentosMedia) {
        if (batimentosMedia == null || batimentosMedia <= 0) {
            throw new IllegalArgumentException("Batimentos médios inválidos.");
        }
    }

    public void setPassos(Integer passos) {
        isPassosValidos(passos);
        this.passos = passos;
    }

    private void isPassosValidos(Integer passos) {
        if (passos == null || passos <= 0) {
            throw new IllegalArgumentException("Número de passos inválido.");
        }
    }

    public void setSonoTotal(Double sonoTotal) {
        isSonoTotalValido(sonoTotal);
        this.sonoTotal = sonoTotal;
    }

    private void isSonoTotalValido(Double sonoTotal) {
        if (sonoTotal == null || sonoTotal <= 0) {
            throw new IllegalArgumentException("Sono total inválido.");
        }
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Double getBatimentosMedia() {
        return batimentosMedia;
    }

    public Integer getPassos() {
        return passos;
    }

    public Double getSonoTotal() {
        return sonoTotal;
    }

    public String getRawData() {
        return rawData;
    }
}
