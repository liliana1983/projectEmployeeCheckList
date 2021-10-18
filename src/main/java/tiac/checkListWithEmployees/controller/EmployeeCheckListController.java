package tiac.checkListWithEmployees.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.DTO.EmployeeCheckListDTO;
import tiac.checkListWithEmployees.exception.ResourceNotFoundException;
import tiac.checkListWithEmployees.service.EmployeeCheckListService;

@RestController
@RequestMapping(path = "/employeeCheckList")
public class EmployeeCheckListController {
	@Autowired
	EmployeeCheckListService employeeCheckService;

	@Autowired
	private ModelMapper modelMapper;

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> createEmployeeCheckList(@RequestParam Long checkId, @RequestParam Long employeeId) {
		List<EmployeeCheckList> newEmployeeCheck = employeeCheckService.createEmployeeCheckList(checkId,employeeId);
		//List<EmployeeCheckListDTO> newEmployeeCheckResponse = (List<EmployeeCheckListDTO>) modelMapper.map(newEmployeeCheck, EmployeeCheckListDTO.class);
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
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateEmployeeCheckList(@PathVariable Long id,
			@RequestBody EmployeeCheckListDTO changedEmployeeCheckList) {
		if (employeeCheckService.findEmployeeCheckList(id).equals(null)) {
			throw new ResourceNotFoundException("Employee Check list doesnt exists!");
		}
		EmployeeCheckList employeeCheckList = employeeCheckService.changeEmployeeCheckList(id,
				changedEmployeeCheckList);
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

//	@Secured({ "ROLE_ADMIN", "ROLE_CEO" })
//	@PutMapping(path = "/withEmployeeAndCheckList")
//	public ResponseEntity<?> connectEmployeeCheckWithEmployeeAndCheckList(@RequestParam Long employeeCheckId,
//			@RequestParam Long employeeId, @RequestParam Long checkId) {
//		if (employeeCheckService.connectEmployeeAndCheckListWithEmployeeCheckList(checkId, employeeId, employeeCheckId)
//				.equals(null))
//			throw new ResourceNotFoundException("One of the parameters do not exist or are missplaced");
//		employeeCheckService.connectEmployeeAndCheckListWithEmployeeCheckList(checkId, employeeId, employeeCheckId);
//		EmployeeCheckList employeeCheckList = employeeCheckService.findEmployeeCheckList(employeeId);
//		EmployeeCheckListDTO employeeCheckResponse = modelMapper.map(employeeCheckList, EmployeeCheckListDTO.class);
//		return new ResponseEntity<EmployeeCheckListDTO>(employeeCheckResponse, HttpStatus.OK);
//
//	}
}
