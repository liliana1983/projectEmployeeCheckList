package tiac.checkListWithEmployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
EmployeeRepository employeeRepo;
}
