CREATE TABLE recomendacao (
                              id BIGSERIAL PRIMARY KEY,

                              usuario_id BIGINT NOT NULL,

                              tipo_atividade VARCHAR(150),

                              titulo VARCHAR(200) NOT NULL,

                              descricao TEXT,

                              created_at DATE NOT NULL,

                              consumido BOOLEAN DEFAULT FALSE,

                              CONSTRAINT fk_recomendacao_usuario
                                  FOREIGN KEY (usuario_id)
                                      REFERENCES usuario(id)
                                      ON DELETE CASCADE
);
