package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class CadastraProduto {

	Scanner scanner = new Scanner(System.in);
	
	private Connection connection;
	
	public CadastraProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
		
	}
	
	public ProdutoModel cadastrarProduto() {

		ProdutoModel produtoModel = new ProdutoModel();
		
		System.out.println("--- CADASTRAR ITENS ---");
		System.out.println("Produto: ");
		produtoModel.setNomeDoProduto(scanner.nextLine());
		System.out.println("Preço: ");
		produtoModel.setPrecoDoProduto(scanner.nextDouble());
		System.out.println("Quantidade: ");
		produtoModel.setQuantidadeDoProduto(scanner.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDoProduto() * produtoModel.getPrecoDoProduto());

		
		try {
			
			String sql = "INSERT INTO produto(nomeDoProduto, precoDoProduto, quantidadeEmEstoque, saldoEmEstoque)  VALUES (?, ?, ?, ?)";
			PreparedStatement prepareStantement = connection.prepareStatement(sql);
			
			prepareStantement.setString(1, produtoModel.getNomeDoProduto());
			prepareStantement.setDouble(2, produtoModel.getPrecoDoProduto());
			prepareStantement.setInt(3, produtoModel.getQuantidadeDoProduto());
			prepareStantement.setDouble(4, produtoModel.getSaldoEmEstoque());
			
			prepareStantement.execute();
			
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}
		return produtoModel;

	}
	
}
