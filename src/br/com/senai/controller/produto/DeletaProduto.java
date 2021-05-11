package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class DeletaProduto {
	
	Scanner scanner = new Scanner(System.in);

	
public void removerProduto(List<ProdutoModel> produtos) {
		
		ListaProduto listaItensProduto = new ListaProduto();
		
		if (produtos.size() == 0) {
			System.out.println("Estoque vazio");
			return;
		}

		listaItensProduto.listarProdutos();

		System.out.println("--- REMOVER ---");
		System.out.print("Insira o Id do Produto: ");
		int idDoProduto = scanner.nextInt();

		if (idDoProduto > produtos.size()) {
			System.out.println("Esse produto não existe");
			return;
		}

		produtos.remove(idDoProduto - 1);
	}
}
