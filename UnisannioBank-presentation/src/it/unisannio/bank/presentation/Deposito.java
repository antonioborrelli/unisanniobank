package it.unisannio.bank.presentation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import it.unisannio.bank.application.BranchLocal;
import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.User;

/**
 * Servlet implementation class Deposito
 */
@WebServlet("/Deposito")
public class Deposito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Deposito() {
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
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject obj = new JSONObject();
			String api_key = request.getParameter("api_key");
			int idaccount = Integer.parseInt(request.getParameter("idaccount"));
			double amount = Double.parseDouble(request.getParameter("amount"));
			double balance = Double.parseDouble(request.getParameter("balance"));
			if (api_key != null && !api_key.equals("")) {
				User user = this.branch.getUserByApi_key(api_key);
				if(user!=null) {
					Account account = this.branch.deposit(amount,idaccount);
					if(account!= null && account.getBalance()>balance) {
						obj.put("error", "false");
						obj.put("message", "Deposito effettuato correttamente");
						JSONObject jsonAccount = new JSONObject();
						jsonAccount.put("id", account.getAccountId());
						jsonAccount.put("email", account.getEmail());
						jsonAccount.put("balance", account.getBalance());
						obj.put("account", jsonAccount);
					}else {
						obj.put("error", "true");
						obj.put("message", "Impossibile effettuare il deposito");
					}
				}else {
					obj.put("error", "true");
					obj.put("message", "Non hai l'autorizzazione per effettuare il deposito");
				}
			} else {
				obj.put("error", "true");
				obj.put("message", "si &egrave; verificato un errore riprovare pi&ugrave; tardi");
			}

			response.setContentType("application/json;charset=UTF-8");
			System.out.println(obj.toString());
			response.getWriter().write(obj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
