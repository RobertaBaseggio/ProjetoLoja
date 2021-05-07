package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {
	
	Scanner scanner = new Scanner(System.in);

	
	public void adicionarCarrinho(List<Carrinho> listaCarrinho, List<ProdutoModel> produtos) {
		Carrinho carrinho = new Carrinho();
		
		
		if(produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados");
		}
		
		System.out.println("--- ADICIONAR ---");
		System.out.println("Qual o id do item?");
		int idDoProduto = scanner.nextInt() - 1;
		System.out.println("Qual a quatidade desejada?");
		int quantidade = scanner.nextInt();
		
		if(Carrinho.getIdDoProduto() > produtos.size()) {
			System.out.println("Esse produto não está cadastrado");
		}
		
		carrinho.setIdDoProduto(idDoProduto);
		carrinho.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		carrinho.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		carrinho.setQuantidadeDoProduto(quantidade);
		carrinho.setSaldoEmEstoque(carrinho.getQuantidadeDoProduto() * carrinho.getQuantidadeDoProduto());
		carrinho.setValorTotalPorItem(carrinho.getQuantidadeDoProduto() * carrinho.getQuantidadeDoProduto());
		listaCarrinho.add(carrinho);
		
		atualizarQuantidadeEValorTotal(produtos, quantidade, idDoProduto);
		
	}

	public List<ProdutoModel> atualizarQuantidadeEValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto){
		ProdutoModel produto = new ProdutoModel();
		
		produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto() - quantidade);
		produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDoProduto());
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);
		
		return produtos;
	}
}
