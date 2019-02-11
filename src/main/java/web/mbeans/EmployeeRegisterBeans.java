package web.mbeans;

import domain.models.binding.EmployeeRegisterBindingModel;
import domain.models.service.EmployeeServiceModel;
import org.modelmapper.ModelMapper;
import service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBeans {

    private EmployeeRegisterBindingModel employeeRBM;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRegisterBeans() {
    }

    @Inject
    public EmployeeRegisterBeans(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        employeeRBM = new EmployeeRegisterBindingModel();
    }

    public EmployeeRegisterBindingModel getEmployeeRBM() {
        return employeeRBM;
    }

    public void setEmployeeRBM(EmployeeRegisterBindingModel employeeRBM) {
        this.employeeRBM = employeeRBM;
    }

    public void register() throws IOException {

        this.employeeService
                .saveEmployee(this.modelMapper.map(this.employeeRBM, EmployeeServiceModel.class));

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");

    }
}
