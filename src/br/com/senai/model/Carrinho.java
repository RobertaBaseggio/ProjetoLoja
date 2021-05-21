package br.com.senai.model;

public class Carrinho {


	private String nomeDoProduto;
	private double precoDoProduto;
	private int quantidadeDoProduto;
	private double saldoEmEstoque;
	private static int idDoProduto;
	private ProdutoModel lojaModel;
	private double valorTotalPorItem;
	
	
	
	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public double getPrecoDoProduto() {
		return precoDoProduto;
	}

	public void setPrecoDoProduto(double precoDoProduto) {
		this.precoDoProduto = precoDoProduto;
	}

	public int getQuantidadeDoProduto() {
		return quantidadeDoProduto;
	}

	public void setQuantidadeDoProduto(int quantidadeDoProduto) {
		this.quantidadeDoProduto = quantidadeDoProduto;
	}

	public double getSaldoEmEstoque() {
		return saldoEmEstoque;
	}

	public void setSaldoEmEstoque(double saldoEmEstoque) {
		this.saldoEmEstoque = saldoEmEstoque;
	}

	public static int getIdDoProduto() {
		return idDoProduto;
	}

	public void setIdDoProduto(int idDoProduto) {
		Carrinho.idDoProduto = idDoProduto;
	}

	public ProdutoModel getLojaModel() {
		return lojaModel;
	}

	public void setLojaModel(ProdutoModel lojaModel) {
		this.lojaModel = lojaModel;
	}

	public double getValorTotalPorItem() {
		return valorTotalPorItem;
	}

	public void setValorTotalPorItem(double valorTotalPorItem) {
		this.valorTotalPorItem = valorTotalPorItem;
	}


	public Carrinho(int idDoProduto, ProdutoModel produtoModel, double valorTotalPorItem) {
		super();
		Carrinho.idDoProduto = idDoProduto;
		this.lojaModel = produtoModel;
		this.valorTotalPorItem = valorTotalPorItem;
	}

	
	

	public Carrinho(String nomeDoProduto, double precoDoProduto, int quantidadeDoProduto, double saldoEmEstoque) {
		super();
		this.nomeDoProduto = nomeDoProduto;
		this.precoDoProduto = precoDoProduto;
		this.quantidadeDoProduto = quantidadeDoProduto;
		this.saldoEmEstoque = saldoEmEstoque;
	}
	public Carrinho() {
		
	}
	
	
	
}
