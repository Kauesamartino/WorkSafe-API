CREATE TABLE wearable_data (
                               id BIGSERIAL PRIMARY KEY,

                               usuario_id BIGINT NOT NULL,

                               data TIMESTAMP NOT NULL,

                               batimentos_media DOUBLE PRECISION,
                               passos INTEGER,
                               sono_total DOUBLE PRECISION,

                               CONSTRAINT fk_wearable_data_usuario
                                   FOREIGN KEY (usuario_id)
                                       REFERENCES usuario(id)
                                       ON DELETE CASCADE
);
