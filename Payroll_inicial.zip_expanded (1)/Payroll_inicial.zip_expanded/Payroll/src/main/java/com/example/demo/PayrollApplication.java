package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.jar.Attributes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;



@SpringBootApplication
public class PayrollApplication {
	private static final String filename = "C:/Users/User/Desktop/class.xml";
	public static void main(String[] argv) throws IOException {
		if (argv.length > 0) {
			System.out.print(argv[0]);
			//check if argv[0].equals("-1") or something like that
		}

		FileWriter arq = new FileWriter("C:/Users/User/Desktop/exportacao.txt");
		SpringApplication.run(PayrollApplication.class, argv);

		Scanner escolha = new Scanner(System.in);
		System.out.printf("Digite 1 caso queria importar:\n");
		System.out.printf("Digite 2 caso queria exportar:\n");
		System.out.printf("Digite 3 caso queria importar e exportar:\n");
		int na = escolha.nextInt();
		if (na!=2) {
			System.out.printf("Importa\n");
			SAXParserFactory factory = SAXParserFactory.newInstance();

			try {

				SAXParser saxParser = factory.newSAXParser();
				PrintAllHandlerSax handler = new PrintAllHandlerSax();
				saxParser.parse(filename, handler);

			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		if (na!=1) {
			System.out.printf("Exportar\n");
			String url = "jdbc:mysql://localhost:3306/AV1_Backend?" + "user=root&password=root" ;

			try (Connection conn = DriverManager.getConnection(url);
				 Statement stmt = conn.createStatement()) {

				try {
					ResultSet rs = stmt.executeQuery("select * from meio_pagamentos;");
					while (rs.next()) {
						String id = rs.getString("id");
						//System.out.println(id);
						String codigo = rs.getString("codigo");
						//System.out.println(codigo);
						String descricao = rs.getString("descricao");
						//System.out.println(descricao);
						String imposto = rs.getString("imposto");
						//System.out.println(imposto);
						String tx_juros = rs.getString("tx_juros");
						//System.out.println(tx_juros);
						Scanner ler = new Scanner(System.in);
						int i, n;
						PrintWriter gravarArq = new PrintWriter(arq);
						//String tudo = "" +  " " + id + "," + codigo + "," + descricao + "," + imposto + "," + tx_juros + "\n";
						gravarArq.printf("\"%s\", \"%s\",\"%s\",\"%s\",\"%s\" \n", id, codigo, descricao,imposto, tx_juros);
						//gravarArq.printf(tudo);

					}
				} catch (SQLException e ) {
					throw new Error("Problem", e);
				}

			} catch (SQLException e) {
				throw new Error("Problem", e);
			}
			arq.close();
		}






	}

}