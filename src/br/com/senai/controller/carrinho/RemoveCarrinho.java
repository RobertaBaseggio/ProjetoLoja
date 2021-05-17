package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class RemoveCarrinho {
	
	Scanner scanner = new Scanner(System.in);
	
	VisualizaCarrinho visualizaCarrinho = new VisualizaCarrinho();
	
	
	public void removerCarrinho(List<Carrinho> listaCarrinho, List<ProdutoModel> produtos, String cliente) {
		
		ListaCarrinho listaCarrinhoItens = new ListaCarrinho();
		
		listaCarrinhoItens.listarCarrinho();
		
		System.out.println("--- REMOVER ---");
		System.out.print("Insira o Id do Produto: ");
		int idDoProdutoRemov = scanner.nextInt();
		
		
		
		if (idDoProdutoRemov > listaCarrinho.size()) {
			System.out.println("Esse produto não existe no seu carrinho");
			visualizaCarrinho.verCarrinho(listaCarrinho, produtos, cliente);
			return;
			
		}

		listaCarrinho.remove(idDoProdutoRemov - 1);
		visualizaCarrinho.verCarrinho(listaCarrinho, produtos, cliente);
	}
}
