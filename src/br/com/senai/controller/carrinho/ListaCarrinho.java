package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.Carrinho;
public class ListaCarrinho {

	private Connection connection;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();

	}

	public ResultSet listarCarrinho() {

		PreparedStatement preparedStatement;

		try {

			String sql = "Select * from itensNoCarrinho";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return null;
			}

			System.out.println("--- CARRINHO ---");
			System.out.printf("| %2s | %15s | %7s | %4s | %9s|\n", "ID", "Produto", " Preço ", " Qtd ", "R$ Total");
			
			resultSet.previous();

			while(resultSet.next()) {
				System.out.printf("| %2s | %15s | %7s | %4s | %9s |\n",
						resultSet.getInt("codigo"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeEmEstoque"),
						resultSet.getDouble("saldoEmEstoque"));
				
			}
			return resultSet;
			
			double valorTotal = quantidade * precoDoProduto; 

			sql = "INSERT INTO carrinho VALUES(null, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nomeDoProduto);
			preparedStatement.setInt(3, quantidadeDesejada);
			preparedStatement.setDouble(4, precoDoProduto);
			preparedStatement.setDouble(5, valorTotal);
			preparedStatement.execute();

			
			/*cod da dani, usar exemplo*/
			double valorTotalDocarrinho = 0;

			for (int i = 0; i < listaCarrinho.size(); i++) {
				valorTotalDocarrinho += listaCarrinho.get(i).getValorTotalPorItem();
			}

			System.out.println("Valor total: R$ " + valorTotalDocarrinho);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	public void gerarCupom(List<Carrinho> listaCarrinho, String cliente) {

		listarCarrinho();
		System.out.println("Cliente: " + cliente);

	}
}
