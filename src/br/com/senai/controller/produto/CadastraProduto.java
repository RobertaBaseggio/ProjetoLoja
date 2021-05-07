package br.com.senai.controller.produto;

import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class CadastraProduto {

	Scanner scanner = new Scanner(System.in);
	
	public ProdutoModel cadastrarProduto() {

		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("--- CADASTRAR ITENS ---");
		System.out.println("Produto: ");
		scanner.next();
		produtoModel.setNomeDoProduto(scanner.nextLine());
		System.out.println("Preço: ");
		produtoModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.println("Quantidade: ");
		produtoModel.setQuantidadeDoProduto(scanner.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDoProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;

	}
}
