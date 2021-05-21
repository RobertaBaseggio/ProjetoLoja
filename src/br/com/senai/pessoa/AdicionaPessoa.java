package br.com.senai.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class AdicionaPessoa {
	
	private Connection connection;
	Scanner scanner = new Scanner(System.in);
	
	public AdicionaPessoa() {
		connection = DataBaseConnection.getInstance().getConnection();

	}

	public String definirCliente() {		
	
	
	
	PreparedStatement preparedStatement;
	
	System.out.println("Infome o nome do cliente: ");
		String nome = scanner.next();
		
		try {
			
			String sql ="insert into cliente values (null, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			preparedStatement.execute();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nome;
	}
}
