package it.unisannio.bank.presentation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisannio.bank.application.BranchLocal;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BranchLocal branch;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if ((request.getParameter("codicefiscale") != null && request.getParameter("codicefiscale").isEmpty())
				|| (request.getParameter("nome") != null && request.getParameter("nome").isEmpty())
				|| (request.getParameter("cognome") != null && request.getParameter("cognome").isEmpty())
				|| (request.getParameter("email") != null && request.getParameter("email").isEmpty())
				|| (request.getParameter("psw") != null && request.getParameter("psw").isEmpty())) {

			String codicefiscale = request.getParameter("codicefiscale");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String psw = request.getParameter("psw");

			this.branch.createUser(codicefiscale, nome, cognome, email, psw);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} else {
			System.out.println("ERROREEEEEEE");
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
