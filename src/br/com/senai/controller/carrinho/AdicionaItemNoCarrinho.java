package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
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
		ResultSet resultSet;

		System.out.println("--- ADICIONAR ---");
		System.out.println("Qual o id do item?");
		int idDoProduto = scanner.nextInt();
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
				produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * quantidade);
			}

		} catch (Exception e) {
			System.out.println("Erro ao adicionar ao carrinho.");
		}

		try {

			String sql = "INSERT INTO itensnocarrinho(nomeDoProduto, precoDoProduto, quantidadeEmEstoque, saldoEmEstoque)  VALUES (?, ?, ?, ?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			prepareStatement.setString(1, produto.getNomeDoProduto());
			prepareStatement.setDouble(2, produto.getPrecoDoProduto());
			prepareStatement.setInt(3, quantidade);
			prepareStatement.setDouble(4, produto.getSaldoEmEstoque());

			prepareStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			String sql = "update produto set quantidadeEmEstoque = ?, saldoEmEstoque = ? where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, produto.getQuantidadeDoProduto() - quantidade);
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
