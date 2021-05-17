package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
public class DeletaProduto {

	private Connection connection;
	private Scanner scanner = new Scanner(System.in);
	private ListaProduto listaProduto;

	public DeletaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public boolean verifivaSeExisteProduto(int idDoProduto) {
		PreparedStatement preparedStatement;

		try {
			String sql = "Select * from produto where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe");
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro aqui");
			return false;
		}
	}

	public void removerProduto() {
		PreparedStatement preparedStatement;

		listaProduto = new ListaProduto();

		System.out.println("--- REMOVER ---");

		if (listaProduto.listarProdutos() == null) {
			System.out.println("Não existem produtos cadastrados");
			return;
		}

		System.out.print("Insira o Id do Produto: ");
		int idDoProduto = scanner.nextInt();

		try {

			if (!verifivaSeExisteProduto(idDoProduto)) {
				return;
			}

			String sql = "delete from produto where codigo = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível excluir essa informação!");
			return;
		}

	}

}
