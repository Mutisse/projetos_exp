package ficha01.model;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable, Comparable{

    private String id;
    private String nomeDevedor;
    private String apelidoDevedor;
    private String genero;
    private String contacto;
    private Date data;
    private float valorDivida;
    private float valorAPagar;
    private float remanescente;
    private String estadoDivida;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeDevedor() {
        return nomeDevedor;
    }

    public void setNomeDevedor(String nomeDevedor) {
        this.nomeDevedor = nomeDevedor;
    }

    public String getApelidoDevedor() {
        return apelidoDevedor;
    }

    public void setApelidoDevedor(String apelidoDevedor) {
        this.apelidoDevedor = apelidoDevedor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(float valorDivida) {
        this.valorDivida = valorDivida;
    }

    public float getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(float valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public float getRemanescente() {
        return remanescente;
    }

    public void setRemanescente(float remanescente) {
        this.remanescente = remanescente;
    }

    public String getEstadoDivida() {
        return estadoDivida;
    }

    public void setEstadoDivida(String estadoDivida) {
        this.estadoDivida = estadoDivida;
    }

   
  

  public void setEstadoDivida(boolean estado) {
        if(estado == true)
            this.estadoDivida = "Pago";
        else
            this.estadoDivida = "Não Pago";  
        
            
    }

    @Override
 public int compareTo(Object o) {
        Cliente outro = (Cliente) o;
        if (getValorAPagar() < outro.getValorAPagar()) {
            return -1;
        }
        if (getValorAPagar() > outro.getValorAPagar()) {
            return 1;
        }
        return 0;
    }
   
}
