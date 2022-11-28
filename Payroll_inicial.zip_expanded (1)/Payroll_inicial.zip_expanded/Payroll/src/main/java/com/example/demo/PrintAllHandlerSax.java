package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrintAllHandlerSax extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();

    @Override
    public void startDocument() {
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        //System.out.printf("Start Element : %s%n", qName);

        if (qName.equalsIgnoreCase("importar")) {
            // get tag's attribute by name
            //String id = attributes.getValue("id");
            //System.out.printf("Staff id : %s%n", id);
        }


    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        String url = "jdbc:mysql://localhost:3306/AV1_Backend?" + "user=root&password=root" ;
        //System.out.printf("End Element : %s%n", qName);
        String codigo = null;
        String descricao = null;
        Float imposto = null;
        Float tx_juros = null;
        int contador = 0;
        int tamanho_tabela = 0;
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            try {

                stmt.executeQuery("SELECT COUNT(Id) AS EsseEhMeuResultado FROM meio_pagamentos;");
                ResultSet rs  = stmt.getResultSet();
                while(rs.next()) {
                    tamanho_tabela = rs.getInt("EsseEhMeuResultado");
                }
                //System.out.println(tamanho_tabela);
                String update_tabela = null;

                if (qName.equalsIgnoreCase("codigo")) {
                    codigo = currentValue.toString();
                    //System.out.printf("Name : %s%n", currentValue.toString());
                    contador = 1;
                    String comando_inserir = "insert into meio_pagamentos(codigo, descricao, imposto,tx_juros) "
                            + "values(\"" + codigo+ "\"," + "\"descricao\"," + "0.0" + "," + "0.0" + ")";

                    //System.out.println(comando_inserir);
                    stmt.executeUpdate(comando_inserir);
                    stmt.close();
                }

                if (qName.equalsIgnoreCase("descricao")) {
                    descricao = currentValue.toString();
                    //System.out.printf("Role : %s%n", currentValue.toString());
                    update_tabela = " UPDATE meio_pagamentos SET descricao =\"" + descricao + "\" where id =" +tamanho_tabela;
                    //System.out.println(update_tabela);
                    stmt.executeUpdate(update_tabela);
                    stmt.close();
                }

                if (qName.equalsIgnoreCase("imposto")) {
                    imposto = Float.parseFloat(currentValue.toString());
                    //System.out.printf("Salary : %s%n", currentValue.toString());
                    update_tabela = " UPDATE meio_pagamentos SET imposto =" + imposto + "where id =" +tamanho_tabela;
                    //System.out.println(update_tabela);
                    stmt.executeUpdate(update_tabela);
                    stmt.close();

                }

                if (qName.equalsIgnoreCase("tx_juros")) {
                    tx_juros = Float.parseFloat(currentValue.toString());
                    //System.out.printf("Bio : %s%n", currentValue.toString());
                    update_tabela = " UPDATE meio_pagamentos SET tx_juros =" + tx_juros + "where id =" +tamanho_tabela;
                    //System.out.println(update_tabela);
                    stmt.executeUpdate(update_tabela);
                    stmt.close();
                }




            } catch (SQLException e ) {
                throw new Error("Problem", e);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }





    }


    @Override
    public void characters(char ch[], int start, int length) {


        currentValue.append(ch, start, length);

    }

}