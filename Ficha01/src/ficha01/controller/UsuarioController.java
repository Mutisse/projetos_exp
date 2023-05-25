package ficha01.controller;

import ficha01.model.Usuarios;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioController {

    static File diretorio = new File("");
    File database = new File("src\\ficha01\\arquivos\\tbusuario.dat");

    public void CaminhoPrincipal() {

        if (diretorio.exists()) {
            System.out.print("Directorio OK!");
        } else {
            diretorio.mkdir();
        }

        if (database.exists()) {
            System.out.print("Banco de dados OK!");

        } else {
            try {
                database.createNewFile();

                ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(database));

                objectOutputStream.writeObject(lista);

                objectOutputStream.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void create(Usuarios user) {
        ArrayList<Usuarios> lista = new ArrayList<Usuarios>();

        lista = (ArrayList<Usuarios>) lista().clone();
        lista.add(user);

        try {
            File database = new File("src\\ficha01\\arquivos\\tbusuario.dat");
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(database))) {
                objectOutputStream.writeObject(lista);
            }

           JOptionPane.showMessageDialog(null, "Cadastrado com sucesso","Notificacao",JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Usuarios> lista() {
        ArrayList<Usuarios> lista = new ArrayList<>();

        try {

            FileInputStream readData = new FileInputStream("src\\ficha01\\arquivos\\tbusuario.dat");
            try (ObjectInputStream os = new ObjectInputStream(readData)) {
                lista = (ArrayList<Usuarios>) os.readObject();
            }

        } catch (FileNotFoundException ex) {
             System.out.println("Ficheiro não encontrado!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        }

        return lista;

    }
    
  
}
