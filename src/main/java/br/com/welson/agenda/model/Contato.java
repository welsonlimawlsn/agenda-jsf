package br.com.welson.agenda.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contato {

    private int id;
    private String nome;
    private String telefone;
    private String email;

    public void adicionar() throws Exception {
        try(Sql sql = new Sql()) {
            sql.command("INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)", nome, telefone, email);
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<Contato> listAll() throws Exception {
        List<Contato> contatos = new ArrayList<>();
        try(Sql sql = new Sql()){
            ResultSet resultSet = sql.query("SELECT * FROM contatos");
            while (resultSet.next()) {
                Contato contato = new Contato();
                contato.id = resultSet.getInt("id_contato");
                contato.nome = resultSet.getString("nome");
                contato.telefone = resultSet.getString("telefone");
                contato.email = resultSet.getString("email");
                contatos.add(contato);
            }
        } catch (Exception e) {
            throw e;
        }
        return contatos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
