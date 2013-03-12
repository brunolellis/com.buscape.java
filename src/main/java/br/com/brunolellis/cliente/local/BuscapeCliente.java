package br.com.brunolellis.cliente.local;

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

	public void exibirOferta(List<Offer> offers) {
		if (offers == null) {
			return;
			
		}
		
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

	public static void main(String[] args) {
		BuscapeCliente cliente = new BuscapeCliente();
		
		int ipad = 385727; // novo ipad 32gb wifi
		int nespresso = 86753;
		int dolceGusto = 298833;
		int samsungsIII = 397366;
		
		
		int ipad_4_32gb_wifi = 477709; // Apple iPad 4 Tela Retina Wi-Fi 32 GB
		int ipad_4_16gb_wifi = 477706; // Apple iPad 4 Tela Retina Wi-Fi 16 GB
		
		
		
		System.out.println("*** iPad");
		cliente.exibirOferta(cliente.pesquisarOfertas(ipad_4_16gb_wifi));
		/*
		
		System.out.println("\n*** Nespresso");
		cliente.exibirOferta(cliente.pesquisarOfertas(nespresso));
		
		System.out.println("\n*** Dolce Gusto");
		cliente.exibirOferta(cliente.pesquisarOfertas(dolceGusto));
		
		System.out.println("\n*** Pesquisa por 'dolce gusto'");
		cliente.exibirProduto(cliente.pesquisarProduto("dolce gusto"));
		
		System.out.println("\n*** Produtos Top");
		cliente.exibirProduto(cliente.exibirProdutosTop());
		
		System.out.println("\n*** Pesquisa pela categoria 'tablet'");
		cliente.exibirCategoria(cliente.pesquisarCategoria("tablet"));
		
		System.out.println("\n*** Pesquisa pela categoria 5839 (som automotivo)");
		cliente.exibirCategoria(cliente.pesquisarCategoria(5839));
		
		/* */
		
		cliente.exibirDetalhesDoProduto(cliente.pesquisarDetalhesDoProduto(samsungsIII));
		
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
