package tiac.checkListWithEmployees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tiac.checkListWithEmployees.service.TimeFrameService;

@RestController
@RequestMapping(path = "/timeFrame")
public class TimeFrameController {
@Autowired
TimeFrameService timeService;

}
