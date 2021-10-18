package tiac.checkListWithEmployees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.entity.TimeFrame;
import tiac.checkListWithEmployees.entity.DTO.TimeFrameDTO;
import tiac.checkListWithEmployees.repository.TimeFrameRepository;

@Service
public class TimeFrameServiceImpl implements TimeFrameService {
	@Autowired
	TimeFrameRepository timeRepo;

	@Override
	public List<TimeFrame> getAllTimeFrame() {
		// TODO Auto-generated method stub
		return timeRepo.findAll();
	}

	@Override
	public TimeFrame removeTimeFrame(Long id) {
		if (timeRepo.existsById(id)) {
			timeRepo.delete(timeRepo.findById(id).get());
		}
		return null;
	}

	@Override
	public TimeFrame createTimeFrame(TimeFrameDTO newFrame) {
		TimeFrame newTimeFrame = new TimeFrame();
		newTimeFrame.setName(newFrame.getName());
		return timeRepo.save(newTimeFrame);
	}

	@Override
	public TimeFrame changeTimeFrame(Long id, TimeFrameDTO changedTimeFrame) {
		if (timeRepo.existsById(id))
			timeRepo.findById(id).get();
		TimeFrame time = timeRepo.findById(id).get();
		time.setName(changedTimeFrame.getName());
		timeRepo.save(time);
		return time;
	}

	@Override
	public TimeFrame findTimeFrame(Long id) {
		if (timeRepo.existsById(id)) {
			return timeRepo.findById(id).get();
		}return null;

	}
}
