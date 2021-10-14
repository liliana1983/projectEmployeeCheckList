package tiac.checkListWithEmployees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tiac.checkListWithEmployees.repository.CheckListRepository;

@Service
public class CheckListServiceImpl implements CheckListService {
@Autowired
CheckListRepository checkRepo;
}
