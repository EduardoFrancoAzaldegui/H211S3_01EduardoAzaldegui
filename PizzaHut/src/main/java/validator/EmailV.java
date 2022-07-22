package validator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import dao.Conexion;

@FacesValidator(value = "emailV")
public class EmailV extends Conexion implements Validator {
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public boolean duplicadoEmailCliente(String email) {
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT CORCLI FROM CLIENTE WHERE CORCLI = '" + email + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en duplicadoEmail " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean duplicadoEmailEmpleado(String email) {
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT COREMP FROM EMPLEADO WHERE COREMP = '" + email + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en duplicadoEmail " + e.getMessage());
        }
        return false;
    }
    
    
    
    public String getEmailEmpleado(String ID) {
        String correo = "";
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT COREMP FROM EMPLEADO WHERE IDEMP = " + ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                correo = rs.getString("COREMP");
            }
            this.closeConeccion();
        } catch (Exception e) {
        }
        return correo;
    }

    
    public String getEmailCliente(String ID) {
        String correo = "";
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT CORCLI FROM CLIENTE WHERE IDCLI = " + ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                correo = rs.getString("CORCLI");
            }
            this.closeConeccion();
        } catch (Exception e) {
        }
        return correo;
    }
    
    

    
    public String getEmail(String ID,String tipo) {
        String correo = "";
        if (tipo.equals("CLIENTE")) {
            correo = getEmailCliente(ID);
        }else{
            System.out.println("Paso por empleado");
            correo = getEmailEmpleado(ID);
        }
        
        return correo;
    }

       
    @Override
    public void validate(FacesContext arg0,UIComponent arg1, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        String mail = ((String) value).trim();
        String id= String.valueOf(arg1.getAttributes().get("ids"));
        System.out.println("Este es el ID "+id);
        String tipo=  (String) arg1.getAttributes().get("tipo");
        String emailAntiguo=getEmail(id,tipo);
        System.out.println("Este es el correo "+emailAntiguo);
        
        if (mail.isEmpty()) {  
        } else {
            Matcher matcher = pattern.matcher(mail);
            if (!matcher.matches()) {
                FacesMessage msg = new FacesMessage("Email Incorrecto");
                throw new ValidatorException(msg);
            }
        }
        
        String email = (String) value;
        if (duplicadoEmailCliente(email) && !emailAntiguo.equals(email)) {
            throw new ValidatorException(new FacesMessage("Duplicado, el EMAIL ya existe"));
        }
        
        String emaill = (String) value;
        if (duplicadoEmailEmpleado(emaill) && !emailAntiguo.equals(emaill) ) {
            throw new ValidatorException(new FacesMessage("Duplicado, el EMAIL ya existe"));
        }
        
        
    }
    
    
}
