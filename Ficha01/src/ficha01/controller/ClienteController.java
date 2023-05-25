package ficha01.controller;

import ficha01.model.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteController {

    static File diretorio = new File("");
    File database = new File("src\\ficha01\\arquivos\\tbCliente.dat");

    public void CaminhoPrincipal() {

        if (diretorio.exists()) {
            System.out.println(" Directorio OK! ");

        } else {
            diretorio.mkdir();
        }

        if (database.exists()) {

            System.out.println(" Banco de dados OK! ");
        } else {
            try {
                database.createNewFile();

                ArrayList<Cliente> lista = new ArrayList<Cliente>();
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

    public static void create(Cliente d) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        lista = (ArrayList<Cliente>) lista().clone();
        lista.add(d);

        try {
            File db = new File("src\\ficha01\\arquivos\\tbCliente.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(db));

            objectOutputStream.writeObject(lista);

            objectOutputStream.close();

            System.out.println("Salvo com sucesso");

        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void edit(String id, Cliente d) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        lista = (ArrayList<Cliente>) lista().clone();

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getId().equals(id)
                    || (lista.get(i).getNomeDevedor() + " " + lista.get(i).getApelidoDevedor()).equals(id)) {

                lista.remove(i);
                lista.add(d);
            }
        }

        try {
            File db = new File("src\\ficha01\\arquivos\\tbCliente.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(db));

            objectOutputStream.writeObject(lista);

            objectOutputStream.close();

            System.out.println("Salvo com sucesso");

        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Cliente> lista() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        try {

            FileInputStream readData = new FileInputStream("src\\ficha01\\arquivos\\tbCliente.dat");
            ObjectInputStream os = new ObjectInputStream(readData);

            lista = (ArrayList<Cliente>) os.readObject();

            os.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "", JOptionPane.ERROR_MESSAGE);
        }

        return lista;

    }
}
