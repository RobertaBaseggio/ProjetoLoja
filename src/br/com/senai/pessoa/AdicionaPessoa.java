package br.com.senai.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class AdicionaPessoa {
	
	private Connection connection;
	Scanner scanner = new Scanner(System.in);
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public AdicionaPessoa() {
		connection = DataBaseConnection.getInstance().getConnection();

	}

	
	public String definirCliente() {		
		System.out.println("Infome o nome do cliente: ");
		String nome = scanner.next();
		
		try {
			
			 String sql = "Select nome from cliente where nomeCliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, nome);
				resultSet = preparedStatement.executeQuery();

				String confirmaNome = resultSet.getString("nomeCliente");		
				
				if(nome != confirmaNome) {
					 sql ="insert into cliente values (null, ?)";
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, nome);
						preparedStatement.execute();
				}
			
			

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nome;
	}
	

}
