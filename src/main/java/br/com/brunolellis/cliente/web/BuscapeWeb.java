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
		
		resp.setContentType("text/html; charset=iso-8859-1");
		resp.setCharacterEncoding("ISO-8859-1");
		
		resp.getWriter().print("<html><head><title>Preços</title>");
		resp.getWriter().print("<meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\">");
		resp.getWriter().print("<meta http-equiv=\"refresh\" content=\"7200\">");
		
		resp.getWriter().print("</head><body>");
		
		BuscapeCliente cliente = new BuscapeCliente();
		
		resp.getWriter().print("<pre>");
		
		int ipad_4_32gb_wifi = 477709;
		resp.getWriter().println("*** Apple iPad 4 Tela Retina Wi-Fi 32 GB:");
		exibirOfertas(cliente.pesquisarOfertas(ipad_4_32gb_wifi), resp);
		resp.getWriter().println("<hr/>");
		
		int ipad_4_16gb_wifi = 477706;
		resp.getWriter().println("*** Apple iPad 4 Tela Retina Wi-Fi 16 GB:");
		exibirOfertas(cliente.pesquisarOfertas(ipad_4_16gb_wifi), resp);
		resp.getWriter().println("<hr/>");
		
		int dolceGusto = 298833;
		resp.getWriter().println("*** Dolce Gusto:");
		exibirOfertas(cliente.pesquisarOfertas(dolceGusto), resp);
		resp.getWriter().println("<hr/>");
		
		int oDilemaDaInovacao = 1857680128;
		resp.getWriter().println("*** O Dilema da Inovacao - Clayton M. Christensen: ");
		exibirOfertas(cliente.pesquisarOfertas(oDilemaDaInovacao), resp);
		resp.getWriter().println("<hr/>");
		
		
		resp.getWriter().print("</pre></body></html>");
		
	}
	
	private void exibirOfertas(List<Offer> offers, HttpServletResponse resp) throws IOException {
		if (offers != null) {
			for (Offer offer : offers) {
				resp.getWriter().println(String.format("%d) %s = R$ %.2f", offer.getId(), offer.getSeller().getSellerName(), offer.getPrice().getValue()));
				
			}

		} else {
			resp.getWriter().println("Nenhuma oferta encontrada.");
			
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
