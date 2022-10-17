package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ItemPagamento;
import com.example.demo.repositories.ItemPagamentoRepository;




@RestController
@RequestMapping("/itempagamento")
public class ItemPagamentoResource{
	
	@Autowired
	private ItemPagamentoRepository repositorio_itempagamento;

//	
	@PostMapping
	public void save(@RequestBody ItemPagamento itempagamento){
		repositorio_itempagamento.save(itempagamento);
	}
	
	@GetMapping
	public List<ItemPagamento>listar(){
		return repositorio_itempagamento.findAll();
	}
	
	@DeleteMapping
	public void excluir(ItemPagamento itempagamento) {
		repositorio_itempagamento.delete(itempagamento);
	}
	
}