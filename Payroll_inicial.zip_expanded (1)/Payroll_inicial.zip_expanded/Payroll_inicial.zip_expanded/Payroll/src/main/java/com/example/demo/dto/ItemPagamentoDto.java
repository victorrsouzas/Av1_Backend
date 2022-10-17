package com.example.demo.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItemPagamentoDto{
	
	@NotNull
	private String codigo_meiopagamento;
	
	@NotNull
	@DecimalMin(value = "0.00000000001",message = "O valor tem que ser maior que zero") 
	private float valor; 
	
	@NotNull
    @Min(value = 0, message = "O prazo tem que ser maior ou igual a zero")
	private int prazo; // 
	
	
	private float imposto_pago; 
	
	private float valor_final; 

}