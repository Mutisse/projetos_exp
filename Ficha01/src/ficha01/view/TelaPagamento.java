/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ficha01.view;

import ficha01.controller.ClienteController;
import ficha01.model.Cliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mutisse
 */
public final class TelaPagamento extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaPagmmentos
     */
    public TelaPagamento() {
        initComponents();
        preencherTabela();
        linhas = new Object[][]{};
        sdf = new SimpleDateFormat("dd MMMM yyyy");
        preencherTabela();

        Date data = new Date();
        DateFormat dtHora = DateFormat.getDateTimeInstance();
        lbl_Atual.setText(dtHora.format(data));
    }

    public void sett_campos() {
        try {

            // "ID  ", "Nome  ","Genero","Emprestado", "A Pagar", "Remanescente", "Estado", "Data da divida"
            lbl_id.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 0).toString());
            lbl_nome.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 1).toString());
            lbl_genero.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 2).toString());
            lbl_emprstado.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 3).toString());
            lbl_Apagar.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 4).toString());
            lbl_ValorRemanescente.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 5).toString());
            txtEstado.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 6).toString());
            lbt_datadivida.setText(" " + jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 7).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro ao Selecionar Cliente ", "Notificação", JOptionPane.ERROR);
        }

    }

    public void find() {
        boolean found = false;
        if (!txtPesquisar.getText().isEmpty()) {
            model.setNumRows(0);
            for (Cliente d : ClienteController.lista()) {
                if (txtPesquisar.getText().equalsIgnoreCase(d.getNomeDevedor() + " " + d.getApelidoDevedor()) || txtPesquisar.getText().equals(d.getId())) {
                    dataf = sdf.format(d.getData());
                    model.addRow(new Object[]{d.getId(), d.getNomeDevedor() + " " + d.getApelidoDevedor(), dataf,
                        d.getValorDivida(), d.getValorAPagar(), d.getRemanescente(), d.getEstadoDivida()});
                    found = true;

                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(rootPane, "Nenhuma correspondência encontrada!");
                preencherTabela();
                txtPesquisar.setText(null);
            }

        } else {
            txtPesquisar.setText(null);
            JOptionPane.showMessageDialog(rootPane, "Digite o NOME ou o ID do cliente a procurar!");
        }
    }

    private void preencherTabela() {
        try {

            Cliente[] array = new Cliente[ClienteController.lista().size()];
            int i = 0;
            for (Cliente d : ClienteController.lista()) {
                array[i] = d;
                i++;
            }

            Arrays.sort(array);   //FAZ A ORDENAÇÃO DO ARRAY "DÍVIDAS" EM ORDEM CRESCENTE DOS VALORES A PAGAR
            model.setNumRows(0);
            for (Cliente dadosCliente : array) {
                dataf = sdf.format(dadosCliente.getData());
                model.addRow(new Object[]{dadosCliente.getId(), dadosCliente.getNomeDevedor() + " " + dadosCliente.getApelidoDevedor(), dadosCliente.getGenero(),
                    dadosCliente.getValorDivida(), dadosCliente.getValorAPagar(), dadosCliente.getRemanescente(), dadosCliente.getEstadoDivida(), dataf});
            }
        } catch (Exception e) {
        }
        jTabbedPane1.setSelectedIndex(1);
    }

    public void Pagar() {

        System.out.println(" 1");
        if (found == true) {
            if (txtEstado.getText().equals("Não Pago")) {
                if (Float.parseFloat(spnValor.getValue().toString()) > 0
                        && Float.parseFloat(spnValor.getValue().toString()) <= Float.parseFloat(lbl_ValorRemanescente.getText())) {

                    for (Cliente d : ClienteController.lista()) {
                        if (d.getId().equals(lbl_id.getText())) {

                            d.setRemanescente(d.getRemanescente() - Float.parseFloat(spnValor.getValue().toString()));
                            ClienteController.edit(lbl_id.getText(), d);
                            JOptionPane.showMessageDialog(rootPane, "Pagamento realizado com sucesso!");
//                            txtPesquisar.setText(null);
//                            txtEstado.setText(null);
//                            txtId.setText(null);
//                            txtNome.setText(null);
//                            lbl_ValorRemanescente.setText(null);
//                            txtPesquisar.setText(null);
//                            spnValor.setValue(0);
//                            btnRegistar.setEnabled(false);

                            if (d.getRemanescente() == 0.0) {
                                d.setEstadoDivida(true);
                                ClienteController.edit(d.getId(), d);
                            }

                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Valor fora dos padrões.\nVerifique os dados!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                }
            } else {

            }
        }
        System.out.println("pageui");
    }

    public void Limpar() {

        lbl_nome.setText(null);
        lbl_genero.setText(null);
        lbt_datadivida.setText(null);
        lbl_id.setText(null);
        lbl_Apagar.setText(null);
        lbl_emprstado.setText(null);
        txtEstado.setText(null);
        lbl_ValorRemanescente.setText(null);
        spnValor.setValue(0);
    }

    ;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        lbl_genero = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        lbl_Atual = new javax.swing.JLabel();
        lbt_datadivida = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        lbl_ValorRemanescente = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        spnValor = new javax.swing.JSpinner();
        jLabel63 = new javax.swing.JLabel();
        lbl_Apagar = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        btnRegistar = new javax.swing.JButton();
        txtEstado = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        lbl_emprstado = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        getContentPane().setLayout(null);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel7.setLayout(null);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Pagamentos");
        jLabel64.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel64);
        jLabel64.setBounds(110, 20, 360, 60);

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setText("Id:");
        jPanel7.add(jLabel67);
        jLabel67.setBounds(20, 130, 30, 20);

        lbl_id.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_id.setForeground(new java.awt.Color(255, 51, 51));
        lbl_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_id);
        lbl_id.setBounds(100, 120, 90, 30);

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("Nome do Cliente:");
        jPanel7.add(jLabel65);
        jLabel65.setBounds(20, 170, 160, 20);

        lbl_nome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_nome);
        lbl_nome.setBounds(20, 210, 170, 30);

        lbl_genero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_genero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_genero);
        lbl_genero.setBounds(210, 210, 120, 30);

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel66.setText("Genero:");
        jPanel7.add(jLabel66);
        jLabel66.setBounds(210, 180, 70, 22);

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/loan.png"))); // NOI18N
        jPanel7.add(jLabel59);
        jLabel59.setBounds(490, 10, 120, 100);

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setText("Data da Divida:");
        jPanel7.add(jLabel69);
        jLabel69.setBounds(210, 250, 130, 22);

        lbl_Atual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_Atual.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_Atual);
        lbl_Atual.setBounds(350, 280, 180, 30);

        lbt_datadivida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbt_datadivida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbt_datadivida);
        lbt_datadivida.setBounds(180, 280, 150, 30);

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setText("Data de Atual:");
        jPanel7.add(jLabel60);
        jLabel60.setBounds(350, 250, 120, 22);

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setText(" Dívida remanescente:");
        jPanel7.add(jLabel61);
        jLabel61.setBounds(10, 250, 180, 22);

        lbl_ValorRemanescente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_ValorRemanescente.setForeground(new java.awt.Color(255, 51, 51));
        lbl_ValorRemanescente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_ValorRemanescente);
        lbl_ValorRemanescente.setBounds(20, 280, 130, 30);

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setText("Valor da  Divida:");
        jPanel7.add(jLabel62);
        jLabel62.setBounds(130, 320, 140, 22);

        spnValor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spnValor.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 50.0d));
        spnValor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnValorStateChanged(evt);
            }
        });
        spnValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                spnValorMouseReleased(evt);
            }
        });
        spnValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                spnValorKeyReleased(evt);
            }
        });
        jPanel7.add(spnValor);
        spnValor.setBounds(130, 350, 100, 30);

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("A Pagar:");
        jPanel7.add(jLabel63);
        jLabel63.setBounds(480, 180, 90, 22);

        lbl_Apagar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_Apagar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_Apagar);
        lbl_Apagar.setBounds(480, 210, 100, 30);

        jButton12.setBackground(new java.awt.Color(204, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/Cancel.png"))); // NOI18N
        jButton12.setText("Cancelar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton12);
        jButton12.setBounds(490, 350, 120, 30);

        jButton13.setBackground(new java.awt.Color(204, 255, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/clean.png"))); // NOI18N
        jButton13.setText("Limpar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton13);
        jButton13.setBounds(360, 350, 120, 30);

        btnRegistar.setBackground(new java.awt.Color(204, 255, 255));
        btnRegistar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/paypa.png"))); // NOI18N
        btnRegistar.setText("Pagar");
        btnRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistarActionPerformed(evt);
            }
        });
        jPanel7.add(btnRegistar);
        btnRegistar.setBounds(250, 350, 100, 30);

        txtEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEstado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(txtEstado);
        txtEstado.setBounds(20, 350, 90, 30);

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel70.setText("Estado");
        jPanel7.add(jLabel70);
        jLabel70.setBounds(20, 320, 100, 20);

        lbl_emprstado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_emprstado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.add(lbl_emprstado);
        lbl_emprstado.setBounds(350, 210, 100, 30);

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel72.setText("Emprestado:");
        jPanel7.add(jLabel72);
        jLabel72.setBounds(350, 180, 120, 20);

        jTabbedPane1.addTab("Pagamento", jPanel7);

        jPanel6.setLayout(null);

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyTyped(evt);
            }
        });
        txtPesquisar.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtPesquisarVetoableChange(evt);
            }
        });
        jPanel6.add(txtPesquisar);
        txtPesquisar.setBounds(10, 90, 190, 30);

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/FundoRegistarDividas.png"))); // NOI18N
        jPanel6.add(jLabel68);
        jLabel68.setBounds(490, 20, 120, 100);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/Pesq.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);
        jButton1.setBounds(210, 90, 40, 30);

        jTable2 = new javax.swing.JTable(){
            public boolean isCellEditTable(int rowIndex, int colIndex) {
                return false;
            }
        };
        String[] colunas = new String[]{"ID ","Nome ","Genero","Emprestado", "A Pagar", "Remanescente", "Estado", "Data da divida"};
        model = new DefaultTableModel(linhas, colunas);
        jTable2.setModel(model);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(115);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(60);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jPanel6.add(jScrollPane1);
        jScrollPane1.setBounds(10, 150, 600, 230);

        jTabbedPane1.addTab("Pesquisar", jPanel6);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 630, 420);

        setBounds(0, 0, 639, 463);
    }// </editor-fold>//GEN-END:initComponents

    private void spnValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnValorKeyReleased

    }//GEN-LAST:event_spnValorKeyReleased

    private void spnValorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnValorMouseReleased

    }//GEN-LAST:event_spnValorMouseReleased

    private void spnValorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnValorStateChanged

    }//GEN-LAST:event_spnValorStateChanged

    private void btnRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistarActionPerformed
        Pagar();
        preencherTabela();
        Limpar();

    }//GEN-LAST:event_btnRegistarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        find();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Limpar();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (!lbl_nome.getText().isEmpty()) {
            if (0 == JOptionPane.showConfirmDialog(rootPane, "Pretende cancelar este registo?\nOs dados actuais serão perdidos.", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                Limpar();

            }
        } else {

        }
    }//GEN-LAST:event_jButton12ActionPerformed
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        sett_campos();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jTable2MouseClicked
    private void btnPesquisarStateChanged(javax.swing.event.ChangeEvent evt) {

    }
    private void txtPesquisarVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtPesquisarVetoableChange

    }//GEN-LAST:event_txtPesquisarVetoableChange

    private void txtPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyTyped
        if (txtEstado.getText().equals("Não Pago")) {
            btnRegistar.setEnabled(true);
        } else if (txtEstado.getText().equals("Pago")) {
            btnRegistar.setEnabled(false);
        }
    }//GEN-LAST:event_txtPesquisarKeyTyped
    private DefaultTableModel model;
    private final Object[][] linhas;
    private String dataf;
    private final SimpleDateFormat sdf;
    private boolean found = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    public javax.swing.JLabel lbl_Apagar;
    public javax.swing.JLabel lbl_Atual;
    public javax.swing.JLabel lbl_ValorRemanescente;
    public javax.swing.JLabel lbl_emprstado;
    public javax.swing.JLabel lbl_genero;
    public javax.swing.JLabel lbl_id;
    public javax.swing.JLabel lbl_nome;
    public javax.swing.JLabel lbt_datadivida;
    private javax.swing.JSpinner spnValor;
    public javax.swing.JLabel txtEstado;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
