package br.com.senai.model.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;
import br.com.senai.model.carrinho;

public class ProdutoController {

	private Scanner scanner;

	public ProdutoController() {
		scanner = new Scanner(System.in);
	}

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
		System.out.println("5) Realizar venda");
		System.out.println("6) Sair do sistema");
		System.out.println("7) Ver carrinho");
		System.out.println("----------------------");

	}

	public ProdutoModel cadastrarProduto() {

		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("--- CADASTRAR ITENS ---");
		System.out.println("Produto: ");
		scanner.nextLine();
		produtoModel.setNomeDoProduto(scanner.nextLine());
		System.out.println("Preço: ");
		produtoModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.println("Quantidade: ");
		produtoModel.setQuantidadeDoProduto(scanner.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDoProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;

	}

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s|\n", "ID", "Produto", " Preço ", " Qtd ", "R$ Total");

		/*
		 * produtos.forEach(produto -> {
		 * 
		 * System.out.printf("| %2s | %10s | %8s | %5s | %9s|\n",
		 * produto.getNomeDoProduto(), produto.getPrecoDoProduto(),
		 * produto.getQuantidadeDoProduto(), produto.getSaldoEmEstoque());
		 * 
		 * });
		 */

		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | %8s | %5s | %9s|\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDoProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}

		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {

		if(produtos.size() > 0) {
			ProdutoModel produto = new ProdutoModel();
			int idDoProduto, indexDoCampo;
			
			listarProdutos(produtos);
			
			System.out.println("---EDITAR---");
			System.out.print("Insira o ID do produto: ");
			idDoProduto = scanner.nextInt();
			
			if(idDoProduto > produtos.size()) {
				System.out.println("Este produto não existe");
				
			}else {
				
			
				System.out.println("---CAMPOS---");
				System.out.println("1) Nome do produto;");
				System.out.println("2) Preço unitário;");
				System.out.println("3) Quantidade.");
				System.out.print("Informe o campo que deseja editar: ");
				indexDoCampo = scanner.nextInt() - 1;
		
				switch (indexDoCampo) {
				case 1:
					System.out.println("Informe o novo nome do produto: ");
					produto.setNomeDoProduto(scanner.next());
		
					produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
					produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
					produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
		
					produtos.set(idDoProduto, produto);
					break;
				case 2:
					System.out.println("Informe o novo preço do produto: ");
					produto.setPrecoDoProduto(scanner.nextInt());
		
					produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
					produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDoProduto() * produto.getPrecoDoProduto());
		
					produtos.set(idDoProduto, produto);
					break;
		
				case 3:
					System.out.println("Informe a nova quantia do produto: ");
					produto.setQuantidadeDoProduto(scanner.nextInt());
		
					produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDoProduto());
					produtos.set(idDoProduto, produto);
		
				default:
					System.out.println("Opção invalida!!!");
					break;
		
				}
			}
		} else {
			System.out.println("Estoque vazio");
		}
		return null;

	}
	
	public void removerProduto(List<ProdutoModel> produtos) {
		
		if(produtos.size()  == 0 ) {
			System.out.println("Estoque vazio");
			return;
		}
		
		listarProdutos(produtos);
		
		System.out.println("--- REMOVER ---");
		System.out.print("Insira o Id do Produto: ");
		int idDoProduto = scanner.nextInt();
		
		if(idDoProduto > produtos.size()) {
			System.out.println("Esse produto não existe");
			return;
		}
		
		produtos.remove(idDoProduto - 1);
	}
	
	public void verCarrinho(List<carrinho> ListaCarrinho, List<ProdutoModel> produtos) {
		
		System.out.println("--- CARRINHO ---");
		System.out.println("1) Adicionar itens");
		System.out.println("2) Remover itens");
		System.out.println("3) Finalizar compra");
		int opcaoCarrinho = scanner.nextInt();
		
		switch(opcaoCarrinho) {
		case 1:
			System.out.println("Qual o id do item?");
			int idDoProduto = scanner.nextInt();
			
			break;
		}
	}
}
