package tiac.checkListWithEmployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.repository.TimeFrameRepository;

@Service
public class TimeFrameServiceImpl implements TimeFrameService {
@Autowired
TimeFrameRepository timeRepo;
}
