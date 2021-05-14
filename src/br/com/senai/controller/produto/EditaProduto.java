package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditaProduto {

	private Scanner scanner = new Scanner(System.in);
	private ListaProduto listaProduto;
	private ProdutoModel produto;
	private Connection connection;
	
	public EditaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
		
	}
	
	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;
		
		listaProduto  = new ListaProduto();
		
			produto = new ProdutoModel();
			int idDoProduto, indexDoCampo;
			
			if (listaProduto.listarProdutos() == null) {
				return null;
			} 
			
			System.out.println("---EDITAR---");
			System.out.print("Insira o ID do produto: ");
			idDoProduto = scanner.nextInt();
			
			try {
				String sql = "Select * from produto where codigo = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if(!resultSet.next()) {
					System.out.println("Este produto não existe");
					 return null;
				} else {
					produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
					produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
					produto.setQuantidadeDoProduto(resultSet.getInt("quantidadeEmEstoque"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}


				System.out.println("---CAMPOS---");
				System.out.println("1) Nome do produto;");
				System.out.println("2) Preço unitário;");
				System.out.println("3) Quantidade.");
				System.out.print("Informe o campo que deseja editar: ");
				indexDoCampo = scanner.nextInt();

				switch (indexDoCampo) {
				case 1:
	
					editarNomeDoProduto(idDoProduto);
					break;
				case 2:
					editarPrecoDoProduto( idDoProduto);
					break;

				case 3:
					editarQuantidadeDoProduto(idDoProduto);

				default:
					System.out.println("Opção invalida!!!");
					break;

				}
	
		
		return null;

	}

	public ProdutoModel editarQuantidadeDoProduto( int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.println("Informe a nova quantia do produto: ");
		produto.setQuantidadeDoProduto(scanner.nextInt());
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDoProduto());
		
		try {
			String sql = "update produto set quantidadeEmEstoque = ?, saldoEmEstoque = ? where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, produto.getQuantidadeDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produto;
	}
	

	public ProdutoModel editarPrecoDoProduto( int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.println("Informe o novo preço do produto: ");
		produto.setPrecoDoProduto(scanner.nextDouble());
		
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDoProduto());
		
		try {
			
			String sql = "update produto set precoDoProduto = ?, saldoEmEstoque = ? where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		return produto;
	}

	public ProdutoModel editarNomeDoProduto(  int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.println("Informe o novo nome do produto: ");
		produto.setNomeDoProduto(scanner.next());
		
		try {
			String sql = "update produto set nomeDoProduto = ? where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

		return produto;
	}
}
