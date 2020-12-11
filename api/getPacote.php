<?php
    include("./Classes/Pacote.php");
    use \Classes\Pacote;
    include("./Classes/Destino.php");
    use \Classes\Destino;
    include("./Classes/Origem.php");
    use \Classes\Origem;
    
    $servidor = "dbturismo_etec.mysql.dbaas.com.br";
    $usuario = "dbturismo_etec";
    $senha = "G9NCaAu2nOzC!";
    $banco = "dbturismo_etec";
    $id = $_GET['id'];
 
    try {
        $conexao = new PDO("mysql:host=$servidor;dbname=$banco;charset=utf8", $usuario, $senha);
        // array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8");
        // echo 'Conexao efetuada com sucesso!';
        //$conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
    catch(PDOException $e){
        echo $e->getMessage();
    }

    $query = "SELECT P.IDPACOTE AS ID_PACOTE, 
        P.NOME_PACOTE, 
        P.IMAGEM AS IMAGEM_PACOTE, 
        P.data_ida AS DATA_IDA_PACOTE, 
        P.data_volta AS DATA_VOLTA_PACOTE, 
        P.tipo_transporte AS TIPO_TRANSPORTE_PACOTE, 
        P.valor_unitario AS VALOR_UNITARIO_PACOTE, 
        P.descricao_pacote AS DESCRICAO_PACOTE,
        O.IDORIGDEST AS ID_ORIGEM, 
        O.NOME AS NOME_ORIGEM, 
        O.CEP AS CEP_ORIGEM, 
        O.RUA AS RUA_ORIGEM, 
        O.NUM AS NUMERO_ORIGEM, 
        O.BAIRRO AS BAIRRO_ORIGEM, 
        O.CIDADE AS CIDADE_ORIGEM, 
        O.UF AS UF_ORIGEM,
        D.IDORIGDEST AS ID_DESTINO, 
        D.NOME AS NOME_DESTINO, 
        D.CEP AS CEP_DESTINO, 
        D.RUA AS RUA_DESTINO, 
        D.NUM AS NUMERO_DESTINO, 
        D.BAIRRO AS BAIRRO_DESTINO, 
        D.CIDADE AS CIDADE_DESTINO, 
        D.UF AS UF_DESTINO
        FROM PACOTE P
        INNER JOIN ORIGEM_DESTINO O
        ON P.ID_ORIGEM = O.IDORIGDEST
        INNER JOIN ORIGEM_DESTINO D
        ON P.ID_DESTINO = D.IDORIGDEST
        WHERE P.IDPACOTE = ".$id;

    $consulta = $conexao->query($query);
    $package = new Pacote();
    
    if($exibe = $consulta->fetch(PDO::FETCH_ASSOC)){
        $package->origem = new Origem();
        $package->destino = new Destino();

        //Pacote
        $package->id = $exibe["ID_PACOTE"];
        $package->nome = $exibe["NOME_PACOTE"];
        $package->dataIda = $exibe["DATA_IDA_PACOTE"];
        $package->dataVolta = $exibe["DATA_VOLTA_PACOTE"];
        $package->tipoTransporte = $exibe["TIPO_TRANSPORTE_PACOTE"];
        $package->valorUnitario = $exibe["VALOR_UNITARIO_PACOTE"];
        $package->descricaoPacote = $exibe["DESCRICAO_PACOTE"];
        $package->imagem = $exibe["IMAGEM_PACOTE"];
        
        //Origem
        $package->origem->id = $exibe["ID_ORIGEM"];
        $package->origem->nome = $exibe["NOME_ORIGEM"];
        $package->origem->cep = $exibe["CEP_ORIGEM"];
        $package->origem->rua = $exibe["RUA_ORIGEM"];
        $package->origem->bairro = $exibe["BAIRRO_ORIGEM"];
        $package->origem->cidade = $exibe["CIDADE_ORIGEM"];
        $package->origem->uf = $exibe["UF_ORIGEM"];
        
        //Destino
        $package->destino->id = $exibe["ID_DESTINO"];
        $package->destino->nome = $exibe["NOME_DESTINO"];
        $package->destino->cep = $exibe["CEP_DESTINO"];
        $package->destino->rua = $exibe["RUA_DESTINO"];
        $package->destino->bairro = $exibe["BAIRRO_DESTINO"];
        $package->destino->cidade = $exibe["CIDADE_DESTINO"];
        $package->destino->uf = $exibe["UF_DESTINO"];
    }

    echo json_encode($package);
    
?>