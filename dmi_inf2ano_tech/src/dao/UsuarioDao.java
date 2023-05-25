/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import model.Usuario;

/**
 *
 * @author edils
 */
public class UsuarioDao {

    Connection conexao = null;
    PreparedStatement pst = null; //pst quer dizer  PreparedStatement
    ResultSet rst = null;

    /**
     * **************** metodo para adciomar usuario
     *
     *******************
     * @param add
     */
    public void Add(Usuario add) {

        conexao = Conector.conectar();
        String sql = "INSERT INTO `dmi_inf2ano_tech`.`tbUsuarios` (`Nome`, `Genero`, `Username`, `email`, `biografia`, `estado`, `Senha`) VALUES (?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, add.getNome());
            pst.setString(2, add.getGenero());
            pst.setString(3, add.getNomeUsuario());
            pst.setString(4, add.getEmail());
            pst.setString(5, add.getBiografia());
            pst.setString(6, add.getEstado());
            pst.setString(7, add.getSenha());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario << " + add.getNome() + " >> Cadastrado com sucesso!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro no cadastro", "Notificação", WARNING_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e + "Erro na conexao");

            }
        }
    }

    /**
     * **************** metodo para alterar usuario *******************
     */
    public void update(Usuario edit) {
        conexao = Conector.conectar();
        String sql = "update tbUsuarios set Nome =?, Genero =?, Username =?, email =?,biografia=?,estado=?,Senha=? where Id_User =?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, edit.getNome());
            pst.setString(2, edit.getGenero());
            pst.setString(3, edit.getNomeUsuario());
            pst.setString(4, edit.getEmail());
            pst.setString(5, edit.getBiografia());
            pst.setString(6, edit.getEstado());
            pst.setString(7, edit.getSenha());
            pst.setString(8, edit.getId());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Os dados o Usuario << " + edit.getNome() + " de ID << " + edit.getId() + " >> foram atualizados com sucesso!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar", "Notificação", WARNING_MESSAGE);
        } finally {
            try {
                pst.close();
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e + "Erro na conexao");

            }
        }
    }

    /**
     * ********* metodo responsavel pela remocao de usuarios
     *
     ******************
     * @param delete
     */
    public void delete(Usuario delete) {
        conexao = Conector.conectar();
        int confirmar = JOptionPane.showConfirmDialog(null, " Tem a certeza que quer remover esse usuario??? \n    " + delete.getNome(), "Notificação", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {

            String sql = "update tbUsuarios set estado='Removido' where Id_User =?";
            try {

                pst = conexao.prepareStatement(sql);
                pst.setString(1, delete.getId());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, " Usuario foi excluiido com sucesso!!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e + " Erro ao remover", "Notificação", JOptionPane.ERROR);
            } finally {
                try {
                    pst.close();
                    conexao.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e + "Erro na conexao");

                }
            }

        } else {
            JOptionPane.showInternalMessageDialog(null, " Operacao cancelada!!");
        }

    }

    // ''''''''''''''''''''''''''' metodos nao usados '''''''''''''''''''''''
    public List<Usuario> ListarTodos() {
        conexao = Conector.conectar();
        List<Usuario> Lista = new ArrayList<>();
        String sql = "select * from tbUsuarios  where estado !='Removido' order by Nome";

        try {
            pst = conexao.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            while (resultado.next()) {
                Usuario Usuario = new Usuario();
                Usuario.setId(resultado.getString("Id_User"));
                Usuario.setNome(resultado.getString("Nome"));
                Usuario.setNomeUsuario(resultado.getString("Username"));
                Usuario.setEmail(resultado.getString("email"));
                Usuario.setBiografia(resultado.getString("biografia"));
                Usuario.setSenha(resultado.getString("Senha"));
                Usuario.setEstado(resultado.getString("estado"));
                Usuario.setGenero(resultado.getString("Genero"));
                Lista.add(Usuario);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e + " Erro ao buscar o registro", "Notificação", JOptionPane.ERROR);
        } finally {
            try {
                pst.close();
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e + "Erro na conexao");

            }
        }
        return Lista;
    }

    public int GerarCodigo() {

        conexao = Conector.conectar();
        // essa Query faz a selecao do mair valor na base de dados 
        String querySql = "select Max(Id_User)from tbUsuarios";
        int id_recuperado = 0;
        try {
            pst = conexao.prepareStatement(querySql);
            rst = pst.executeQuery();

            if (rst != null && rst.next()) {
                id_recuperado = rst.getInt(1);
                id_recuperado++;

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro do codigo", "Notificação", JOptionPane.ERROR_MESSAGE);

        } finally {
            try {
                pst.close();
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " Erro na conexao", "Notificação", JOptionPane.ERROR);

            }

        }
        return id_recuperado;
    }
}
