package tiac.checkListWithEmployees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tiac.checkListWithEmployees.service.CheckListItemService;

@RestController
@RequestMapping(path = "/checkListItem")
public class checkListItemController {
@Autowired
CheckListItemService itemService;
}
