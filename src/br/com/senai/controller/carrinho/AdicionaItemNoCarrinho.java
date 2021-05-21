package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.Carrinho;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {

	Scanner scanner = new Scanner(System.in);

	private Connection connection;

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void adicionarCarrinho() {
		
		
		PreparedStatement preparedStatement;
		ProdutoModel produto = new ProdutoModel();
		Carrinho carrinho = new Carrinho();
		ResultSet resultSet;

		System.out.println("--- ADICIONAR ---");
		System.out.println("Qual o id do item?");
		int idDoProduto = scanner.nextInt();
		int CodigoDoProduto = idDoProduto;
		System.out.println("Qual a quatidade desejada?");
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
				produto.setSaldoEmEstoque(resultSet.getDouble("saldoEmEstoque"));
			}

	
			String sql2 = "update produto set quantidadeEmEstoque = ?, saldoEmEstoque = ? where codigo = ?";
			preparedStatement = connection.prepareStatement(sql2);

			
			preparedStatement.setDouble(1, produto.getQuantidadeDoProduto() - quantidade);
			preparedStatement.setDouble(2, produto.getPrecoDoProduto() * (produto.getQuantidadeDoProduto() - quantidade));
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		try {
			
			String sql = "Select * from produto where codigo = ?";
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
				carrinho.setSaldoEmEstoque(produto.getPrecoDoProduto() * quantidade);
			}

				String sql2 = "INSERT INTO itensnocarrinho(codigoProduto, nomeDoProduto, precoDoProduto, quantidadeEmEstoque, saldoEmEstoque) VALUES (?,?, ?, ?, ?)";
				PreparedStatement prepareStatement = connection.prepareStatement(sql2);
				
				prepareStatement.setInt(1, CodigoDoProduto);
				prepareStatement.setString(2, produto.getNomeDoProduto());
				prepareStatement.setDouble(3, produto.getPrecoDoProduto());
				prepareStatement.setInt(4, quantidade);
				prepareStatement.setDouble(5, carrinho.getSaldoEmEstoque());

				prepareStatement.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			


		

		

	}
}
