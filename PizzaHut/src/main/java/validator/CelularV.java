package validator;

import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import dao.Conexion;
//https://www.youtube.com/watch?v=T4CdPISRHFY

@FacesValidator(value = "celularV")
public class CelularV extends Conexion implements Validator {
    

    public boolean duplicadoCelularCliente(String celular) {
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT CELCLI FROM CLIENTE WHERE CELCLI = '" + celular + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            this.closeConeccion();
        } catch (Exception e) {
            System.out.println("Error en duplicadoCelular " + e.getMessage());
        }
        return false;
    }

    
    public boolean duplicadoCelularEmpleado(String celular) {
        try {
            conexion = getConnection();
            ps = conexion.prepareStatement("SELECT CELEMP FROM EMPLEADO WHERE CELEMP='" + celular + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            this.closeConeccion();
        } catch (Exception e) {
            System.out.println("Error en duplicadoCelular " + e.getMessage());
        }
        return false;
    }
    
    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String numero = value.toString().trim();
        String numeroAntiguo= String.valueOf(component.getAttributes().get("telefono"));
        
        if (numero.length() != 0 && numero.length() < 10) {
            String formato = "^9\\d\\d\\d\\d\\d\\d\\d\\d$";
            boolean val = Pattern.matches(formato, numero);
            if (!val) {
                throw new ValidatorException(new FacesMessage("El numero de Celular debe contener 9 digitos"));
            }
        }
        
        String celular = (String) value;
        if (duplicadoCelularCliente(celular) && !numeroAntiguo.equals(celular)) {
            throw new ValidatorException(new FacesMessage("Duplicado, el CELULAR ya existe"));
        }
        
         String celularr = (String) value;
        if (duplicadoCelularEmpleado(celularr) && !numeroAntiguo.equals(celularr)) {
            throw new ValidatorException(new FacesMessage("Duplicado, el CELULAR ya existe"));
        }
        

    }
}
