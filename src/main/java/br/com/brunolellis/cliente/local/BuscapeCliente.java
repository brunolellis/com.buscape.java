package br.com.brunolellis.cliente.local;

import java.io.IOException;
import java.util.List;

import com.buscape.java.api.GrupoBuscape;
import com.buscape.java.api.buscape.BuscapeAPI;
import com.buscape.java.api.response.Category;
import com.buscape.java.api.response.Offer;
import com.buscape.java.api.response.Product;
import com.buscape.java.api.response.Result;

public class BuscapeCliente {

	private static final String APP_ID = "2b504d467162784455724d3d";
	
	private BuscapeAPI buscape;
	
	public BuscapeCliente() {
		buscape = new GrupoBuscape(APP_ID).sandbox().buscape();
		
	}

	public static void main(String[] args) throws IOException {
		BuscapeCliente cliente = new BuscapeCliente();
		cliente.listagem();
	}
	
	private void listagem() throws IOException {
		exibirOfertas(606174, "Lenovo Z40-70 Intel Core i7-4500U 1.8 GHz 8192 MB 1024 GB");
		exibirOfertas(606194, "Lenovo Z40 Intel Core i7-4500U 1.8 GHz 16384 MB 1024 GB");
		exibirOfertas(510109, "Samsung Lava e seca Seine WD106UHSAWQ Frontal 10,1 Kg Branco");
		
		exibirOfertas(606726, "Smartphone Samsung Galaxy A5 SM-A500M Desbloqueado");
		
	}

	private void exibirOfertas(int codigo, String descricao) throws IOException {
		exibirOferta(pesquisarOfertas(codigo), descricao);
		System.out.println();
	}
	
	public List<Offer> pesquisarOfertas(int idProduto) {
		Result resultado = buscape.findOfferList().setProductId(idProduto).sortByPrice().call();
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
		
		System.out.println(String.format("%d) %s (R$ %.2f ~ R$ %.2f)", produto.getId(),
				produto.getProductName(), produto.getPriceMin(),
				produto.getPriceMax()));
			
		
	}

	public void exibirOferta(List<Offer> offers, String descricao) {
		if (offers == null) {
			return;
			
		}
		
		System.out.println(descricao);
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

}
