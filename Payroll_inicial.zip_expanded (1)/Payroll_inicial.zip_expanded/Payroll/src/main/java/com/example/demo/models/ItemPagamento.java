package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity
public class ItemPagamento {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//@Column(name = "codigo_meiopagamento", nullable = false)
	@Column(name = "codigo_meiopagamento")
	private String codigo_meiopagamento;
	
	@Column(name = "valor", nullable = false)
	private float valor; 
	
	@Column(name = "prazo", nullable = false) 
	private int prazo; // 
	
	@Column(name = "imposto_pago") 
	private float imposto_pago; //
	
	@Column(name = "valor_final") 
	private double valor_final; //
	
	
	public ItemPagamento(){}

	
	
	public ItemPagamento(String codigo_meiopagamento, float valor, int prazo, float imposto_pago, double valor_final) {
		super();
		//MeioPagamentos meiopagamento = new MeioPagamentos();
		//float imposto = meiopagamento.getimposto();
		//float taxadejuros = meiopagamento.gettx_juros();
		this.codigo_meiopagamento = codigo_meiopagamento;
		this.valor = valor;
		this.prazo = prazo;
		this.imposto_pago = valor * prazo;
		this.valor_final = valor * prazo * valor;
		//this.imposto_pago = (valor * imposto) ;
		//this.valor_final = (valor * imposto) + Math.pow( (valor*(1 + taxadejuros)), prazo);

	}
	
	public long getreferencia_bancaria() {
		return id;
		
	}
	
	public String getMeiopagamento() {
		return codigo_meiopagamento;
	}


	public void setMeiopagamento(String codigo_meiopagamento) {
		this.codigo_meiopagamento = codigo_meiopagamento;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public int getPrazo() {
		return prazo;
	}


	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}


	public float getImposto_pago() {
		return imposto_pago;
	}


	public void setImposto_pago(float imposto_pago) {
		this.imposto_pago = imposto_pago;
	}


	public double getValor_final() {
		return valor_final;
	}


	public void setValor_final(double valor_final) {
		this.valor_final = valor_final;
	}
	

} 