CREATE TABLE autoavaliacao (
                               id BIGSERIAL PRIMARY KEY,

                               usuario_id BIGINT NOT NULL,

                               data DATE NOT NULL,

                               estresse INTEGER,
                               humor INTEGER,
                               energia INTEGER,
                               qualidade_sono INTEGER,

                               comentarios TEXT,

                               CONSTRAINT fk_autoavaliacao_usuario
                                   FOREIGN KEY (usuario_id)
                                       REFERENCES usuario(id)
                                       ON DELETE CASCADE
);
