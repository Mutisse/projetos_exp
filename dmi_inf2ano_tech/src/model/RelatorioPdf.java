package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mutisse
 */
public class RelatorioPdf {

    private String dataf;
    private SimpleDateFormat sdf;

    public void gerarRelatorio(String total, String Maculino, String Femenino) {

        Document documento = new Document();

        Date data = new Date();
        sdf = new SimpleDateFormat("dd/MM/yyyy");

        Calendar c1 = Calendar.getInstance();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("src\\Usuarios Relatorio.pdf"));
            documento.open();
            documento.setPageSize(PageSize.A4);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("    ******  RELATÓRIO  ****** "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" |----------------------------|"));
            documento.add(new Paragraph(" | Masculino: " + Maculino + "|"));
            documento.add(new Paragraph(" |____________________________|"));
            documento.add(new Paragraph(" | Feminino:  " + Femenino + "|"));
            documento.add(new Paragraph(" |----------------------------|"));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("   Total de Usarios :  " + total));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));

            documento.add(new Paragraph("Data: " + sdf.format(data) + "  Local: * Maputo-Moçambique "));
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar documento.\nERRO: " + ex);
        } finally {
            documento.close();
        }
    }
}
