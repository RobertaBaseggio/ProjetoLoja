package br.com.senai.controller;

import java.util.Scanner;


public class ProdutoController {

	Scanner scanner = new Scanner(System.in);

	

	public int opcao() {
		System.out.print(">");
		return scanner.nextInt();
	}
	
	

	public void menu() {
		System.out.println("\n---MENU---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Ver carrinho");
		System.out.println("6) Sair do sistema");
		System.out.println("----------------------");
		

	}
	

	

	
}
