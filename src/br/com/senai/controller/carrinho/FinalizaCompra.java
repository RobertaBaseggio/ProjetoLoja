package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class FinalizaCompra {

	Scanner scanner = new Scanner(System.in);
	
	VisualizaCarrinho visualizaCarrinho = new VisualizaCarrinho();
	
public void finalizarCompra(List<Carrinho> listaCarrinho, List<ProdutoModel> produtos, String cliente) {
		
		ListaCarrinho listaCarrinhoItens = new ListaCarrinho();
		
		listaCarrinhoItens.listarCarrinho();
		
		System.out.println("--- FINALIZAR ---");
		System.out.println("Tem certeza que deseja finalizar a compra?");
		System.out.println("1) Sim");
		System.out.println("2) Não");
		int confirmacao = scanner.nextInt();

		if (confirmacao == 1) {
			listaCarrinho.clear();
			visualizaCarrinho.verCarrinho(listaCarrinho, produtos, cliente);
			listaCarrinhoItens.gerarCupom(listaCarrinho, cliente);
		} else {
			visualizaCarrinho.verCarrinho(listaCarrinho, produtos, cliente);
		}

	}

}
