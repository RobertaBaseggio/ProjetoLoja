package br.com.senai.controller.produto;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.dao.DataBaseConnection;

public class ListaProduto {
	
	private Connection connection;
	

	public ListaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
		
	}
	
	public ResultSet listarProdutos() {
	
	PreparedStatement preparedStatement;
		
		try{
			
			String sql ="Select * from produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return null;
			}
			
			System.out.println("--- PRODUTOS CADASTRADOS ---");
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
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
