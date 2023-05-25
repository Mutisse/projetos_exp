/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ficha01.view;

import ficha01.controller.ClienteController;
import ficha01.model.Cliente;
import ficha01.model.RelatorioPdf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Mutisse
 */
public final class TelaRelatorio extends javax.swing.JInternalFrame {

    /**
     * Creates new form Relatorio
     */
    public TelaRelatorio() {
        initComponents();
        Relatorio();
        showPieChart();
        showBarChart();
        Date data = new Date();
        DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG);
        dat = formato.format(data);
    }

    public void showBarChart() {
        DefaultCategoryDataset barra = new DefaultCategoryDataset();
        barra.setValue(DevMaiorDivida, "dat", "Maior Divida");
        barra.setValue(tMenorDivida, "dat", "Menor Divida");
        barra.setValue(totalNPagas, "dat", " Nao Pagas");
        barra.setValue(totalPagas, "dat", " Pagas");
        barra.setValue(mediaDividas, "dat", "Media Dividas");
        barra.setValue(totalDividas, "dat", "Total Dividas");
        org.jfree.chart.JFreeChart grafico = ChartFactory.createBarChart("Agiota Business", "Status", "Montante", barra, PlotOrientation.VERTICAL, true, true, false);

        
        ChartPanel barChartPanel = new ChartPanel(grafico);
        barChartPanel.setBounds(5, 5, 600, 370);
        showBarChart.removeAll();
        showBarChart.add(barChartPanel, BorderLayout.CENTER);

    }

    public void showPieChart() {

        //create dataset
        /*Valor total em dívidas,
        Média das Dívidas, Valor Total de Dívidas Pagas, 
        Devedor com maior dívida, Devedor com menor Dívida) */
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("Maior divida", (float) DevMaiorDivida);
        barDataset.setValue("Menor divida", (float) tMenorDivida);
        barDataset.setValue("Total Nao Pagas", (float) totalNPagas);
        barDataset.setValue("Total Pagas", (float) totalPagas);
        barDataset.setValue("Media Dividas", (float) mediaDividas);
        barDataset.setValue("Total Dividas", (float) totalDividas);

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Agiota Business", barDataset, true, true, true);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        //changing pie chart blocks colors
        // piePlot.setSectionPaint(10, new Color(0, 204, 204));
        piePlot.setSectionPaint((int) DevMaiorDivida, new Color(255, 255, 102));
        piePlot.setSectionPaint((int) tMenorDivida, new Color(102, 255, 102));
        piePlot.setSectionPaint((int) totalNPagas, new Color(0, 204, 204));
        piePlot.setSectionPaint((int) totalPagas, new Color(255, 102, 153));
        piePlot.setSectionPaint((int) totalDividas, new Color(0, 204, 204));
        piePlot.setSectionPaint((int) mediaDividas, new Color(0, 204, 204));
        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel pieChartPanel = new ChartPanel(piechart);
        pieChartPanel.setBounds(20, 5, 330, 280);
        PiepanelChart.add(pieChartPanel, BorderLayout.CENTER);
        PiepanelChart.validate();
    }

    public void Relatorio() {
        if (!ClienteController.lista().isEmpty()) {
            Cliente[] array = new Cliente[ClienteController.lista().size()];
            int i = 0;

            for (Cliente d : ClienteController.lista()) {
                array[i] = d;
                totalDividas += d.getValorAPagar();
                if (d.getEstadoDivida().equals("Pago")) {
                    totalPagas += d.getValorAPagar();

                }
                if ((d.getEstadoDivida().equals("Não Pago") && d.getRemanescente() != 0)) {
                    totalPagas += (d.getValorAPagar() - d.getRemanescente());
                    totalNPagas = d.getRemanescente();
                }
                i++;
            }
            Arrays.sort(array);
            mediaDividas = (float) (totalDividas / array.length);
            tMenorDivida = (float) Float.parseFloat(Float.toString(array[0].getValorAPagar()));
            //relatorio
            txtTotalDividas.setText(" " + Float.toString(totalDividas));
            txtMediaDividas.setText(" " + Float.toString(mediaDividas));
            txtTotalPagas.setText(" " + Float.toString(totalPagas));
            lblTotalNPagas.setText(" " + Float.toString(totalNPagas));
            // pessoas
            txtDevMaiorDivida.setText(" " + array[array.length - 1].getNomeDevedor() + " " + array[array.length - 1].getApelidoDevedor());
            txtDevMenorDivida.setText(" " + array[0].getNomeDevedor() + " " + array[0].getApelidoDevedor());
            //
            txtMaiorDivida.setText(" " + Float.toString(array[array.length - 1].getValorAPagar()));
            txtMenorDivida.setText(" " + Float.toString(array[0].getValorAPagar()));

            //
        } else {

            JOptionPane.showMessageDialog(rootPane, "Não há dívidas registadas até o momento!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void GerarRelatorio() {
        new RelatorioPdf().gerarRelatorio(txtTotalDividas.getText(), txtMediaDividas.getText(), txtTotalPagas.getText(),lblTotalNPagas.getText(),
                txtDevMenorDivida.getText(), ttt.getText(), txtMenorDivida.getText(), txtMaiorDivida.getText());
        JOptionPane.showMessageDialog(rootPane, "Relatório gerado com sucesso \nna pasta do projecto!");

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
        jLabel8 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        PiepanelChart = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ttt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaiorDivida = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDevMaiorDivida = new javax.swing.JLabel();
        txtMenorDivida = new javax.swing.JLabel();
        txtTotalPagas = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalNPagas = new javax.swing.JLabel();
        txtDevMenorDivida = new javax.swing.JLabel();
        txtMediaDividas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTotalDividas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        showBarChart = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        PiepanelChart1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ttt1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaiorDivida1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDevMaiorDivida1 = new javax.swing.JLabel();
        txtMenorDivida1 = new javax.swing.JLabel();
        txtTotalPagas1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTotalNPagas1 = new javax.swing.JLabel();
        txtDevMenorDivida1 = new javax.swing.JLabel();
        txtMediaDividas1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalDividas1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        showBarChart1 = new javax.swing.JPanel();

        jPanel1.setLayout(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ficha01/view/icons/FundoRegistarDividas.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(450, 20, 100, 120);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel4.setLayout(null);

        PiepanelChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PiepanelChart.setLayout(null);
        jPanel4.add(PiepanelChart);
        PiepanelChart.setBounds(10, 50, 390, 290);

        jLabel4.setText(" Cliente com maior dívida");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(430, 120, 160, 20);

        ttt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ttt.setForeground(new java.awt.Color(255, 51, 51));
        ttt.setText("     Raltorio");
        ttt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(ttt);
        ttt.setBounds(460, 50, 120, 40);

        jLabel9.setText("Valor da divida");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(430, 190, 100, 20);

        txtMaiorDivida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaiorDivida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtMaiorDivida);
        txtMaiorDivida.setBounds(430, 220, 80, 20);

        jLabel3.setText("Valorda divida");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(430, 300, 100, 20);

        txtDevMaiorDivida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDevMaiorDivida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtDevMaiorDivida);
        txtDevMaiorDivida.setBounds(430, 150, 180, 40);

        txtMenorDivida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMenorDivida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtMenorDivida);
        txtMenorDivida.setBounds(430, 320, 80, 20);

        txtTotalPagas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTotalPagas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtTotalPagas);
        txtTotalPagas.setBounds(80, 360, 60, 20);

        jLabel10.setText(" Total Pago");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(10, 360, 70, 20);

        jLabel6.setText("Total nao Pago");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(150, 360, 90, 20);

        lblTotalNPagas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalNPagas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(lblTotalNPagas);
        lblTotalNPagas.setBounds(240, 360, 70, 20);

        txtDevMenorDivida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDevMenorDivida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtDevMenorDivida);
        txtDevMenorDivida.setBounds(430, 250, 180, 40);

        txtMediaDividas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMediaDividas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtMediaDividas);
        txtMediaDividas.setBounds(430, 360, 80, 20);

        jLabel2.setText("Media da Dadividas");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(320, 360, 110, 20);

        txtTotalDividas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalDividas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtTotalDividas);
        txtTotalDividas.setBounds(520, 348, 90, 30);

        jLabel1.setText("        Total ");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(520, 320, 80, 20);

        jTabbedPane1.addTab("showPieChart", jPanel4);

        showBarChart.setLayout(null);
        jTabbedPane1.addTab("showBarChart", showBarChart);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel5.setLayout(null);

        PiepanelChart1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PiepanelChart1.setLayout(null);
        jPanel5.add(PiepanelChart1);
        PiepanelChart1.setBounds(10, 50, 390, 290);

        jLabel5.setText(" Cliente com maior dívida");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(430, 120, 160, 20);

        ttt1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ttt1.setForeground(new java.awt.Color(255, 51, 51));
        ttt1.setText("     Raltorio");
        ttt1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(ttt1);
        ttt1.setBounds(460, 50, 120, 40);

        jLabel11.setText("Valor da divida");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(430, 190, 100, 20);

        txtMaiorDivida1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMaiorDivida1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtMaiorDivida1);
        txtMaiorDivida1.setBounds(430, 220, 80, 20);

        jLabel7.setText("Valorda divida");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(430, 300, 100, 20);

        txtDevMaiorDivida1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDevMaiorDivida1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtDevMaiorDivida1);
        txtDevMaiorDivida1.setBounds(430, 150, 180, 40);

        txtMenorDivida1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMenorDivida1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtMenorDivida1);
        txtMenorDivida1.setBounds(430, 320, 80, 20);

        txtTotalPagas1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTotalPagas1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtTotalPagas1);
        txtTotalPagas1.setBounds(80, 360, 60, 20);

        jLabel12.setText(" Total Pago");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(10, 360, 70, 20);

        jLabel13.setText("Total nao Pago");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(150, 360, 90, 20);

        lblTotalNPagas1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalNPagas1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(lblTotalNPagas1);
        lblTotalNPagas1.setBounds(240, 360, 70, 20);

        txtDevMenorDivida1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDevMenorDivida1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtDevMenorDivida1);
        txtDevMenorDivida1.setBounds(430, 250, 180, 40);

        txtMediaDividas1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMediaDividas1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtMediaDividas1);
        txtMediaDividas1.setBounds(430, 360, 80, 20);

        jLabel14.setText("Media da Dadividas");
        jPanel5.add(jLabel14);
        jLabel14.setBounds(320, 360, 110, 20);

        txtTotalDividas1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalDividas1.setForeground(new java.awt.Color(255, 153, 0));
        txtTotalDividas1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.add(txtTotalDividas1);
        txtTotalDividas1.setBounds(520, 348, 90, 30);

        jLabel15.setText("        Total ");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(520, 320, 80, 20);

        jTabbedPane2.addTab("showPieChart", jPanel5);

        showBarChart1.setLayout(null);
        jTabbedPane2.addTab("showBarChart", showBarChart1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 639, 459);
    }// </editor-fold>//GEN-END:initComponents
    String dat;
    float totalDividas = 0, mediaDividas = 0, totalPagas = 0, totalNPagas = 0, DevMaiorDivida = 0, tMenorDivida = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PiepanelChart;
    private javax.swing.JPanel PiepanelChart1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblTotalNPagas;
    private javax.swing.JLabel lblTotalNPagas1;
    private javax.swing.JPanel showBarChart;
    private javax.swing.JPanel showBarChart1;
    private javax.swing.JLabel ttt;
    private javax.swing.JLabel ttt1;
    private javax.swing.JLabel txtDevMaiorDivida;
    private javax.swing.JLabel txtDevMaiorDivida1;
    private javax.swing.JLabel txtDevMenorDivida;
    private javax.swing.JLabel txtDevMenorDivida1;
    private javax.swing.JLabel txtMaiorDivida;
    private javax.swing.JLabel txtMaiorDivida1;
    private javax.swing.JLabel txtMediaDividas;
    private javax.swing.JLabel txtMediaDividas1;
    private javax.swing.JLabel txtMenorDivida;
    private javax.swing.JLabel txtMenorDivida1;
    private javax.swing.JLabel txtTotalDividas;
    private javax.swing.JLabel txtTotalDividas1;
    private javax.swing.JLabel txtTotalPagas;
    private javax.swing.JLabel txtTotalPagas1;
    // End of variables declaration//GEN-END:variables

}
