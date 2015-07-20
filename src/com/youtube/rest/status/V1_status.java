package com.youtube.rest.status;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.youtube.dao.Oracle308tube;

@Path("/v1/status/")
public class V1_status {

	private static final String api_version = "00.01.00";

	@GET
	// @POST
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Services</> ";
	}

	@GET
	@Path("/version")
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: " + api_version + "</> ";

	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		try {
			DataSource dataSource = Oracle308tube.Oracle308tubeConn();
			System.out.println("dataSource.toString()=> "+dataSource.toString());
			conn = dataSource.getConnection(); // calls
																		// the
																		// method
																		// defined
																		// in
																		// the
																		// Oracle308tube
																		// package

			// simple sql query to return the date/time
			query = conn
					.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME "
							+ "from sys.dual");
			ResultSet rs = query.executeQuery();

			// loops through the results and save it into myString
			while (rs.next()) {
				System.out.println("Row Number " + rs.getRow() + ": "+ rs.getString("DATETIME"));
				myString = rs.getString("DATETIME");
			}
			query.close(); // close connection

			returnString = "<p>Database Status</p> "
					+ "<p>Database Date/Time return: " + myString + "</p>";
		} catch (Exception e) {
			e.printStackTrace();
		}/**
		 * The finally cause will always run. Even if the the method get a
		 * error. You want to make sure the connection to the database is
		 * closed.
		 */
		finally {
			if (conn != null)
				conn.close();
		}

		return returnString;
	}

}
