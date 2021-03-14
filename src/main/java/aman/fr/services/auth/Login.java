package aman.fr.services.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aman.fr.persistantdata.modele.user.AUser;
import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;


@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public Login() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// R�cup�ration de la session
		HttpSession session = request.getSession();

		// R�cup�ration des informations du formulaire
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		System.out.println(login +" " + password);

		try {
			Mediatek m = Mediatek.getInstance();
			System.out.println(m);
			Utilisateur u = m.getUser(login, password);
			session.setAttribute("utilisateur", u);
			System.out.println("yes!!!!!!");
			if(((AUser) u).isBibliothecaire()) {
				response.sendRedirect("/Librarian");
			}
			else {
				response.sendRedirect("/User");
			}
			
		} catch (Exception e) {
			request.setAttribute("erreur", "Login invalide");
			response.sendRedirect("/Login");
		}
		/*
		* catch (UserNotFound e) {
			request.setAttribute("erreur", "Login invalide");
			response.sendRedirect("/projet-app-web-java/Login");
		} catch (IncorectPwd e) {
			request.setAttribute("erreur", "Mot de passe invalide");
			response.sendRedirect("/projet-app-web-java/Login");
		}*/
	}
}
