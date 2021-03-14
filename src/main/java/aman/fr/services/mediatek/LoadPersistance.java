package aman.fr.services.mediatek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet(value = "/LoadPersistence", loadOnStartup = 1)
public class LoadPersistance extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("aman.fr.persistantdata.MediatekData");
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
