package br.com.senai.controller.carrinho;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.Carrinho;
import br.com.senai.pessoa.AdicionaPessoa;
public class FinalizaCompra {

	Scanner scanner = new Scanner(System.in);

	VisualizaCarrinho visualizaCarrinho = new VisualizaCarrinho();
	AdicionaPessoa adicionaPessoa = new AdicionaPessoa();

	private Connection connection;

	public FinalizaCompra() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void finalizarCompra(String cliente) {

		ListaCarrinho listaCarrinho = new ListaCarrinho();
		Carrinho carrinho = new Carrinho();
		ResultSet resultSet;

		listaCarrinho.listarCarrinho();

		System.out.println("--- FINALIZAR ---");
		System.out.println("Tem certeza que deseja finalizar a compra?");
		System.out.println("1) Sim");
		System.out.println("2) Não");
		int confirmacao = scanner.nextInt();

		try {

			String sql = "Select * from itensNoCarrinho ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return;

			} else {
				carrinho.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				carrinho.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				carrinho.setQuantidadeDoProduto(resultSet.getInt("quantidadeEmEstoque"));
				carrinho.setSaldoEmEstoque(resultSet.getDouble("saldoEmEstoque"));

			}

		} catch (Exception e) {
			System.out.println("Erro .");
		}

		try {

			if (confirmacao == 1) {
		
				String sql = "INSERT INTO comprasEfetuadas (nomeDoProduto, precoDoProduto, quantidadeEmEstoque, nomeCliente) "
						+ "VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, carrinho.getNomeDoProduto());
				preparedStatement.setDouble(2, carrinho.getPrecoDoProduto());
				preparedStatement.setInt(3, carrinho.getQuantidadeDoProduto());
				preparedStatement.setString(4, cliente);
				preparedStatement.execute();

				listaCarrinho.gerarCupom(cliente);

				String sql2 = "truncate table itensNoCarrinho";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.execute();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}
