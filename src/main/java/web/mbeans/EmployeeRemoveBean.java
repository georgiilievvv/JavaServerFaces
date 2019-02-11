package web.mbeans;

import domain.models.service.EmployeeServiceModel;
import jdk.jfr.Name;
import org.modelmapper.ModelMapper;
import service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRemoveBean {

    private EmployeeServiceModel employeeServiceModel;

    private EmployeeService employeeService;

    public EmployeeRemoveBean() {
    }

    public void remove(String id) throws IOException {

        this.employeeService.removeEmployee(id);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
