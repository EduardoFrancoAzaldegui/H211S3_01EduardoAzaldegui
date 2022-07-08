/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Date;


public class Venta {
    
    
    private int IDVEN;
    private Date FECVENT;
    private int TIPVENT;
    private int IDCLI;
    private int IDEMP;

    
    /**
     * @return the IDVEN
     */
    public int getIDVEN() {
        return IDVEN;
    }

    /**
     * @param IDVEN the IDVEN to set
     */
    public void setIDVEN(int IDVEN) {
        this.IDVEN = IDVEN;
    }

    /**
     * @return the FECVENT
     */
    public Date getFECVENT() {
        return FECVENT;
    }

    /**
     * @param FECVENT the FECVENT to set
     */
    public void setFECVENT(Date FECVENT) {
        this.FECVENT = FECVENT;
    }

    /**
     * @return the TIPVENT
     */
    public int getTIPVENT() {
        return TIPVENT;
    }

    /**
     * @param TIPVENT the TIPVENT to set
     */
    public void setTIPVENT(int TIPVENT) {
        this.TIPVENT = TIPVENT;
    }

    /**
     * @return the IDCLI
     */
    public int getIDCLI() {
        return IDCLI;
    }

    /**
     * @param IDCLI the IDCLI to set
     */
    public void setIDCLI(int IDCLI) {
        this.IDCLI = IDCLI;
    }

    /**
     * @return the IDEMP
     */
    public int getIDEMP() {
        return IDEMP;
    }

    /**
     * @param IDEMP the IDEMP to set
     */
    public void setIDEMP(int IDEMP) {
        this.IDEMP = IDEMP;
    }

    
    
}
