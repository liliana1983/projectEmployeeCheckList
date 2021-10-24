package tiac.checkListWithEmployees.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tiac.checkListWithEmployees.entity.TimeFrame;
import tiac.checkListWithEmployees.entity.DTO.TimeFrameDTO;
import tiac.checkListWithEmployees.exception.ResourceNotFoundException;
import tiac.checkListWithEmployees.service.TimeFrameService;
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping(path = "/api/timeFrame")
public class TimeFrameController {
	@Autowired
	TimeFrameService timeService;

	@Autowired
	private ModelMapper modelMapper;

	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> createTimeFrame(@RequestBody TimeFrameDTO newTimeFrame) {
		TimeFrame newTime = timeService.createTimeFrame(newTimeFrame);
		TimeFrameDTO newTimeResponse = modelMapper.map(newTime, TimeFrameDTO.class);
		return new ResponseEntity<TimeFrameDTO>(newTimeResponse, HttpStatus.OK);

	}

	@Secured("ROLE_ADMIN")
	@GetMapping
	public ResponseEntity<List<TimeFrameDTO>> getAllCheckList() {
		List<TimeFrameDTO> timeList = timeService.getAllTimeFrame().stream()
				.map(time -> modelMapper.map(time, TimeFrameDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<List<TimeFrameDTO>>(timeList, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getOneTime(@PathVariable Long id) {
		if (timeService.findTimeFrame(id).equals(null)) {
			throw new ResourceNotFoundException("Time frame doesnt exists!");
		}
		TimeFrame time = timeService.findTimeFrame(id);
		TimeFrameDTO timeResponse = modelMapper.map(time, TimeFrameDTO.class);
		return new ResponseEntity<TimeFrameDTO>(timeResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateTimeFrame(@PathVariable Long id, @RequestBody TimeFrameDTO changedTimeFrame) {
		if (timeService.findTimeFrame(id).equals(null)) {
			throw new ResourceNotFoundException("Time frame doesnt exists!");
		}
		TimeFrame checkList = timeService.changeTimeFrame(id, changedTimeFrame);
		TimeFrameDTO timeResponse = modelMapper.map(checkList, TimeFrameDTO.class);
		return new ResponseEntity<TimeFrameDTO>(timeResponse, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delTimeFrame(@PathVariable Long id) {
		if (timeService.removeTimeFrame(id).equals(null)) {
			throw new ResourceNotFoundException("TimeFrame doesnt exists or is already deleted!");
		}
		timeService.removeTimeFrame(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
