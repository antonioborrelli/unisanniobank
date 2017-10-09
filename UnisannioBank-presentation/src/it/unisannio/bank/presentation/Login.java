package it.unisannio.bank.presentation;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisannio.bank.application.BranchLocal;
import it.unisannio.bank.model.User;
import it.unisannio.bank.utility.Converter;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String error = "false";
		String message = "";
		HashMap<String,String> risposta = new HashMap<>();
		
		//CORPO
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username != null && !username.equals("") && password != null && !password.equals("")){
			User user = this.branch.getUser(username);
			if(user != null){
				if(user.getPassword().equals(Converter.getMD5(password))){
					error = "false";
					message = "login avvenuto con successo";
					risposta.put("nome", user.getNome());
					risposta.put("cognome", user.getCognome());
					risposta.put("email", user.getEmail());
					risposta.put("api_key", user.getApi_key());
				}else{
					error = "true";
					message = "password errata!";
				}
					
			}else{
				error = "true";
				message = "username inesistente!";
			}
		}else{
			error = "true";
			message = "compilare correttamente tutti i campi!";
		}
		
		
		//INVIO RISPOSTA
		risposta.put("error", error);
		risposta.put("message", message);
		Gson gson = new Gson();
		String json = gson.toJson(risposta); 
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
		
	}

}
