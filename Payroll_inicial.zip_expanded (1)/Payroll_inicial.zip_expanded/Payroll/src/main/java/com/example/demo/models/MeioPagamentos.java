package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MeioPagamentos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo", nullable = false)
	private String codigo; // Alfanúmerico
	
	@Column(name = "descricao", nullable = false) 
	private String descricao; // Alfa
	
	@Column(name = "tx_juros", nullable = false)
	private Float tx_juros; // Número Decimal
	
	@Column(name = "imposto", nullable = false)
	private Float imposto; // Número Decimal
	
	public MeioPagamentos(){}

	
	public MeioPagamentos(String codigo, String descricao, Float tx_juros, Float imposto) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.tx_juros = tx_juros;
		this.imposto = imposto;
	}
	
	public int getId() {
		return id;
		
	}
	
	public String getcodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getdescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Float gettx_juros() {
		return tx_juros;
	}
	
	public void setTx_juros(Float tx_juros) {
		this.tx_juros = tx_juros;

	}

	public Float getimposto() {
		return imposto;
	}
	
	public void setImposto(Float imposto) {
		this.imposto = imposto;
	}
	
	public String toString() {
		return codigo;
		
	}
	
public class teste{
	
}
}

