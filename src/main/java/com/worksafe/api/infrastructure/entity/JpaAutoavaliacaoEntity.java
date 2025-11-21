package com.worksafe.api.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "autoavaliacao")
public class JpaAutoavaliacaoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private JpaUsuarioEntity jpaUsuarioEntity;

    private LocalDate data;

    private Integer estresse;

    private Integer humor;

    private Integer energia;

    private Integer qualidadeSono;

    private String comentarios;

    public JpaAutoavaliacaoEntity() {
    }

    public JpaAutoavaliacaoEntity(Long id, JpaUsuarioEntity jpaUsuarioEntity, LocalDate data, Integer estresse, Integer humor, Integer energia, Integer qualidadeSono, String comentarios) {
        this.id = id;
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.data = data;
        this.estresse = estresse;
        this.humor = humor;
        this.energia = energia;
        this.qualidadeSono = qualidadeSono;
        this.comentarios = comentarios;
    }

    public JpaAutoavaliacaoEntity(JpaUsuarioEntity jpaUsuarioEntity, LocalDate data, Integer estresse, Integer humor, Integer energia, Integer qualidadeSono, String comentarios) {
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.data = data;
        this.estresse = estresse;
        this.humor = humor;
        this.energia = energia;
        this.qualidadeSono = qualidadeSono;
        this.comentarios = comentarios;
    }
}
