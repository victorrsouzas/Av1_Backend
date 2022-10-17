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

	@Column(name = "codigo_meiopagamento", nullable = false)
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
		this.codigo_meiopagamento = codigo_meiopagamento;
		this.valor = valor;
		this.prazo = prazo;
		this.imposto_pago = imposto_pago;
		this.valor_final = valor_final;

	}

	public String getCodigo_meiopagamento() {
		return codigo_meiopagamento;
	}

	public void setCodigo_meiopagamento(String codigo_meiopagamento) {
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