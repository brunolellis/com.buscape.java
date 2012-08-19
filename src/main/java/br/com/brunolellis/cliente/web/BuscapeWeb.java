package br.com.brunolellis.cliente.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import br.com.brunolellis.cliente.local.BuscapeCliente;

import com.buscape.java.api.response.Offer;

public class BuscapeWeb extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		
		resp.getWriter().print("<html><head><title>Preços</title>");
		resp.getWriter().print("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		resp.getWriter().print("</head><body>");
		
		BuscapeCliente cliente = new BuscapeCliente();
		
		int ipad = 385727; // novo ipad 32gb wifi
		int nespresso = 86753;
		int dolceGusto = 298833;
		int samsungsIII = 397366;
		
		resp.getWriter().print("<pre>");
		
		resp.getWriter().println("*** iPad:");
		exibirOfertas(cliente.pesquisarOfertas(ipad), resp);
		resp.getWriter().println("<hr/>");
		resp.getWriter().println("*** Nespresso:");
		exibirOfertas(cliente.pesquisarOfertas(nespresso), resp);
		resp.getWriter().println("<hr/>");
		resp.getWriter().println("*** Dolce Gusto:");
		exibirOfertas(cliente.pesquisarOfertas(dolceGusto), resp);
		
		resp.getWriter().print("</pre></body></html>");
		
	}
	
	private void exibirOfertas(List<Offer> offers, HttpServletResponse resp) throws IOException {
		if (offers != null) {
			for (Offer offer : offers) {
				resp.getWriter().println(String.format("%d) %s = R$ %.2f", offer.getId(), offer.getSeller().getSellerName(), offer.getPrice().getValue()));
				
			}

		}		
		
	}
	
	public static void main(String[] args) throws Exception {
		int port = 5000;
		
		if (System.getenv("PORT") != null) {
			port = Integer.valueOf(System.getenv("PORT"));
			
		}
		
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(new BuscapeWeb()), "/*");
		server.start();
		server.join();
		
	}
	
}
