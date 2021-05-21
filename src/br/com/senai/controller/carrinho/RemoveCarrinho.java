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

	public void removerCarrinho(String cliente) {
		ProdutoModel produto = new ProdutoModel();

		PreparedStatement preparedStatement;

		ListaCarrinho listaCarrinho = new ListaCarrinho();
		Carrinho carrinho = new Carrinho();
		int quantidadeNoEstoque;

		System.out.println("--- REMOVER ---");

		if (listaCarrinho.listarCarrinho() == null) {
			System.out.println("Não existem produtos cadastrados");
			return;
		}

		System.out.print("Insira o codigo de Produto: ");
		int idDoProduto = scanner.nextInt();
		System.out.println("Qual a quantidade a ser removida?");
		int quantidade = scanner.nextInt();

		try {

			
			String sql = "Select * from produto where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return;

			} else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDoProduto(resultSet.getInt("quantidadeEmEstoque"));
				produto.setSaldoEmEstoque(resultSet.getInt("saldoEmEstoque"));
				
			}
			

			 sql = "Select * from itensNoCarrinho where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return;

			} else {
				carrinho.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				carrinho.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				carrinho.setQuantidadeDoProduto(resultSet.getInt("quantidadeEmEstoque"));
				carrinho.setSaldoEmEstoque(resultSet.getInt("saldoEmEstoque"));
				quantidadeNoEstoque = resultSet.getInt("saldoEmEstoque");
			}
			

			String sql2 = "update produto set quantidadeEmEstoque = ?, saldoEmEstoque = ? where codigo = ?";
			preparedStatement = connection.prepareStatement(sql2);

			preparedStatement.setDouble(1, produto.getQuantidadeDoProduto() + quantidade);
			preparedStatement.setDouble(2, produto.getPrecoDoProduto() *  (produto.getQuantidadeDoProduto() + quantidade));
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();

			
			if(quantidade == quantidadeNoEstoque) {
				String sql3 = "delete from itensNoCarrinho where codigoProduto = ?";
				preparedStatement = connection.prepareStatement(sql3);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.execute();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação!");
		}

	}

}
