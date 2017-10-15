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

/**
 * Servlet implementation class Prelievo
 */
@WebServlet("/Prelievo")
public class Prelievo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prelievo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			JSONObject obj = new JSONObject();
			String api_key = request.getParameter("api_key");
			int idAccount = Integer.parseInt(request.getParameter("idAccount"));
			double cifra = Double.parseDouble(request.getParameter("cifra"));
			if(api_key != null && !api_key.equals("")){
				
				Account account = this.branch.withdraw(cifra, idAccount);
				if(account!= null){
					obj.put("error", "false");
					obj.put("message", "Prelievo effettuato correttamente");
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
				obj.put("message", "si &egrave; verificato un errore. Non si &egrave; autorizzati a compiere questa operazione");
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
