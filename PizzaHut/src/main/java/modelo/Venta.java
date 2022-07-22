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
    private int IDCLI;
    private int IDSUC;

    // VENTA_DETALLE
    private int CANTVEDET;
    private double SUBVEDET;
    private int IDPRO;

    // VENTA_EMPLEADO
    private int IDEMP;

    //VISTA CARRITO
    private String NOMPRO;
    private double PREPRO;
    private double SUBTOTAL;

    //VISTA VENTA
    private String NOMCLI;
    private String VENDEDOR;
    private double TOTAL;

    //VISTA VENTA_DETALLE
    private String DESPACHADOR;

    //CARRITO_VENTA
    private int IDCARVEN;
    private int IDEMVEN;
    private int IDEMDES;
    
    //VISTA REPORTE CAMPOS ESTATICOS
    private String CELCLI;
    private String DIRCLI;
    private String CELEMP;
    private String COREMP;

    /**
     * @return the IDSUC
     */
    public int getIDSUC() {
        return IDSUC;
    }

    /**
     * @param IDSUC the IDSUC to set
     */
    public void setIDSUC(int IDSUC) {
        this.IDSUC = IDSUC;
    }

    /**
     * @return the CANTVEDET
     */
    public int getCANTVEDET() {
        return CANTVEDET;
    }

    /**
     * @param CANTVEDET the CANTVEDET to set
     */
    public void setCANTVEDET(int CANTVEDET) {
        this.CANTVEDET = CANTVEDET;
    }

    /**
     * @return the SUBVEDET
     */
    public double getSUBVEDET() {
        return SUBVEDET;
    }

    /**
     * @param SUBVEDET the SUBVEDET to set
     */
    public void setSUBVEDET(double SUBVEDET) {
        this.SUBVEDET = SUBVEDET;
    }

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
     * @return the SUBTOTAL
     */
    public double getSUBTOTAL() {
        return SUBTOTAL;
    }

    /**
     * @param SUBTOTAL the SUBTOTAL to set
     */
    public void setSUBTOTAL(double SUBTOTAL) {
        this.SUBTOTAL = SUBTOTAL;
    }

    /**
     * @return the NOMCLI
     */
    public String getNOMCLI() {
        return NOMCLI;
    }

    /**
     * @param NOMCLI the NOMCLI to set
     */
    public void setNOMCLI(String NOMCLI) {
        this.NOMCLI = NOMCLI;
    }

    /**
     * @return the VENDEDOR
     */
    public String getVENDEDOR() {
        return VENDEDOR;
    }

    /**
     * @param VENDEDOR the VENDEDOR to set
     */
    public void setVENDEDOR(String VENDEDOR) {
        this.VENDEDOR = VENDEDOR;
    }

    /**
     * @return the TOTAL
     */
    public double getTOTAL() {
        return TOTAL;
    }

    /**
     * @param TOTAL the TOTAL to set
     */
    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    /**
     * @return the DESPACHADOR
     */
    public String getDESPACHADOR() {
        return DESPACHADOR;
    }

    /**
     * @param DESPACHADOR the DESPACHADOR to set
     */
    public void setDESPACHADOR(String DESPACHADOR) {
        this.DESPACHADOR = DESPACHADOR;
    }

    /**
     * @return the IDEMVEN
     */
    public int getIDEMVEN() {
        return IDEMVEN;
    }

    /**
     * @param IDEMVEN the IDEMVEN to set
     */
    public void setIDEMVEN(int IDEMVEN) {
        this.IDEMVEN = IDEMVEN;
    }

    /**
     * @return the IDEMDES
     */
    public int getIDEMDES() {
        return IDEMDES;
    }

    /**
     * @param IDEMDES the IDEMDES to set
     */
    public void setIDEMDES(int IDEMDES) {
        this.IDEMDES = IDEMDES;
    }

    /**
     * @return the IDCARVEN
     */
    public int getIDCARVEN() {
        return IDCARVEN;
    }

    /**
     * @param IDCARVEN the IDCARVEN to set
     */
    public void setIDCARVEN(int IDCARVEN) {
        this.IDCARVEN = IDCARVEN;
    }

    /**
     * @return the CELCLI
     */
    public String getCELCLI() {
        return CELCLI;
    }

    /**
     * @param CELCLI the CELCLI to set
     */
    public void setCELCLI(String CELCLI) {
        this.CELCLI = CELCLI;
    }

    /**
     * @return the DIRCLI
     */
    public String getDIRCLI() {
        return DIRCLI;
    }

    /**
     * @param DIRCLI the DIRCLI to set
     */
    public void setDIRCLI(String DIRCLI) {
        this.DIRCLI = DIRCLI;
    }

    /**
     * @return the CELEMP
     */
    public String getCELEMP() {
        return CELEMP;
    }

    /**
     * @param CELEMP the CELEMP to set
     */
    public void setCELEMP(String CELEMP) {
        this.CELEMP = CELEMP;
    }

    /**
     * @return the COREMP
     */
    public String getCOREMP() {
        return COREMP;
    }

    /**
     * @param COREMP the COREMP to set
     */
    public void setCOREMP(String COREMP) {
        this.COREMP = COREMP;
    }

}
