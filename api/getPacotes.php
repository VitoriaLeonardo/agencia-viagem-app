<?php
    include("./Classes/Pacote.php");
    use \Classes\Pacote;
    
    $servidor = "dbturismo_etec.mysql.dbaas.com.br";
    $usuario = "dbturismo_etec";
    $senha = "G9NCaAu2nOzC!";
    $banco = "dbturismo_etec";

 
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
        P.valor_unitario AS VALOR_UNITARIO_PACOTE, 
        P.IMAGEM AS IMAGEM_PACOTE, 
        P.descricao_pacote AS DESCRICAO_PACOTE
        FROM PACOTE P";

    $consulta = $conexao->query($query);
    $packages = array();
    
    while($exibe = $consulta->fetch(PDO::FETCH_ASSOC)){
        $package = new Pacote();
        
        //Pacote
        $package->id = $exibe["ID_PACOTE"];
        $package->nome = $exibe["NOME_PACOTE"];
        $package->valorUnitario = $exibe["VALOR_UNITARIO_PACOTE"];
        $package->descricaoPacote = $exibe["DESCRICAO_PACOTE"];
        $package->imagem = $exibe["IMAGEM_PACOTE"];
        // var_dump($exibe["TIPO_TRANSPORTE_PACOTE"]);
        
        //var_dump($package);
        array_push($packages, $package);
    }
    // var_dump($packages);
    echo json_encode(["packages" => $packages]);
    
?>