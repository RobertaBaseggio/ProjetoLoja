package br.com.senai.pessoa;

import java.util.Scanner;

public class AdicionaPessoa {

public String definirCliente() {
		
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
	
		System.out.println("Infome o nome do cliente: ");
		String nome = scanner.next();
		
		return nome;
	}
}
