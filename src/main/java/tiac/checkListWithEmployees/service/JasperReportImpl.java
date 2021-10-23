package tiac.checkListWithEmployees.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.repository.EmployeeCheckListRepository;

@Service
public class JasperReportImpl implements JasperReportService {

	@Autowired
	private EmployeeCheckListRepository employeeCheckRepo;

	public String exportReport( Long id) throws JRException, IOException {
		List<EmployeeCheckList> employeeCheck = employeeCheckRepo.findAllByEmployeeId(id);
		System.out.println(employeeCheck.get(0).getDone());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeCheck);
		Map<String, Object> params = new HashMap<>();
		params.put("Ljiljana", dataSource);

		InputStream is = this.getClass().getResource("/employeeReport.jrxml").openStream();
		JasperDesign jasperDesign = JRXmlLoader.load(is);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, dataSource);
		ByteArrayInputStream bais = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
		System.out.println(bais.read());
		JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\HP\\Desktop\\Report\\employeeCheck.pdf");

		return "employeeReport";


	}
}
