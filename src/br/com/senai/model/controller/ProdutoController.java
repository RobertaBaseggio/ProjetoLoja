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
		System.out.println("Pre�o: ");
		produtoModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.println("Quantidade: ");
		produtoModel.setQuantidadeDoProduto(scanner.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDoProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;

	}

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %10s | %8s | %4s | %9s|\n", "Produto", " Pre�o ", " Qtd ", "R$ Total");

		produtos.forEach(produto -> {
			System.out.printf("| %10s | %8s | %5s | %9s|\n", produto.getNomeDoProduto(), produto.getPrecoDoProduto(),
					produto.getQuantidadeDoProduto(), produto.getSaldoEmEstoque());

		});
		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		System.out.println("---EDITAR---");
		System.out.print("Insira o ID do produto: ");
		idDoProduto = scanner.nextInt();

		System.out.println("---CAMPOS---");
		System.out.println("1) Nome do produto;");
		System.out.println("2) Pre�o unit�rio;");
		System.out.println("3) Quantidade.");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = scanner.nextInt();

		switch (indexDoCampo) {
		case 1:
			System.out.println("Informe o novo nome do produto: ");
			produto.setNomeDoProduto(scanner.next());
			
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			break;
		case 2:
			System.out.println("Informe o novo pre�o do produto: ");
			produto.setPrecoDoProduto(scanner.nextInt());
			
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			break;

		case 3:
			System.out.println("Informe a nova quantia do produto: ");
			produto.setQuantidadeDoProduto(scanner.nextInt());
			
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			break;

		default:

			break;

		}
		return null;
	}
}
