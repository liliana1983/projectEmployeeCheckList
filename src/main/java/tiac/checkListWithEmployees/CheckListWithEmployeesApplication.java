package tiac.checkListWithEmployees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheckListWithEmployeesApplication {
@Autowired
TestData testData;
	public static void main(String[] args) {
		SpringApplication.run(CheckListWithEmployeesApplication.class, args);
	}

}
