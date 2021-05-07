package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.model.Carrinho;

public class ListaCarrinho {

	
	public List<Carrinho> listarCarrinho(List<Carrinho> listaCarrinho) {
		System.out.println("--- CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s|\n", "ID", "Produto", " Preço ", " Qtd ", "R$ Total");

		if(listaCarrinho.size() == 0) {
			System.out.println("Sem produtos a serem listados ainda");
			return null;
			
		}
			for (int i = 0; i < listaCarrinho.size(); i++) {
				System.out.printf("| %2s | %10s | %8s | %5s | %9s|\n", i + 1, listaCarrinho.get(i).getNomeDoProduto(),
						listaCarrinho.get(i).getPrecoDoProduto(), listaCarrinho.get(i).getQuantidadeDoProduto(),
						listaCarrinho.get(i).getSaldoEmEstoque(), listaCarrinho.get(i).getValorTotalPorItem());

		}
		
		double valorTotalDocarrinho = 0;
		
		for(int i =0; i< listaCarrinho.size(); i++) {
			valorTotalDocarrinho+= listaCarrinho.get(i).getValorTotalPorItem();
		}
		
		System.out.println("Valor total: R$ " + valorTotalDocarrinho);
		
		
		return listaCarrinho;
	}
	
	public void gerarCupom(List<Carrinho> listaCarrinho, String cliente) {
		
		
		
		listarCarrinho(listaCarrinho);
		System.out.println("Cliente: " + cliente);
		
	}
}
