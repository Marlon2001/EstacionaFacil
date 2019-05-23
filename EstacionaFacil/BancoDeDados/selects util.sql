SELECT 
    mensalista.cod_mensalista,
    mensalista.nome,
    mensalista.email,
    veiculo.placa,
    fabricante.fabricante,
    telefone.telefone,
    endereco.logradouro,
    endereco.numero,
    endereco.bairro,
    cidade.nome_cidade,
    estado.nome_estado,
    estado.sigla
FROM
    tbl_mensalista AS mensalista
    
        INNER JOIN
    tbl_mensalista_telefone AS mt ON mt.cod_mensalista = mensalista.cod_mensalista
        INNER JOIN
    tbl_telefone AS telefone ON telefone.cod_telefone = mt.cod_telefone
        INNER JOIN
        
    tbl_veiculo_mensalista AS vm ON vm.cod_mensalista = mensalista.cod_mensalista
        INNER JOIN
    tbl_veiculo AS veiculo ON veiculo.cod_veiculo = vm.cod_veiculo
    
        INNER JOIN
    tbl_fabricante AS fabricante ON fabricante.cod_fabricante = veiculo.cod_fabricante
    
        INNER JOIN
    tbl_endereco_mensalista AS em ON em.cod_mensalista = mensalista.cod_mensalista
        INNER JOIN
    tbl_endereco AS endereco ON endereco.cod_endereco = em.cod_endereco
    
        INNER JOIN
    tbl_cidade AS cidade ON cidade.cod_cidade = endereco.cod_cidade
        INNER JOIN
    tbl_estado AS estado ON estado.cod_estado = cidade.cod_estado where mensalista.cod_mensalista =1; 

