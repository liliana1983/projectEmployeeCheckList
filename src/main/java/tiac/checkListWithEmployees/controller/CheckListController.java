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
import org.springframework.web.bind.annotation.RestController;

import tiac.checkListWithEmployees.entity.CheckListTemplate;
import tiac.checkListWithEmployees.entity.DTO.CheckListTemplateDTO;
import tiac.checkListWithEmployees.exception.ResourceNotFoundException;
import tiac.checkListWithEmployees.service.CheckListService;

@RestController
@RequestMapping(path = "/checkList")
public class CheckListController {
	@Autowired
	CheckListService checkService;

	@Autowired
	private ModelMapper modelMapper;

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> createCheckList(@RequestBody CheckListTemplateDTO newCheckList) {
		CheckListTemplate newCheck = checkService.createCheckList(newCheckList);
		CheckListTemplateDTO newCheckResponse = modelMapper.map(newCheck, CheckListTemplateDTO.class);
		return new ResponseEntity<CheckListTemplateDTO>(newCheckResponse, HttpStatus.OK);

	}

	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<List<CheckListTemplateDTO>> getAllCheckList() {
		List<CheckListTemplateDTO> checkList = checkService.getAllCheckList().stream()
				.map(check -> modelMapper.map(check, CheckListTemplateDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<CheckListTemplateDTO>>(checkList, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getOneCheckList(@PathVariable Long id) {
		if (checkService.findCheckList(id).equals(null)) {
			throw new ResourceNotFoundException("Check list doesnt exists!");
		}
		CheckListTemplate checkList = checkService.findCheckList(id);
		CheckListTemplateDTO checkResponse = modelMapper.map(checkList, CheckListTemplateDTO.class);
		return new ResponseEntity<CheckListTemplateDTO>(checkResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateCheckListItem(@PathVariable Long id,
			@RequestBody CheckListTemplateDTO changedCheckList) {
		if (checkService.findCheckList(id).equals(null)) {
			throw new ResourceNotFoundException("Check list doesnt exists!");
		}
		CheckListTemplate checkList = checkService.changeCheckList(id, changedCheckList);
		CheckListTemplateDTO checkResponse = modelMapper.map(checkList, CheckListTemplateDTO.class);
		return new ResponseEntity<CheckListTemplateDTO>(checkResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delCheckList(@PathVariable Long id) {
		if (checkService.removeCheckList(id).equals(null)) {
			throw new ResourceNotFoundException("Item doesnt exists or is already deleted!");
		}
		checkService.removeCheckList(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
