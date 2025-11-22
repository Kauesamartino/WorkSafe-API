CREATE TABLE alerta (
                        id BIGSERIAL PRIMARY KEY,

                        usuario_id BIGINT NOT NULL,

                        tipo_alerta VARCHAR(50) NOT NULL,

                        descricao TEXT,

                        severidade VARCHAR(50) NOT NULL,

                        data TIMESTAMP,

                        resolvido BOOLEAN DEFAULT FALSE,

                        CONSTRAINT fk_alerta_usuario
                            FOREIGN KEY (usuario_id)
                                REFERENCES usuario(id)
                                ON DELETE CASCADE
);
