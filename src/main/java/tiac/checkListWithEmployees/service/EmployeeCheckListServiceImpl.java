package tiac.checkListWithEmployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.repository.EmployeeCheckListRepository;

@Service
public class EmployeeCheckListServiceImpl implements EmployeeCheckListService {
@Autowired
EmployeeCheckListRepository employeeCheckRepo;
}
