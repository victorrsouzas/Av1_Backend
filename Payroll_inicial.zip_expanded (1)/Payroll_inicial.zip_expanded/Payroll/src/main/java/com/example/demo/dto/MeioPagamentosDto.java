package com.example.demo.dto;

import javax.validation.constraints.*;

public class MeioPagamentosDto {

    @NotBlank
    @Size(min= 6, max = 6, message = "O código deve ter 6 caracteres")
    private String codigo; // Alfanúmerico

    //NotBlank ele verifica se o campo não é nulo
    @NotBlank
    //Size é o tamanho da string
    @Size(min= 1, max = 50, message = "A descrição tem o limite de 50 caracteres")
    private String descricao; // Alfa

    @NotNull
    //O elemento anotado deve ser um número cujo valor deve ser maior ou igual ao mínimo especificado.
    @DecimalMin(value = "0.00000000001",message = "A Taxa de de juros tem que ser maior que zero") 
    //@Min(value = 1, message = "A Taxa de de juros tem que ser maior que zero")
    private Float tx_juros; // Número Decimal

    @NotNull
    @Min(value = 0, message = "A Taxa de de juros tem que ser maior ou igual a zero")
    @Max(value = 1, message = "A Taxa de de juros tem que ser menor ou igual a 1")
    private Float imposto; // Número Decimal

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getTx_juros() {
        return tx_juros;
    }

    public void setTx_juros(Float tx_juros) {
        this.tx_juros = tx_juros;
    }

    public Float getImposto() {
        return imposto;
    }

    public void setImposto(Float imposto) {
        this.imposto = imposto;
    }
}
