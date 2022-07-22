/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.Conexion;
import dao.daoVenta;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Venta;
import net.sf.jasperreports.engine.JasperRunManager;

public class Reporte extends Conexion{
    
    public void getReportePdf(String root, String numeroacta,String tipReport) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        try {
            System.out.println(root);
            File reportfile = new File(root);
            
       
            Map<String, Object> parameter = new HashMap<String, Object>();
            
            if(tipReport.equals("Venta")){
                System.out.println("Paso por parametro de reporte venta");
                parameter.put("Prueba","SI FUNCIONA :D");
            }
            
            
            byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, this.getConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(bytes.length);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
        public void getReportePdf(String root, String numeroacta,String tipReport,Venta venta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        try {
            System.out.println(root);
            File reportfile = new File(root);
            
       
            Map<String, Object> parameter = new HashMap<String, Object>();
            
            if(tipReport.equals("Venta")){
                daoVenta dao=new daoVenta();
                venta= dao.fillReport(venta);
                String id= String.valueOf(venta.getIDVEN());
                System.out.println("Este es el nuevo id del reporte "+id);
                dao.updateReporte(id);
                System.out.println("Paso por parametro de reporte venta");
                parameter.put("CLIENTE",venta.getNOMCLI());
                parameter.put("CELCLI",venta.getCELCLI());
                parameter.put("DIRCLI",venta.getDIRCLI());
                parameter.put("VENDEDOR",venta.getVENDEDOR());
                parameter.put("CELEMP",venta.getCELEMP());
                parameter.put("CORREO",venta.getCOREMP());
                parameter.put("TOTAL", venta.getTOTAL());
            }
            
            
            byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, this.getConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(bytes.length);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void exportPrograma(String ventaspdf, String boleta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
