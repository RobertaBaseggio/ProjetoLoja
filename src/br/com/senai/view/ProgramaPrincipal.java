package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.model.ProdutoModel;
import br.com.senai.pessoa.AdicionaPessoa;
import br.com.senai.controller.ProdutoController;
import br.com.senai.controller.carrinho.VisualizaCarrinho;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.Carrinho;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<Carrinho> listaCarrinho = new ArrayList<Carrinho>();

		ProdutoController produtoController = new ProdutoController();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaItensProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		AdicionaPessoa adicionaPessoa = new AdicionaPessoa();
		VisualizaCarrinho visualizaCarrinho = new VisualizaCarrinho();

		boolean sair = false;
		
		String cliente = adicionaPessoa.definirCliente();
		
		
		do {
			
			produtoController.menu();
			int opcao = produtoController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(cadastraProduto.cadastrarProduto());
				
				break;
				
			case 2:
				
				listaItensProduto.listarProdutos();
				
				break;
			case 3:
				editaProduto.editarProduto();
				break;
			case 4:
				deletaProduto.removerProduto();
				break;
			case 5: 
				visualizaCarrinho.verCarrinho(listaCarrinho, produtos, cliente);
				break;
			case 6:
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
