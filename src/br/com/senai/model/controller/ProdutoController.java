package br.com.senai.model.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

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

	public void ConsultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		for (ProdutoModel ProdutoModel : produtos) {
			System.out.println(ProdutoModel.toString());
		}
		
	}
	
}
