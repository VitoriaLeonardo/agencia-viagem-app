package com.cursoapp.skyblueapplication.Classes;

import java.util.HashMap;

public class Cliente {
    public int id;
    public String nome;
    public String email;
    public String cpf;
    public String rg;
    public String telefone;
    public String senha;

    public HashMap hashMap(){
        HashMap map = new HashMap();
        map.put("nome", this.nome);
        map.put("email", this.email);
        map.put("cpf", this.cpf);
        map.put("rg", this.rg);
        map.put("telefone", this.telefone);
        map.put("senha", this.senha);
        return map;
    }
}
