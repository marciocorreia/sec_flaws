

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UnsafeServlet
 */
@WebServlet("/SafeServlet")
public class SafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(UnsafeServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String input = request.getParameter("input");
		LOGGER.info("Received {} as parameter", input);
		
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("	<head><title>SecFlaws Project</title></head>");
			out.println("	<body>");
			out.println("		<h1>SecFlaws Project</h1>");
			out.println("		<p>input: ["+ input +"]");
			try (Connection conn = DriverManager.getConnection("jdbc:h2:~/eclipse/h2db/secflaws", "sa", "")){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMERS WHERE NAME = '"+input+"'");
				out.println("		<table>");
				out.println("			<tr><th>Id</th><th>Name</th></tr>");
				while(rs.next()) {
					out.println("			<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
				}
				out.println("		</table>");
				stmt.close();
				conn.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			out.println("		<p><a href=\"index.jsp\">Back</a>");
			out.println("	</body>");
			out.println("</html>");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
