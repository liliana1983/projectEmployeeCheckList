package tiac.checkListWithEmployees.service;

import java.util.List;

import tiac.checkListWithEmployees.entity.TimeFrame;
import tiac.checkListWithEmployees.entity.DTO.TimeFrameDTO;

public interface TimeFrameService {
	
public List<TimeFrame> getAllTimeFrame();
	
	public TimeFrame removeTimeFrame(Long id);
	
	public TimeFrame createTimeFrame(TimeFrameDTO newFrame);
	
	public TimeFrame changeTimeFrame(Long id, TimeFrameDTO changedTimeFrame);
	
	public TimeFrame findTimeFrame(Long id);
}
