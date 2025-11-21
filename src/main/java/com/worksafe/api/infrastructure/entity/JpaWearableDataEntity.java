package com.worksafe.api.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wearable_data")
public class JpaWearableDataEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private JpaUsuarioEntity jpaUsuarioEntity;

    private LocalDateTime data;

    private Double batimentosMedia;

    private Integer passos;

    private Double sonoTotal;

    private String rawData;

    public JpaWearableDataEntity() {
    }

    public JpaWearableDataEntity(Long id, JpaUsuarioEntity jpaUsuarioEntity, LocalDateTime data, Double batimentosMedia, Integer passos, Double sonoTotal, String rawData) {
        this.id = id;
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.data = data;
        this.batimentosMedia = batimentosMedia;
        this.passos = passos;
        this.sonoTotal = sonoTotal;
        this.rawData = rawData;
    }

    public JpaWearableDataEntity(JpaUsuarioEntity jpaUsuarioEntity, LocalDateTime data, Double batimentosMedia, Integer passos, Double sonoTotal, String rawData) {
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.data = data;
        this.batimentosMedia = batimentosMedia;
        this.passos = passos;
        this.sonoTotal = sonoTotal;
        this.rawData = rawData;
    }
}
