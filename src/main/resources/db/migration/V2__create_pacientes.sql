CREATE TABLE pacientes (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    uf VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id)
);
