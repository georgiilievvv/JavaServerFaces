package service;

import domain.entities.Employee;
import domain.models.service.EmployeeServiceModel;
import org.modelmapper.ModelMapper;
import repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employee) {
        try{
            this.employeeRepository.save(this.modelMapper.map(employee, Employee.class));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<EmployeeServiceModel> findAll() {
        return this.employeeRepository.findAll()
                .stream()
                .map(e -> this.modelMapper
                        .map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeEmployee(String id) {
        try {
            this.employeeRepository.remove(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}

