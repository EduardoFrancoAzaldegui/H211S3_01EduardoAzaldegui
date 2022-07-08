/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PC-EDUARDO
 */
public class Producto {
    
    
    
   private int IDPRO;
   private String NOMPRO;
   private double PREPRO;
   private int CANPRO;

    /**
     * @return the IDPRO
     */
    public int getIDPRO() {
        return IDPRO;
    }

    /**
     * @param IDPRO the IDPRO to set
     */
    public void setIDPRO(int IDPRO) {
        this.IDPRO = IDPRO;
    }

    /**
     * @return the NOMPRO
     */
    public String getNOMPRO() {
        return NOMPRO;
    }

    /**
     * @param NOMPRO the NOMPRO to set
     */
    public void setNOMPRO(String NOMPRO) {
        this.NOMPRO = NOMPRO;
    }

    /**
     * @return the PREPRO
     */
    public double getPREPRO() {
        return PREPRO;
    }

    /**
     * @param PREPRO the PREPRO to set
     */
    public void setPREPRO(double PREPRO) {
        this.PREPRO = PREPRO;
    }

    /**
     * @return the CANPRO
     */
    public int getCANPRO() {
        return CANPRO;
    }

    /**
     * @param CANPRO the CANPRO to set
     */
    public void setCANPRO(int CANPRO) {
        this.CANPRO = CANPRO;
    }

}
