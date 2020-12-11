<?php

    $servidor = "dbturismo_etec.mysql.dbaas.com.br";
    $usuario = "dbturismo_etec";
    $senha = "G9NCaAu2nOzC!";
    $banco = "dbturismo_etec";
 
    try {
        $conexao = new PDO("mysql:host=$servidor;dbname=$banco;charset=utf8", $usuario, $senha);
    }
    catch(PDOException $e){
        echo $e->getMessage();
    }

    $data = json_decode(file_get_contents('php://input'), true);

    $sql = $conexao->prepare('SELECT * FROM cliente WHERE email = :email and senha = :senha');
    $sql->execute(array(
        ':email'=>$data['email'],
        ':senha'=>$data['senha']
      ));

    if($sql->rowCount() >= 1){
        echo json_encode(["resultado" => "Logado com sucesso!", "status" => 200]);
        
    }else{
        echo json_encode(["resultado"=>"Falha ao logar", "status" => 401]);
    }
    
?>