ALTER TABLE foto_documento DROP COLUMN foto_documento_id;
ALTER TABLE foto_documento ADD PRIMARY KEY(cliente_cpf_cnpj);
ALTER TABLE foto_documento MODIFY tamanho BIGINT;
