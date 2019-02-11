package service;

import domain.models.service.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employee);

    List<EmployeeServiceModel> findAll();

    boolean removeEmployee(String id);
}
