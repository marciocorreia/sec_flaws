import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.UnixCodec;

/**
 * Servlet implementation class UnsafeServlet
 */
@WebServlet("/SafeServlet")
public class SafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(SafeServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String input = request.getParameter("input");
		LOGGER.info("Received {} as parameter", ESAPI.encoder().encodeForOS(new UnixCodec(), input));

		Properties prop = new Properties();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LOGGER.info("Received {} as parameter", ESAPI.encoder().encodeForOS(new UnixCodec(), input));
		
		try {
            prop.load(getServletContext().getResourceAsStream("/WEB-INF/classes/DB.properties"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/eclipse/h2db/secflaws", prop);
			stmt = conn.prepareStatement("SELECT * FROM CUSTOMERS WHERE NAME = ?");
			stmt.setString(1, input);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("	<head><title>SecFlaws Project</title></head>");
			out.println("	<body>");
			out.println("		<h1>SecFlaws Project</h1>");
			out.println("		<p>input: ["+ ESAPI.encoder().encodeForHTML(input) +"]");
			out.println("		<table>");
			out.println("			<tr><th>Id</th><th>Name</th></tr>");
			while(rs != null && rs.next()) {
				out.println("			<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
			}
			out.println("		</table>");
			out.println("		<p><a href=\"index.jsp\">Back</a>");
			out.println("	</body>");
			out.println("</html>");
			stmt.close();
			conn.close();
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
