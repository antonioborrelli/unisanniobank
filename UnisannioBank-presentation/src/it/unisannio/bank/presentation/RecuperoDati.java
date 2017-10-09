package it.unisannio.bank.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import it.unisannio.bank.application.BranchLocal;
import it.unisannio.bank.model.Account;
import it.unisannio.bank.model.User;

/**
 * Servlet implementation class RecuperoDati
 */
@WebServlet("/RecuperoDati")
public class RecuperoDati extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperoDati() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		try {

			
			JSONObject obj = new JSONObject();
			
			//CORPO
			String api_key = request.getParameter("api_key");
			if(api_key != null && !api_key.equals("")){
				User user = this.branch.getUserByApi_key(api_key);
				if(user != null){
//					ArrayList<Account> listAccount = this.branch.getUserAccounts(user.getEmail());
					user.setAccounts(this.branch.getUserAccounts(user.getEmail()));
					
					obj.put("error", "false");
					obj.put("message", "dati utente recuperati con successo");
					JSONObject jsonUser = new JSONObject();
					jsonUser.put("nome", user.getNome());
					jsonUser.put("cognome", user.getCognome());
					jsonUser.put("email", user.getEmail());
					jsonUser.put("codicefiscale", user.getCodicefiscale());
					obj.put("user", jsonUser);
					JSONArray arrayAccount = new JSONArray();
					if(user.getAccounts() != null)
						for(Account account : user.getAccounts()){
							JSONObject jsonAccount = new JSONObject();
							jsonAccount.put("id", account.getAccountId());
							jsonAccount.put("email", account.getEmail());
							jsonAccount.put("balance", account.getBalance());
							arrayAccount.put(jsonAccount);
						}
					
					obj.put("accounts", arrayAccount);
					
					
				}else{
					obj.put("error", "true");
					obj.put("message", "si &egrave; verificato un errore riprovare pi&ugrave; tardi");
				}
			}
			
			//INVIO RISPOSTA

			response.setContentType("application/json;charset=UTF-8");
			System.out.println(obj.toString());
			response.getWriter().write(obj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
