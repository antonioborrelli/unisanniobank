package it.unisannio.bank.presentation;

import java.io.IOException;
import java.io.PrintWriter;
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

		String output="Registrazione avvenuta con successo!!!";
		
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    try {
	        /* TODO output your response here.*/
	        out.println(output);
	    } finally {
	        out.close();
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String error = "false";
		String message = "";
		HashMap<String,String> risposta = new HashMap<>();
		
		if (	(request.getParameter("codicefiscale") != null && !request.getParameter("codicefiscale").equals(""))
				|| (request.getParameter("nome") != null && !request.getParameter("nome").equals(""))
				|| (request.getParameter("cognome") != null && !request.getParameter("cognome").equals(""))
				|| (request.getParameter("email") != null && !request.getParameter("email").equals(""))
				|| (request.getParameter("psw") != null && !request.getParameter("psw").equals(""))) {
			
			String codicefiscale = request.getParameter("codicefiscale");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String psw = request.getParameter("psw");
			User user = this.branch.getUser(email);
			if(user != null){
				error = "true";
				message = "utente gi&agrave; esistente";
			}else{
				
				try{
					user = this.branch.createUser(codicefiscale, nome, cognome, email, Converter.getMD5(psw));
					
					error = "false";
					message = "registrazione avvenuta con successo";
					risposta.put("nome", user.getNome());
					risposta.put("cognome", user.getCognome());
					risposta.put("email", user.getEmail());
					risposta.put("api_key", user.getApi_key());
					
				}catch(Exception e){
					error = "true";
					message = "errore di sistema riprovare più tardi.";
				}
			}
			
		}else{
			error = "true";
			message = "compilare correttamente tutti i campi!";
		}
		
		
		
		risposta.put("error", error);
		risposta.put("message", message);
		Gson gson = new Gson();
		String json = gson.toJson(risposta); 
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
		
		
//		if ((request.getParameter("codicefiscale") != null && request.getParameter("codicefiscale").isEmpty())
//				|| (request.getParameter("nome") != null && request.getParameter("nome").isEmpty())
//				|| (request.getParameter("cognome") != null && request.getParameter("cognome").isEmpty())
//				|| (request.getParameter("email") != null && request.getParameter("email").isEmpty())
//				|| (request.getParameter("psw") != null && request.getParameter("psw").isEmpty())) {
//
//			String codicefiscale = request.getParameter("codicefiscale");
//			String nome = request.getParameter("nome");
//			String cognome = request.getParameter("cognome");
//			String email = request.getParameter("email");
//			String psw = request.getParameter("psw");
//
//			this.branch.createUser(codicefiscale, nome, cognome, email, psw);
////			request.getRequestDispatcher("https://www.google.it").forward(request, response);
//			response.getWriter().println("Registrazione avvenuta con successo");
//
//		} else {
////			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
//			response.getWriter().println("Registrazione non avvenuta con successo");
//
//		}
//		doGet(request, response);
		
		
	}

}
