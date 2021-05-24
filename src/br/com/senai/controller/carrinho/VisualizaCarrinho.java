package br.com.senai.controller.carrinho;

import java.util.Scanner;


public class VisualizaCarrinho {

	Scanner scanner = new Scanner(System.in);
	
	public void verCarrinho(String cliente) {

		ListaCarrinho listaCarrinhoItens = new ListaCarrinho();
		AdicionaItemNoCarrinho addCarrinho = new AdicionaItemNoCarrinho();
		RemoveCarrinho removeCarrinho = new RemoveCarrinho();
		FinalizaCompra finalizaCompra = new FinalizaCompra();
		
		listaCarrinhoItens.listarCarrinho();
		
		System.out.println("--- CARRINHO ---");
		System.out.println("1) Adicionar itens");
		System.out.println("2) Remover itens");
		System.out.println("3) Finalizar compra");
		System.out.println("4) Sair do carrinho");
		int opcaoCarrinho = scanner.nextInt();

		switch (opcaoCarrinho) {
		case 1:
			addCarrinho.adicionarCarrinho();
			break;
		case 2:
			removeCarrinho.removerCarrinho( cliente);
			break;
		case 3:
			finalizaCompra.finalizarCompra( cliente);
			break;
		case 4:
			
			break;
			default:
				System.out.println("Opção inexistente");
			break;
		}
	}
}
