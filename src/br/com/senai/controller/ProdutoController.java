package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.model.Carrinho;

public class ProdutoController {

	Scanner scanner = new Scanner(System.in);

	

	public int opcao() {
		System.out.print(">");
		return scanner.nextInt();
	}
	
	

	public void menu() {
		System.out.println("\n---MENU---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Ver carrinho");
		System.out.println("6) Sair do sistema");
		System.out.println("----------------------");
		

	}
	

	public void verCarrinho(List<Carrinho> listaCarrinho, List<ProdutoModel> produtos,String cliente) {

		ListaCarrinho listaCarrinhoItens = new ListaCarrinho();
		AdicionaItemNoCarrinho addCarrinho = new AdicionaItemNoCarrinho();
		
		listaCarrinhoItens.listarCarrinho(listaCarrinho);
		
		System.out.println("--- CARRINHO ---");
		System.out.println("1) Adicionar itens");
		System.out.println("2) Remover itens");
		System.out.println("3) Finalizar compra");
		System.out.println("4) Sair do carrinho");
		int opcaoCarrinho = scanner.nextInt();

		switch (opcaoCarrinho) {
		case 1:
			addCarrinho.adicionarCarrinho(listaCarrinho, produtos);
			break;
		case 2:
			removerCarrinho(listaCarrinho, produtos, cliente);
			break;
		case 3:
			finalizarCompra(listaCarrinho, produtos, cliente);
			break;
		case 4:
			menu();
			
			default:
				System.out.println("Opção inexistente");
				menu();
			break;
		}
	}

	
	public void removerCarrinho(List<Carrinho> listaCarrinho, List<ProdutoModel> produtos, String cliente) {
		
		ListaCarrinho listaCarrinhoItens = new ListaCarrinho();
		
		listaCarrinhoItens.listarCarrinho(listaCarrinho);
		
		System.out.println("--- REMOVER ---");
		System.out.print("Insira o Id do Produto: ");
		int idDoProdutoRemov = scanner.nextInt();
		
		
		
		if (idDoProdutoRemov > listaCarrinho.size()) {
			System.out.println("Esse produto não existe no seu carrinho");
			verCarrinho(listaCarrinho, produtos, cliente);
			return;
			
		}

		listaCarrinho.remove(idDoProdutoRemov - 1);
		verCarrinho(listaCarrinho, produtos, cliente);
	}

	public void finalizarCompra(List<Carrinho> listaCarrinho, List<ProdutoModel> produtos, String cliente) {
		
		ListaCarrinho listaCarrinhoItens = new ListaCarrinho();
		
		listaCarrinhoItens.listarCarrinho(listaCarrinho);
		
		System.out.println("--- FINALIZAR ---");
		System.out.println("Tem certeza que deseja finalizar a compra?");
		System.out.println("1) Sim");
		System.out.println("2) Não");
		int confirmacao = scanner.nextInt();

		if (confirmacao == 1) {
			listaCarrinho.clear();
			verCarrinho(listaCarrinho, produtos, cliente);
			listaCarrinhoItens.gerarCupom(listaCarrinho, cliente);
		} else {
			verCarrinho(listaCarrinho, produtos, cliente);
		}

	}
}
