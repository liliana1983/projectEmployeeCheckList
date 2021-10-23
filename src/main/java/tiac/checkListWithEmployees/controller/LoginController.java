package tiac.checkListWithEmployees.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tiac.checkListWithEmployees.Util.Encryption;
import tiac.checkListWithEmployees.entity.Employee;
import tiac.checkListWithEmployees.entity.DTO.EmployeeDTO;
import tiac.checkListWithEmployees.repository.EmployeeRepository;
import tiac.checkListWithEmployees.repository.RoleRepository;
@CrossOrigin(origins = "${client.url}")
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Value("${spring.security.secret-key}")
	private String secretKey;
	
	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;
	
	
	private String getJWTToken(Employee employee) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(employee.getRole().getRoleName().toString());
		String token = Jwts.builder().setId("softtekJWT").setSubject(employee.getUsername())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration))
				.signWith(SignatureAlgorithm.HS512, this.secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String pwd) {
	Employee employee = employeeRepo.findByUsername(username);
		if (employee != null && Encryption.validatePassword(pwd, employee.getPassword())) {
			String token = getJWTToken(employee);
			EmployeeDTO user = new EmployeeDTO();
			user.setUsername(username);
			user.setToken(token);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
	}

}
