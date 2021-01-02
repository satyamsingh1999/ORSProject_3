/*package in.co.rays.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;


import com.sun.mail.iap.Response;


import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

*//**
 * Servlet implementation class JasperMarksheet
 *//*
@WebServlet(name= "JasperMarksheet" ,urlPatterns={"/ctl/JasperMarksheet"})
public class JasperMarksheet extends BaseCtl {

	select  role_no,name,physics,chemistry,maths,(physics+chemistry+maths) as total ,
	round(((physics+chemistry+maths)/3),2) as percentage  from st_marksheet where physics>33 and 
	chemistry>33 and maths>33 Order By desc limit 0,10
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("go get JasperMarksheet ");
		Connection con = null;
		JasperReport jr = null;
		JasperDesign jd = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");
			
			String database = rb.getString("DATABASE");
			
			if ("HIBERNATE".equals(database)) 
			{
				System.out.println("Hibernate Jasper");
				Session session = in.co.rays.util.HibDataSource.getSession();
				SessionFactoryImplementor factoryImplementor = (SessionFactoryImplementor) session.getSessionFactory();
				ConnectionProvider connectionProvider = factoryImplementor.getConnectionProvider();
				try {
					con = connectionProvider.getConnection();
				} catch (Exception e) {
 
				}
			}
			if ("JDBC".equals(database)) 
			{
				
				con = JDBCDataSource.getConnection();
			}
			Map map = new HashMap();
			String path = getServletContext().getRealPath("/WEB-INF/");
			System.out.println("path--- "+path);
			jd = JRXmlLoader.load("C:/Users/LENOVO/project3 workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Project_3/WEB-INF/MeritList.jrxml");
			jr= JasperCompileManager.compileReport(jd);
			byte[] bytestrem = JasperRunManager.runReportToPdf(jr, map,con);
			OutputStream os = resp.getOutputStream();
			resp.setContentType("application/pdf");
			resp.setContentLength(bytestrem.length);
			os.write(bytestrem, 0, bytestrem.length);
		} catch (Exception e) {
			
		}
	}
	@Override
	protected String getView() {
		return null;
	}
}
*/