package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;
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
			System.out.printf("| %2s | %15s | %15s | %7s | %4s | %9s|\n", "ID", "Cod de produto", "Produto", " Preço ", " Qtd ", "R$ Total");
			
			resultSet.previous();

			while(resultSet.next()) {
				System.out.printf("| %2s | %15s | %15s | %7s | %4s | %9s |\n",
						resultSet.getInt("codigo"),
						resultSet.getInt("codigoProduto"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeEmEstoque"),
						resultSet.getDouble("saldoEmEstoque"));
				
			}
			
			String sql3 = "select sum(saldoEmEstoque) as 'TotalNoCarrinho' from itensnocarrinho";
			preparedStatement = connection.prepareStatement(sql3);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			System.out.println("Total No Carrinho: " + resultSet.getDouble("TotalNoCarrinho"));
			
			return resultSet;
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}
	
	
	public void gerarCupom( String cliente) {
		

		listarCarrinho();
		System.out.println("Cliente: " + cliente);
		
		
		

	}
}
