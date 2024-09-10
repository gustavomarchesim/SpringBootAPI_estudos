-- Início da transação
BEGIN;

-- Adicionar nova coluna boolean
ALTER TABLE medicos ADD COLUMN novo_ativo BOOLEAN DEFAULT TRUE;

-- Copiar dados da coluna antiga para a nova
UPDATE medicos SET novo_ativo = (ativo = 1);

-- Remover coluna antiga
ALTER TABLE medicos DROP COLUMN ativo;

-- Renomear nova coluna para o nome original
ALTER TABLE medicos RENAME COLUMN novo_ativo TO ativo;

-- Finalizar transação
COMMIT;
