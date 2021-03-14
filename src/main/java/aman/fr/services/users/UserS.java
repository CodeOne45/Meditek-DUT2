package aman.fr.services.users;

import aman.fr.persistantdata.modele.user.AUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/User")
public class UserS extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserS(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        AUser u = (AUser) session.getAttribute("utilisateur");

        if( u == null) {
            response.sendRedirect("/Login");
        }
        else if(u.isBibliothecaire()) {
            response.sendRedirect("/Login");
        }
        else {
            this.getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
