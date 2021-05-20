package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class RemoveCarrinho {
	
	Scanner scanner = new Scanner(System.in);
	
	VisualizaCarrinho visualizaCarrinho = new VisualizaCarrinho();
	ProdutoModel produto = new ProdutoModel();
	ResultSet resultSet;
	
	private Connection connection;
	
	public RemoveCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public boolean verifivaSeExisteProdutoNoCarrinho(int idDoProduto) {
		PreparedStatement preparedStatement;

		try {
			String sql = "Select * from itensNoCarrinho where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public void removerCarrinho( String cliente) {
		
		PreparedStatement preparedStatement;
		
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		Carrinho carrinho = new Carrinho();
		
		
		System.out.println("--- REMOVER ---");
		
		if (listaCarrinho.listarCarrinho() == null) {
			System.out.println("Não existem produtos cadastrados");
			return;
		}
		
		
		System.out.print("Insira o Id do Produto: ");
		int idDoProduto = scanner.nextInt();

		
		try {
			
			if (!verifivaSeExisteProdutoNoCarrinho(idDoProduto)) {
				return;
			}
			
			
			String sql = "delete from itensNoCarrinho where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação!");
		}
		
		try {
			
			
			
			String sql2 ="update produto set quantidadeEmEstoque = quantidade em estoque + ? where codigo = ? ";
			preparedStatement = connection.prepareStatement(sql2);
			
			preparedStatement.setInt(1, carrinho.getQuantidadeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
			
		}

	
}
