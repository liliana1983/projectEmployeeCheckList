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

import tiac.checkListWithEmployees.entity.CheckListItemTemplate;
import tiac.checkListWithEmployees.entity.DTO.CheckListItemTemplateDTO;
import tiac.checkListWithEmployees.exception.ResourceNotFoundException;
import tiac.checkListWithEmployees.service.CheckListItemService;

@RestController
@RequestMapping(path = "/checkListItem")
public class checkListItemController {
	@Autowired
	private CheckListItemService itemService;
	@Autowired
	private ModelMapper modelMapper;

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody CheckListItemTemplateDTO newItem) {
		CheckListItemTemplate newCheckItem = itemService.createItem(newItem);
		CheckListItemTemplateDTO itemResponse = modelMapper.map(newCheckItem, CheckListItemTemplateDTO.class);
		return new ResponseEntity<CheckListItemTemplateDTO>(itemResponse, HttpStatus.CREATED);

	}

	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<List<CheckListItemTemplateDTO>> getAllCheckItem() {
		List<CheckListItemTemplateDTO> items = itemService.getAllItems().stream()
				.map(item -> modelMapper.map(item, CheckListItemTemplateDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<CheckListItemTemplateDTO>>(items, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getOneItem(@PathVariable Long id) {
		if (itemService.findItem(id).equals(null)) {
			throw new ResourceNotFoundException("Item doesnt exists!");
		}

		CheckListItemTemplate item = itemService.findItem(id);
		CheckListItemTemplateDTO itemResponse = modelMapper.map(item, CheckListItemTemplateDTO.class);
		return new ResponseEntity<CheckListItemTemplateDTO>(itemResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateCheckListItem(@PathVariable Long id,
			@RequestBody CheckListItemTemplateDTO changedItem) {
		if (itemService.findItem(id).equals(null)) {
			throw new ResourceNotFoundException("Item doesnt exists!");
		}
		CheckListItemTemplate item = itemService.changeItem(id, changedItem);
		CheckListItemTemplateDTO itemResponse = modelMapper.map(item, CheckListItemTemplateDTO.class);
		return new ResponseEntity<CheckListItemTemplateDTO>(itemResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delCheckItem(@PathVariable Long id) {
		if (itemService.remove(id).equals(null)) {
			throw new ResourceNotFoundException("Item doesnt exists or is already deleted!");
		}
		itemService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping(path = "/timeCheckList")
	public ResponseEntity<?> addTimeAndCheckList(@RequestParam Long timeId, @RequestParam Long checkId,
			@RequestParam Long itemId) {
		if (itemService.addCheckListAndTime(timeId, checkId, itemId).equals(null)) {
			throw new ResourceNotFoundException("Item, checkList or time frame not found!");
		}
		itemService.addCheckListAndTime(checkId, itemId, timeId);
		CheckListItemTemplate item = itemService.findItem(itemId);
		CheckListItemTemplateDTO itemResponse = modelMapper.map(item, CheckListItemTemplateDTO.class);
		return new ResponseEntity<CheckListItemTemplateDTO>(itemResponse, HttpStatus.OK);

	}

}