package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long referencia_bancaria;
	
	@Column(name = "cnpj_cliente", nullable = false)
	private String cnpj_cliente; // Alfanúmerico
	
	@Column(name = "dt_emprestimo", nullable = false) 
	private Date dt_emprestimo; // 
	
	
	public Emprestimo(){}

	
	public Emprestimo(String cnpj_cliente, Date dt_emprestimo) {
		super();
		this.cnpj_cliente = cnpj_cliente;
		this.dt_emprestimo = dt_emprestimo;

	}
	
	public long getreferencia_bancaria() {
		return referencia_bancaria;
		
	}
	
	public void setreferencia_bancaria(long referencia_bancaria) {
		this.referencia_bancaria = referencia_bancaria;
	}
	
	public String getcnpj_cliente() {
		return cnpj_cliente;
	}
	
	public void setCnpj_cliente(String cnpj_cliente) {
		this.cnpj_cliente = cnpj_cliente;
	}
	
	public Date getdt_emprestimo() {
		return dt_emprestimo;
	}
	
	public void setDt_emprestimo(Date dt_emprestimo) {
		this.dt_emprestimo = dt_emprestimo;
	}
}