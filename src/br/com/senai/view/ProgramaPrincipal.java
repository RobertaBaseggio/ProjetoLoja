package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;
import br.com.senai.model.ProdutoModel;
import br.com.senai.model.controller.ProdutoController;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

		ProdutoController produtoController = new ProdutoController();

		boolean sair = false;

		do {
			produtoController.menu();
			int opcao = produtoController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				
				break;
				
			case 2:
				produtoController.listarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 9:
				sair = true;
				break;
			default:
				System.out.println("Opção Invalida");
				break;
			}

		} while (!sair);

		System.out.println("Programa Encerrado!!");
	}

}
