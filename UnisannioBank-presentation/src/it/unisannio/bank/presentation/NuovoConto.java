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
 * Servlet implementation class NuovoConto
 */
@WebServlet("/NuovoConto")
public class NuovoConto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoConto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		try{
			JSONObject obj = new JSONObject();
			String api_key = request.getParameter("api_key");
			double amount = Double.parseDouble(request.getParameter("amount"));
			if(api_key != null && !api_key.equals("")){
				User user = this.branch.getUserByApi_key(api_key);
				Account account = this.branch.createAccount(user.getEmail(), amount);
				if(account!= null){
					obj.put("error", "false");
					obj.put("message", "conto creato correttamente");
					JSONObject jsonAccount = new JSONObject();
					jsonAccount.put("id", account.getAccountId());
					jsonAccount.put("email", account.getEmail());
					jsonAccount.put("balance", account.getBalance());
					obj.put("account", jsonAccount);
				}else{
					obj.put("error", "true");
					obj.put("message", "si &egrave; verificato un errore riprovare pi&ugrave; tardi");
				}
			}else{
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
