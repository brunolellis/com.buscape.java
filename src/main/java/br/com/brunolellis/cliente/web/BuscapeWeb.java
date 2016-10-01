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
		
		resp.getWriter().print("<pre>");
		
		//exibirOfertas(606174, "Lenovo Z40-70 Intel Core i7-4500U 1.8 GHz 8192 MB 1024 GB", resp);
		//exibirOfertas(611724, "Lenovo Z40-70 Intel Core i7-4500U 1.8 GHz 16384 MB 1024 GB", resp);
		exibirOfertas(598166, "LG 23MP55HQ LED IPS 23.0 polegadas", resp);
		exibirOfertas(188619, "Assassin's Creed II Playstation 3 Blu-Ray", resp);
		exibirOfertas(188921, "Burigotto Bye Bye Simples", resp);
		
		
		resp.getWriter().print("</pre></body></html>");
		
	}
	
	private void novaLinha(HttpServletResponse resp) throws IOException {
		resp.getWriter().println("");
	}

	private void exibirOfertas(int codigo, String descricao, HttpServletResponse resp) throws IOException {
		resp.getWriter().println("<a href='http://www.buscape.com.br/prod_unico?idu=" + codigo + "' target='_blank'>");
		resp.getWriter().println(descricao);
		resp.getWriter().println("</a>");
		
		exibirOfertas(new BuscapeCliente().pesquisarOfertas(codigo), resp);
		novaLinha(resp);
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
