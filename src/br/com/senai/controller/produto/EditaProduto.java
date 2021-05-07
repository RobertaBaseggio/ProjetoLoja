package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class EditaProduto {

	Scanner scanner = new Scanner(System.in);
	
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		
		ListaProduto listaItensProduto = new ListaProduto();

		if (produtos.size() > 0) {
			ProdutoModel produto = new ProdutoModel();
			int idDoProduto, indexDoCampo;

			listaItensProduto.listarProdutos(produtos);

			System.out.println("---EDITAR---");
			System.out.print("Insira o ID do produto: ");
			idDoProduto = scanner.nextInt() - 1;

			if (idDoProduto > produtos.size()) {
				System.out.println("Este produto não existe");

			} else {

				System.out.println("---CAMPOS---");
				System.out.println("1) Nome do produto;");
				System.out.println("2) Preço unitário;");
				System.out.println("3) Quantidade.");
				System.out.print("Informe o campo que deseja editar: ");
				indexDoCampo = scanner.nextInt();

				switch (indexDoCampo) {
				case 1:
	
					editarNomeDoProduto(produtos, produto, idDoProduto);
					break;
				case 2:
					editarPrecoDoProduto(produtos, produto, idDoProduto);
					break;

				case 3:
					editarQuantidadeDoProduto(produtos, produto, idDoProduto);

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

	public ProdutoModel editarQuantidadeDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		System.out.println("Informe a nova quantia do produto: ");
		produto.setQuantidadeDoProduto(scanner.nextInt());

		produto.setSaldoEmEstoque(
				produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDoProduto());
		produtos.set(idDoProduto, produto);
		
		return produto;
	}
	

	public ProdutoModel editarPrecoDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		System.out.println("Informe o novo preço do produto: ");
		produto.setPrecoDoProduto(scanner.nextInt());

		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setSaldoEmEstoque(
				produtos.get(idDoProduto).getQuantidadeDoProduto() * produto.getPrecoDoProduto());

		produtos.set(idDoProduto, produto);
		return produto;
	}

	public ProdutoModel editarNomeDoProduto(List<ProdutoModel> produtos, ProdutoModel produto, int idDoProduto) {
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
		
		System.out.println("Informe o novo nome do produto: ");
		produto.setNomeDoProduto(scanner.next());

		produtos.set(idDoProduto, produto);
		return produto;
	}
}
