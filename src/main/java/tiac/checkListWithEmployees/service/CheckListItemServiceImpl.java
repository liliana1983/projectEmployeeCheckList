package tiac.checkListWithEmployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.repository.CheckListItemRepository;

@Service
public class CheckListItemServiceImpl implements CheckListItemService {
@Autowired
CheckListItemRepository itemRepo; 
}
