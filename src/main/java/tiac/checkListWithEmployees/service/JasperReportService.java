package tiac.checkListWithEmployees.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public interface JasperReportService {
	String exportReport (Long id) throws FileNotFoundException, JRException, IOException;
}
