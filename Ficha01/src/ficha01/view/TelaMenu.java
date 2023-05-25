/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ficha01.view;

import ficha01.controller.UsuarioController;
import ficha01.model.Usuarios;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Mutisse
 */
public final class TelaMenu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public TelaMenu() {
        initComponents();
        HoraData();

        URL url = this.getClass().getResource("/ficha01/view/icons/user.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(iconeTitulo);
        for (Usuarios user : UsuarioController.lista()) {
            lbl_Usuario.setText(user.getNome());
           
        }
        lbl_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/administrador.png")));
    }

    public void HoraData() {
        Date data = new Date();
        sdf = new SimpleDateFormat("dd MMMM yyyy");
        Calendar now = Calendar.getInstance();

        class hora implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                lbl_horaData.setText(String.format(" Horas: " + " %1$tH : %1$tM : %1$tS", now));

            }

        }

        Timer timer = new Timer(1000, new hora());
        timer.start();

        lbl_data.setText(sdf.format(data));

        Calendar c1 = Calendar.getInstance();
        int hora = c1.get(Calendar.HOUR_OF_DAY);

        if (hora <= 12 && hora <= 12) {
            lbl_Saudacao.setText(" Bom Dia");
        } else if (hora > 12 && hora < 18) {
            lbl_Saudacao.setText(" Boa Tarde");
        } else {
            lbl_Saudacao.setText(" Boa Noite");
        }
        lbl_texto.setText("  @" + now.getWeekYear() + " * Maputo-Moçambique ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_Usuario = new javax.swing.JLabel();
        lbl_data = new javax.swing.JLabel();
        lbl_horaData = new javax.swing.JLabel();
        lbl_Saudacao = new javax.swing.JLabel();
        lbl_texto = new javax.swing.JLabel();
        lbl_add = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        GerarRelatorio = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        lbl_Usuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel1.add(lbl_Usuario);
        lbl_Usuario.setBounds(20, 190, 141, 20);

        lbl_data.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jPanel1.add(lbl_data);
        lbl_data.setBounds(10, 330, 160, 50);

        lbl_horaData.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jPanel1.add(lbl_horaData);
        lbl_horaData.setBounds(10, 10, 160, 20);

        lbl_Saudacao.setFont(new java.awt.Font("Arial Narrow", 3, 12)); // NOI18N
        jPanel1.add(lbl_Saudacao);
        lbl_Saudacao.setBounds(10, 390, 160, 20);

        lbl_texto.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        lbl_texto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(lbl_texto);
        lbl_texto.setBounds(10, 420, 160, 16);

        lbl_add.setBackground(new java.awt.Color(153, 153, 153));
        lbl_add.setToolTipText("Clica para ver o seu perfil");
        lbl_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_addActionPerformed(evt);
            }
        });
        jPanel1.add(lbl_add);
        lbl_add.setBounds(20, 60, 106, 120);

        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(10, 30, 160, 200);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 180, 490);
        getContentPane().add(jDesktopPane1);
        jDesktopPane1.setBounds(180, 2, 640, 490);

        jMenu4.setBackground(new java.awt.Color(255, 51, 51));
        jMenu4.setForeground(new java.awt.Color(255, 51, 51));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/logout.png"))); // NOI18N
        jMenu4.setText("Sair");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/off.png"))); // NOI18N
        jMenuItem6.setText("Encerar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/rela.png"))); // NOI18N
        jMenu3.setText("Estatísticas");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/total.png"))); // NOI18N
        jMenuItem4.setText("Estatísticas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        GerarRelatorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        GerarRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/printer_green_10875 - Copy.png"))); // NOI18N
        GerarRelatorio.setText("Imprimir");
        GerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GerarRelatorioActionPerformed(evt);
            }
        });
        jMenu3.add(GerarRelatorio);

        jMenuBar1.add(jMenu3);

        jMenu1.setBackground(new java.awt.Color(204, 255, 255));
        jMenu1.setBorder(null);
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/devedor.png"))); // NOI18N
        jMenu1.setText("Clientes");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/loan (1).png"))); // NOI18N
        jMenuItem1.setText(" Registar  Devedor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/salary2.png"))); // NOI18N
        jMenuItem3.setText("Pagamento de Dívidas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(840, 527));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    TelaRegistar registar = new TelaRegistar();
    TelaPagamento pagar = new TelaPagamento();
    TelaRelatorio relatorio = new TelaRelatorio();
    TelaPerfil perfil = new TelaPerfil();
   
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            jDesktopPane1.remove(pagar);
            jDesktopPane1.remove(perfil);
            jDesktopPane1.remove(relatorio);
            jDesktopPane1.add(registar);

            registar.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, " Erro! nao selecionar a tela que ja esta em execucao", "notificacao", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            jDesktopPane1.remove(registar);
            jDesktopPane1.remove(relatorio);
            jDesktopPane1.remove(perfil);
            jDesktopPane1.add(pagar);
            pagar.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, " Erro! nao selecionar a tela que ja esta em execucao", "notificacao", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            jDesktopPane1.remove(registar);
            jDesktopPane1.remove(pagar);
            jDesktopPane1.remove(perfil);
            jDesktopPane1.add(relatorio);
            relatorio.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, " Erro! nao selecionar a tela que ja esta em execucao", "notificacao", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Tem a Certeza que quer sair??", "Notificacao", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            this.dispose();
        } else {
            new TelaMenu();
        }

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void GerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GerarRelatorioActionPerformed
        TelaRelatorio rL = new TelaRelatorio();
        rL.GerarRelatorio();
    }//GEN-LAST:event_GerarRelatorioActionPerformed

    private void lbl_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_addActionPerformed
        try {
            jDesktopPane1.remove(pagar);
            jDesktopPane1.remove(relatorio);
            jDesktopPane1.remove(registar);
            jDesktopPane1.add(perfil);

            perfil.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, " Erro! nao selecionar a tela que ja esta em execucao", "notificacao", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lbl_addActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenu().setVisible(true);
            }
        });
    }

    private String dataf;
    private SimpleDateFormat sdf;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem GerarRelatorio;
    public javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_Saudacao;
    public javax.swing.JLabel lbl_Usuario;
    private javax.swing.JButton lbl_add;
    private javax.swing.JLabel lbl_data;
    private javax.swing.JLabel lbl_horaData;
    private javax.swing.JLabel lbl_texto;
    // End of variables declaration//GEN-END:variables
}
