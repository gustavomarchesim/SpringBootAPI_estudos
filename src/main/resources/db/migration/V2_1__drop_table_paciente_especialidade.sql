-- Início da transação
BEGIN;

-- Remover coluna antiga
ALTER TABLE medicos DROP COLUMN especialidade;

-- Finalizar transação
COMMIT;