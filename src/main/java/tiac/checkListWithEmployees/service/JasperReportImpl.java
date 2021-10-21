package tiac.checkListWithEmployees.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
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
		String path = "C:\\Users\\HP\\Desktop\\Report";
		List<EmployeeCheckList> employeeCheck = employeeCheckRepo.findAllByEmployeeId(id);
		System.out.println(employeeCheck.get(0).getDescription());
		// load file and compile it

		// File file =
		// ResourceUtils.getFile("C:\\Users\\HP\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\checkListWithEmployees\\src\\main\\resources\\employeeReport.jrxml");
		// JasperReport jasperReport =
		// JasperCompileManager.compileReport(file.getPath());
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

//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//		if (reportFormat.equalsIgnoreCase("pdf")) {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "employeeCheck.pdf");
//
//		}
//		return "report generated in path :" + path;
//	}

	}
}
