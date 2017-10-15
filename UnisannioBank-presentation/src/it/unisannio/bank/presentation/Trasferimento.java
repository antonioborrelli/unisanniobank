package it.unisannio.bank.presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.unisannio.bank.application.BranchLocal;
import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.User;

@SuppressWarnings("serial")
@WebServlet("/Trasferimento")
public class Trasferimento extends HttpServlet {

	@EJB
	private BranchLocal branch;

	public Trasferimento() {
		// TODO Auto-generated constructor stub

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try 
		{
			JSONObject obj = new JSONObject();
			String api_key = request.getParameter("api_key");
			int idDest = Integer.parseInt(request.getParameter("idDest"));
			int idSource = Integer.parseInt(request.getParameter("idSource"));
			double amount = Double.parseDouble(request.getParameter("amount"));

			if (api_key != null && !api_key.equals("")) {
				User user = this.branch.getUserByApi_key(api_key);
				if(user!=null) {
					Account dest = this.branch.getAccount(idDest);
					if(dest!=null){
						ArrayList<Account> accounts = this.branch.transfer(idSource, idDest, amount);
						if(accounts!= null) {
							obj.put("error", "false");
							obj.put("message", "Trasferimento effettuato correttamente");
							JSONArray listJson = new JSONArray();
							for(Account account : accounts) {
								JSONObject jsonAccount = new JSONObject();
								jsonAccount.put("id", account.getAccountId());
								jsonAccount.put("email", account.getEmail());
								jsonAccount.put("balance", account.getBalance());
								listJson.put(jsonAccount);
							}
							obj.put("accounts", listJson);
						}else {
							obj.put("error", "true");
							obj.put("message", "Impossibile effettuare il deposito");
						}
					}else{
						obj.put("error", "true");
						obj.put("message", "Numero di conto errato!");
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
