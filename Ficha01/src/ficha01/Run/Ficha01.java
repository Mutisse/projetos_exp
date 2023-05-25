/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ficha01.Run;

import ficha01.controller.UsuarioController;
import ficha01.view.TelaLogin;
import ficha01.view.TelaRegistoUsuario;

/**
 *
 * @author Mutisse
 */
public class Ficha01 {

    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        controller.CaminhoPrincipal();

        if (!controller.lista().isEmpty()) {
            new TelaLogin().setVisible(true);
        } else {
            new TelaRegistoUsuario().setVisible(true);
        }
    }

}
