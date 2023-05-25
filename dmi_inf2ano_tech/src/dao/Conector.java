/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;

/**
 *
 * @author user
 */
public class Conector {

    /* metodo responsavel em estabelecer aconexao com o banco*/
    public static Connection conectar() {

        java.sql.Connection conexao = null;
        /* chamar o drive de concxao*/
        String driver = "com.mysql.jdbc.Driver";
        /* armazena informacao do banco de dados */
        String url = "jdbc:mysql://localhost:3306/DMI_INF2Ano_TECH";
        String user = "root";
        String senha = "";
        /* estabelecendo a conexao com o banco*/

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

}
