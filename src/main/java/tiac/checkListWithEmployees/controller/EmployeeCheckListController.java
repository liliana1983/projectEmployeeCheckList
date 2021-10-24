package tiac.checkListWithEmployees.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.DTO.EmployeeCheckListDTO;
import tiac.checkListWithEmployees.exception.ResourceNotFoundException;
import tiac.checkListWithEmployees.service.EmployeeCheckListService;
import tiac.checkListWithEmployees.service.JasperReportService;

@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping(path = "/api/employeeCheckList")
public class EmployeeCheckListController {
	@Autowired
	EmployeeCheckListService employeeCheckService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	JasperReportService jasperReport;

	@Secured({"ROLE_ADMIN","ROLE_CEO"})
	@PostMapping
	public ResponseEntity<?> createEmployeeCheckList(@RequestParam Long checkId, @RequestParam Long employeeId) {
		List<EmployeeCheckList> newEmployeeCheck = employeeCheckService.createEmployeeCheckList(checkId, employeeId);
		return new ResponseEntity<List<EmployeeCheckList>>(newEmployeeCheck, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<List<EmployeeCheckListDTO>> getAllCheckList() {
		List<EmployeeCheckListDTO> employeeCheckList = employeeCheckService.getAllEmployeeCheckLists().stream()
				.map(check -> modelMapper.map(check, EmployeeCheckListDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<EmployeeCheckListDTO>>(employeeCheckList, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getOneEmployeeCheckList(@PathVariable Long id) {
		if (employeeCheckService.findEmployeeCheckList(id).equals(null)) {
			throw new ResourceNotFoundException("Employee Check list doesnt exists!");
		}
		EmployeeCheckList employeeCheckList = employeeCheckService.findEmployeeCheckList(id);
		EmployeeCheckListDTO employeeCheckResponse = modelMapper.map(employeeCheckList, EmployeeCheckListDTO.class);
		return new ResponseEntity<EmployeeCheckListDTO>(employeeCheckResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delEmployeeCheckList(@PathVariable Long id) {
		if (employeeCheckService.deleteEmployeeCheckList(id).equals(null)) {
			throw new ResourceNotFoundException("Item doesnt exists or is already deleted!");
		}
		employeeCheckService.deleteEmployeeCheckList(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/UnChecked")
	public ResponseEntity<?> getAllNotFullyCheckedLists() {
		List<CheckListTemplate> listUnchecked = employeeCheckService.getAllNotChecked();
		return new ResponseEntity<>(listUnchecked, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_EMPLOYEE" })
	@PatchMapping(path = "/checkings")
	public ResponseEntity<?> checkIsChecked(@RequestParam Long employeeCheckId) {
		EmployeeCheckList lista = employeeCheckService.setIfIsChecked(employeeCheckId);

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CEO" })
	@GetMapping(path = "/UncheckedItems")

	public ResponseEntity<?> seeUncheckedItems() {
		List<String> uncheckedItems = employeeCheckService.findUncheckedItemNames();
		return new ResponseEntity<>(uncheckedItems, HttpStatus.OK);

	}

	@Secured({ "ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_CEO" })
	@GetMapping(path = "/checkedItems")

	public ResponseEntity<?> seeCheckedItems() {
		List<String> checkedItems = employeeCheckService.findCheckedItemNames();
		return new ResponseEntity<>(checkedItems, HttpStatus.OK);

	}

	@Secured({ "ROLE_ADMIN", "ROLE_CEO" })
	@GetMapping(path = "/employees")
	public ResponseEntity<?> seeEmployeesWithUncheckedLists() {
		List<Employee> employees = employeeCheckService.employeesWhoDidntCheckAll();
		return new ResponseEntity<>(employees, HttpStatus.OK);

	}

	@Secured({ "ROLE_ADMIN", "ROLE_EMPLOYEE" })
	@GetMapping(path = "/personalCheckLists")
	public ResponseEntity<?> getReports(@RequestParam String username) {
		List<EmployeeCheckList> myList = employeeCheckService.assignedCheckListsToEmployee(username);
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_EMPLOYEE" })
	@GetMapping(path = "/checklist")
	public ResponseEntity<?> getReportsPdf(@RequestParam Long id)
			throws FileNotFoundException, JRException, IOException {
		String report = jasperReport.exportReport(id);
		System.out.println(report);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_EMPLOYEE" })
	@GetMapping(path = "/templates")
	public ResponseEntity<?> getTemplates(@RequestParam Boolean type) {
		List<EmployeeCheckList> templates = employeeCheckService.getTemplateForEmployee(type);
		return new ResponseEntity<>(templates, HttpStatus.OK);
	}
}
