INSERT INTO fornecedores (ativo, cnpj, email, razao_social, telefone)
VALUES (true, '12.345.678/0001-99', 'contato@worldtech.com.br', 'World Tech Importações', '(11) 99999-9999');

INSERT INTO fornecedores (ativo, cnpj, email, razao_social, telefone)
VALUES (true, '98.765.432/0001-88', 'vendas@kabum.com.br', 'Kabum Componentes', '(11) 88888-8888');

INSERT INTO fornecedores (ativo, cnpj, email, razao_social, telefone)
VALUES (false, '11.222.333/0001-44', 'falencia@chinaimp.com', 'China Imp. (Inativo)', '(11) 77777-7777');


INSERT INTO produtos (categoria, data_cadastro, descricao, fornecedor_id, nome, preco_custo, preco_venda, quantidade_minimo, quantidade_total_estoque, status)
VALUES ('PERIFERICOS', CURRENT_DATE, 'Teclado mecânico de alta qualidade com switch azul', 1, 'Teclado Mecânico RGB', 150.00, 350.00, 10, 50, 'ATIVO');

INSERT INTO produtos (categoria, data_cadastro, descricao, fornecedor_id, nome, preco_custo, preco_venda, quantidade_minimo, quantidade_total_estoque, status)
VALUES ('HARDWARE', CURRENT_DATE, 'Placa de vídeo Nvidia RTX 3060 12GB', 2, 'Placa de Vídeo RTX 3060', 1800.00, 2500.00, 5, 12, 'ATIVO');

INSERT INTO produtos (categoria, data_cadastro, descricao, fornecedor_id, nome, preco_custo, preco_venda, quantidade_minimo, quantidade_total_estoque, status)
VALUES ('MONITORES', '2024-01-15', 'Monitor Gamer 27 polegadas 144hz', 1, 'Monitor Ultrawide 27"', 900.00, 1500.00, 20, 0, 'PAUSADO');

INSERT INTO produtos (categoria, data_cadastro, descricao, fornecedor_id, nome, preco_custo, preco_venda, quantidade_minimo, quantidade_total_estoque, status)
VALUES ('PERIFERICOS', '2023-11-20', 'Mouse antigo com fio', 2, 'Mouse Office', 15.00, 35.00, 50, 0, 'DESCONTINUADO');