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
				System.out.println("Este produto n?o existe");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}


	public void removerCarrinho(String cliente) {
		ProdutoModel produto = new ProdutoModel();

		PreparedStatement preparedStatement;

		ListaCarrinho listaCarrinho = new ListaCarrinho();
		Carrinho carrinho = new Carrinho();
		int quantidadeNoEstoque;

		System.out.println("--- REMOVER ---");

		if (listaCarrinho.listarCarrinho() == null) {
			System.out.println("N?o existem produtos cadastrados");
			return;
		}

		System.out.print("Insira o codigo de Produto: ");
		int idDoProduto = scanner.nextInt();

		try {

			
			String sql = "Select * from itensNoCarrinho where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				System.out.println("N?o possui dados cadastrados.");
				return;

			} else {
				carrinho.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				carrinho.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				carrinho.setQuantidadeDoProduto(resultSet.getInt("quantidadeEmEstoque"));
				carrinho.setSaldoEmEstoque(resultSet.getDouble("saldoEmEstoque"));
				quantidadeNoEstoque = resultSet.getInt("quantidadeEmEstoque");
				preparedStatement.execute();
				
			}
			
			 sql = "Select * from produto where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("N?o possui dados cadastrados.");
				return;

			} else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDoProduto(resultSet.getInt("quantidadeEmEstoque"));
				produto.setSaldoEmEstoque(resultSet.getDouble("saldoEmEstoque"));
			}
			
				String sql2 = "delete from itensNoCarrinho where codigoProduto = ?";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.execute();
		

				sql = "update produto set quantidadeEmEstoque = ?, saldoEmEstoque = ? where codigo = ?";
				preparedStatement = connection.prepareStatement(sql);
		
				preparedStatement.setDouble(1, produto.getQuantidadeDoProduto() + quantidadeNoEstoque);
				preparedStatement.setDouble(2, produto.getPrecoDoProduto() *  (produto.getQuantidadeDoProduto() + quantidadeNoEstoque));
				preparedStatement.setInt(3, idDoProduto);
				preparedStatement.execute();
			
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("N?o foi poss?vel excluir essa informa??o!");
		}

	}

}
