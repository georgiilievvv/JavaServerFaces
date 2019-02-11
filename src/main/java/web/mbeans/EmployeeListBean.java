package web.mbeans;

import domain.models.view.EmployeeListViewModel;
import org.modelmapper.ModelMapper;
import service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    List<EmployeeListViewModel> employees = new ArrayList<>();

    private EmployeeService employeeService;
    private ModelMapper modelmapper;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelmapper) {
        this.employeeService = employeeService;
        this.modelmapper = modelmapper;
        this.employees = this.employeeService.findAll()
                .stream()
                .map(e -> this.modelmapper
                        .map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }
}
