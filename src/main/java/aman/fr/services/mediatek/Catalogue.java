package aman.fr.services.mediatek;

import aman.fr.persistantdata.modele.user.AUser;
import mediatek2021.Document;
import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Catalogue")
public class Catalogue extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Catalogue() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        AUser u = (AUser) request.getSession().getAttribute("utilisateur");
        int typeDocument = Integer.parseInt(request.getParameter("typeAjout"));
        if (u == null) {
            response.sendRedirect("/projet-app-web-java/Login");
        } else if (u.isBibliothecaire()) {
            response.sendRedirect("/projet-app-web-java/Login");
        } else {
            List<Document> documents;
            switch(typeDocument) {
                case 1:
                    documents.add(Mediatek.getInstance().catalogue(1));
                case 2:
                    documents.add(Mediatek.getInstance().catalogue(2));
                case 3:
                    documents.add(Mediatek.getInstance().catalogue(2));
                default:
                    throw new IllegalStateException("Error on loading a doc");
            }
            request.setAttribute("documents", documents);

            this.getServletContext().getRequestDispatcher("/catalogue.jsp").forward(request, response);
        }
    }
}
