/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Klesia
 */
public class Usuario {

    private String Id,nome,email,genero,nomeUsuario,biografia,senha,estado;
   

    public Usuario(String nome, String email, String genero, String nomeUsuario, String biografia, String senha, String estado, String Id) {
        this.nome = nome;
        this.email = email;
        this.genero = genero;
        this.nomeUsuario = nomeUsuario;
        this.biografia = biografia;
        this.senha = senha;
        this.estado = estado;
        this.Id = Id;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", genero=" + genero + ", nomeUsuario=" + nomeUsuario + ", biografia=" + biografia + ", senha=" + senha + ", estado=" + estado + ", Id=" + Id + '}';
    }
    
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
    
}
