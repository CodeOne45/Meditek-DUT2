package aman.fr.services.mediatek;

import aman.fr.persistantdata.modele.user.AUser;
import mediatek2021.Mediatek;
import mediatek2021.SuppressException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteDoc")
public class DeleteDocument extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.setAttribute("typeAjout", request.getParameter("typeAjout"));
        AUser u = (AUser) session.getAttribute("utilisateur");

        if (u == null) {
            response.sendRedirect("/Login");
        } else if (!u.isBibliothecaire()) {
            response.sendRedirect("/Login");
        }
        else {
            this.getServletContext().getRequestDispatcher("/deleteDoc.jsp").forward(request, response);;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int docID = Integer.parseInt(id);

        try {
            Mediatek.getInstance().suppressDoc(docID);
        } catch (SuppressException e) {
            request.setAttribute("error", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/Librarian");
    }
}
