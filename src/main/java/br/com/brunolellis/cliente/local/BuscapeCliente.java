package br.com.brunolellis.cliente.local;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.buscape.java.api.GrupoBuscape;
import com.buscape.java.api.buscape.BuscapeAPI;
import com.buscape.java.api.response.Category;
import com.buscape.java.api.response.Offer;
import com.buscape.java.api.response.Product;
import com.buscape.java.api.response.Result;

public class BuscapeCliente {

	//private static final String APP_ID = "5868384365533763337a383d";
	//private static final String APP_ID = "564771466d477a4458664d3d";
	//private static final String APP_ID = "c405d239-4599-3ef4-b79a-c986bd5f7792";
	
	private BuscapeAPI buscape;
	
	public BuscapeCliente() {
		String appid = "36686569316a69734762553d";
		appid = "7838643852314c587376343d";
		appid = "757347736264524b6936303d";
		buscape = new GrupoBuscape(appid).sandbox().buscape();
		
	}

	public static void main(String[] args) throws IOException {
		BuscapeCliente cliente = new BuscapeCliente();
		cliente.listagem();
	}
	
	private void listagem() throws IOException {
		exibirOfertas(528476, "Burigotto Matrix Evolution Até 25 Kg");
	}

	private void exibirOfertas(int codigo, String descricao) throws IOException {
		log("pesquisando ofertas para " + descricao);
		exibirOferta(pesquisarOfertas(codigo), descricao);
	}
	
	public List<Offer> pesquisarOfertas(int idProduto) {
		Result resultado = buscape.findOfferList().setProductId(idProduto).sortByPrice().call();

		if (resultado == null) {
			return null;
		}
		
		return resultado.getOffer();
	}
	
	public List<Product> pesquisarProduto(String keyword) {
		Result resultado = buscape.findProductList().setKeyword(keyword).sortByPrice().call();
		return resultado.getProduct();
		
	}
	
	public void exibirProduto(List<Product> products) {
		if (products == null) {
			return;
			
		}
		
		for (Product produto : products) {
			exibirProduto(produto);
			
		}
		
	}
	
	public void exibirProduto(Product produto) {
		if (produto == null) {
			return;
			
		}
		
		log(String.format("%d) %s (R$ %.2f ~ R$ %.2f)", produto.getId(),
				produto.getProductName(), produto.getPriceMin(), produto.getPriceMax()));
	}

	public void exibirOferta(List<Offer> offers, String descricao) {
		if (offers == null) {
			log("nenhuma oferta encontrada para " + descricao);
			return;
			
		}
		
		log(offers.size() + " ofertas encontradas para " + descricao);
		for (Offer offer : offers) {
			exibirOferta(offer);
			
		}
		
	}
	
	public void exibirOferta(Offer offer) {
		if (offer == null) {
			return;
			
		}
		
		System.out.println(String.format("%d) %s = R$ %.2f", offer.getId(), offer.getSeller().getSellerName(), offer.getPrice().getValue()));
//		if (offer.getLinks() != null) {
//			for (Link link : offer.getLinks()) {
//				System.out.println(link.getUrl());
//				
//			}
//			
//		}
//		
//		System.out.println("-----");
		
		
	}
	
	public List<Product> exibirProdutosTop() {
		return buscape.topProducts().call().getProduct();
		
	}
	
	public List<Category> pesquisarCategoria(String categoria) {
		Result resultado = buscape.findCategoryList().setKeyword(categoria).call();
		return resultado.getSubCategory();
		
	}
	
	public Category pesquisarCategoria(int id) {
		Result resultado = buscape.findCategoryList().setCategoryId(id).call();
		return resultado.getCategory();
		
	}

	
	public void exibirCategoria(List<Category> categorias) {
		if (categorias == null) {
			return;
			
		}
		
		for (Category categoria : categorias) {
			exibirCategoria(categoria);
			
		}

	}
	
	public void exibirCategoria(Category categoria) {
		System.out.println(String.format("%d) %s", categoria.getId(), categoria.getName()));
		
	}

	private void exibirDetalhesDoProduto(Product produto) {
		// TODO
		
	}

	private Product pesquisarDetalhesDoProduto(int id) {
		Result resultado = buscape.viewProductDetails().setProductId(id).call();
		if (resultado == null || resultado.getProduct() == null || resultado.getProduct().size() < 1) {
			return null;
			
		}
		
		return resultado.getProduct().get(0);
		
	}

	private void log(String mensagem) {
		System.out.println(LocalDateTime.now() + " - " + mensagem);
	}
}
