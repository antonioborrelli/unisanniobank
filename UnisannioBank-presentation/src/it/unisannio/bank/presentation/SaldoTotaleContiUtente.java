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
 * Servlet implementation class SaldoTotaleContiUtente
 */
@WebServlet("/SaldoTotaleContiUtente")
public class SaldoTotaleContiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaldoTotaleContiUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String api_key = request.getParameter("api_key");
		String total = String.valueOf(this.branch.totalAmountUser(api_key));

		response.getWriter().write(total);
		
	}

}
