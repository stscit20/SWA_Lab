package de.hse.swa.SWA_Lab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hse.swa.SWA_Lab.model.*;
import de.hse.swa.SWA_Lab.servlet.*;
/**
 * Servlet implementation class Servlet
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int swauserids = 1;
	private Swauser swauser;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SWA_Lab");
    private EntityManager em = emf.createEntityManager();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubPrintWriter writer = response.getWriter();
		PrintWriter writer = response.getWriter();
		if(request.getParameter("username") == null) {
			writer.println("<html>");
			writer.println("<head><title>Testlogin</title></head>");
			writer.println("<body>");
			writer.println("	<form action=\"servlet\" method=\"GET\"> ");
			writer.println("	<div>Email: <input type=\"text\" name=\"mail\"</div> ");
			writer.println("	<div>Username: <input type=\"text\" name=\"username\"</div> ");
			writer.println("	<div> Passwort: <input type=\"password\" name=\"passwort\"> </div>");
			writer.println("	<div> <input type=\"submit\" value=\"Registrate\"> </div>");
			writer.println("	</form> ");
			writer.println("<body>");
			writer.println("</html>");
		}
			else {
	    }
		writer.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
