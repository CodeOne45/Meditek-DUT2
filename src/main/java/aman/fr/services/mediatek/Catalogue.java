package aman.fr.services.mediatek;

import aman.fr.persistantdata.modele.user.AUser;
import mediatek2021.Document;
import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Catalogue")
public class Catalogue extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Catalogue() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        AUser u = (AUser) session.getAttribute("utilisateur");
        String typeDocument = request.getParameter("type");

        if (u == null) {
            response.sendRedirect("/Login");
        } else if (!u.isBibliothecaire()) {
            response.sendRedirect("/Login");
        }else if (typeDocument == null) {
            List<Document> documentsList = Mediatek.getInstance().catalogue(4);
            request.setAttribute("documents", documentsList);
        }else{
            int docType = Integer.parseInt(typeDocument);
            List<Document> documentsList = Mediatek.getInstance().catalogue(docType);
            request.setAttribute("documents", documentsList);
        }
        this.getServletContext().getRequestDispatcher("/catalogue.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }
}
