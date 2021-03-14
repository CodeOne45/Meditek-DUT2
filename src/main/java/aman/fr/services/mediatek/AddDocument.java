package aman.fr.services.mediatek;

import aman.fr.persistantdata.MediatekData;
import aman.fr.persistantdata.modele.user.AUser;
import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("AddDoc")
public class AddDocument extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddDocument() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException, ServletException {
        HttpSession session = request.getSession();
        request.setAttribute("typeAjout", request.getParameter("typeAjout"));
        AUser u = (AUser) session.getAttribute("utilisateur");

        if (u == null) {
            response.sendRedirect("/Login");
        } else if (!u.isBibliothecaire()) {
            response.sendRedirect("/Login");
        }
        else if(request.getParameter("typeAjout") == null) {
            response.sendRedirect("/Login");
        }
        else {
            this.getServletContext().getRequestDispatcher("addDoc.jsp").forward(request, response);;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeDocument = Integer.parseInt(request.getParameter("typeAjout"));

        Mediatek m = Mediatek.getInstance();

        String titre = request.getParameter("titre");
        String date = request.getParameter("date");

        switch(typeDocument) {
            case 1:
                String auteur = request.getParameter("auteur");
                try {
                    m.newDocument(typeDocument, titre, date, auteur);
                } catch (NewDocException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                String realisateur = request.getParameter("realisateur");
                String qualite = request.getParameter("qualite");
                try {
                    m.newDocument(2, titre, date, realisateur, qualite);
                } catch (NewDocException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                String artiste = request.getParameter("artiste");
                String genre = request.getParameter("genre");
                try {
                    m.newDocument(3, titre, date, genre,artiste);
                } catch (NewDocException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalStateException("Error on adding a doc");
        }

        response.sendRedirect("/Librarian");
    }
}
