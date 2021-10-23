package tiac.checkListWithEmployees;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import tiac.checkListWithEmployees.Util.Encryption;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.repository.EmployeeRepository;
import tiac.checkListWithEmployees.repository.RoleRepository;


@SpringBootApplication
public class CheckListWithEmployeesApplication {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CheckListWithEmployeesApplication.class, args);
	}

	@Bean
	public TomcatServletWebServerFactory tomcatEmbedded() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
				// -1 means unlimited
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});
		return tomcat;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			/*
			 * if(!roleRepository.existsById(1)) { roleRepository.save(new
			 * RoleEntity(1,"ROLE_ADMIN")); roleRepository.save(new
			 * RoleEntity(2,"ROLE_CEO")); roleRepository.save(new
			 * RoleEntity(3,"ROLE_OFFICER")); }
			 */

			if (!employeeRepository.existsByUsername("ljisha")) {
				Employee admin = new Employee();
				admin.setName("Ljiljana");
				admin.setSurname("Curcic");
				admin.setPassword(Encryption.getPassEncoded("misika"));
				admin.setUsername("ljisha");
				admin.setPhoneNumber("0643699753");
				admin.setEducationLevel("VII");
				admin.setSocialSecurityNumber("2812983805010");
				admin.setEmail("ljisha@neobee.net");

				employeeRepository.save(admin);

			}
		};
	}
	
	    }    
	

