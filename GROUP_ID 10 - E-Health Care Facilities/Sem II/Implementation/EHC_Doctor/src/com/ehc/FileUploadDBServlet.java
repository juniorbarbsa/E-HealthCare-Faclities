package com.ehc;

import java.io.IOException;
import java.io.InputStream;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
// upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {

	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}

		Connection connection = null; // connection to the database
		String message = null; // message will be sent back to client
		System.out.println(inputStream);
		System.out.println("Conneting to Database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/appdb", "root", "");
			System.out.println("Connection Successful");

			// constructs SQL statement
			String sql = "INSERT INTO contacts (first_name, last_name, photo) values (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);

			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				statement.setBlob(3, inputStream);
			}

			// sends the statement to the database server
			int row = statement.executeUpdate();
			if (row > 0) {
				message = "File uploaded and saved into database";
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				// closes the database connection
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			// sets the message in request scope
			request.setAttribute("Message", message);

			// forwards to the message page
			getServletContext().getRequestDispatcher("/Message.jsp").forward(
					request, response);
		}
	}
}