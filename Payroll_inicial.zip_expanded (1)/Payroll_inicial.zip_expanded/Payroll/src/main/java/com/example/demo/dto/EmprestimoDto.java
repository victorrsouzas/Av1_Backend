package com.example.demo.dto;




import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

public class EmprestimoDto {

    @NotBlank
    @CNPJ
    private String cnpj_cliente;

    @NotNull
    @Future
    private Date dt_emprestimo;

    public String getCnpj_cliente() {
        return cnpj_cliente;
    }

    public void setCnpj_cliente(String cnpj_cliente) {
        this.cnpj_cliente = cnpj_cliente;
    }

    public Date getDt_emprestimo() {
        return dt_emprestimo;
    }

    public void setDt_emprestimo(Date dt_emprestimo) {
        this.dt_emprestimo = dt_emprestimo;
    }

}