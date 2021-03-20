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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/AddDoc")
public class AddDocument extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddDocument() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        HttpSession session = request.getSession();
        request.setAttribute("typeAjout", request.getParameter("typeAjout"));
        AUser u = (AUser) session.getAttribute("utilisateur");

        if (u == null) {
            response.sendRedirect("/Login");
        } else if (!u.isBibliothecaire()) {
            response.sendRedirect("/Login");
        }
        else {
            this.getServletContext().getRequestDispatcher("/addDoc.jsp").forward(request, response);;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeDocument = Integer.parseInt(request.getParameter("type"));
        String titre = request.getParameter("titre");
        String desc = request.getParameter("desc");

        List<Object> requestList = new ArrayList<>(Arrays.asList(titre, desc));

        switch(typeDocument) {
            case 1:
                requestList.add(request.getParameter("auteur"));
                break;
            case 2:
                requestList.add(request.getParameter("realisateur"));
                break;
            case 3:
                requestList.add(request.getParameter("artiste"));
                break;
            default:
                throw new IllegalStateException("Error on adding a doc");
        }

        try {
            Mediatek.getInstance().newDocument(typeDocument, requestList.toArray());
        }catch (NewDocException e){
            e.printStackTrace();
        }

        response.sendRedirect("/Librarian");
    }
}
