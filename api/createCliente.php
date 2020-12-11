<?php
include("./Classes/Cliente.php");
use Classes\Cliente;

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
$data = json_decode(file_get_contents('php://input'), true);

try{

    $sql = $conexao->prepare('insert into cliente values(default, :nome, :email, :cpf, :rg, :telefone, :senha)');
    $sql->execute(array(
        ':nome'=>$data['nome'],
        ':email'=>$data['email'],
        ':cpf'=>$data['cpf'],
        ':rg'=>$data['rg'], 
        ':telefone'=>$data['telefone'], 
        ':senha'=>$data['senha']
      ));

    if($sql->rowCount() >= 1){
        echo json_encode(["resultado" => "sucesso"]);
    }
    else{
        echo json_encode(["resultado" => "falha", "error" => $sql->errorInfo()]);
    }

}
catch(PDOException $e){
    echo json_encode(["resultado" => "EXCESSAO"]);
}
?>