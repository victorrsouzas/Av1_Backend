package com.example.demo.resources;

import java.util.*;

import com.example.demo.dto.EmprestimoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Emprestimo;
import com.example.demo.repositories.EmprestimoRepository;

import javax.validation.Valid;


@RestController
@RequestMapping("/Emprestimo")
public class EmprestimoResource{
	
	private final EmprestimoRepository repositorio_emprestimo;

	public EmprestimoResource(EmprestimoRepository repositorio_emprestimo) {
		this.repositorio_emprestimo = repositorio_emprestimo;
	}

	@PostMapping
	public ResponseEntity<Object> saveEmprestimo(@RequestBody @Valid EmprestimoDto emprestimoDto){
		var EmprestimoModel = new Emprestimo();
		BeanUtils.copyProperties(emprestimoDto, EmprestimoModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio_emprestimo.save(EmprestimoModel));
	}
	
	@GetMapping
	public List<Emprestimo>listar(){
		return repositorio_emprestimo.findAll();
	}

	// procurar empréstimo por número da ref. bancaria
	@GetMapping("/{referencia_bancaria}")
	public ResponseEntity<Object> getOneEmprestimo(@PathVariable(value = "referencia_bancaria") long referencia_bancaria){
		Optional<Emprestimo> emprestimoModelOptional = repositorio_emprestimo.findById(referencia_bancaria);
		if(!emprestimoModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(emprestimoModelOptional.get());
	}
	
	@PutMapping("/{referencia_bancaria}")
	public ResponseEntity<Object> updateEmprestimo(@PathVariable(value = "referencia_bancaria") long referencia_bancaria, @RequestBody @Valid EmprestimoDto emprestimoDto){
		Optional<Emprestimo> emprestimoModelOptional = repositorio_emprestimo.findById(referencia_bancaria);
		if(!emprestimoModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
		}
		var emprestimoModel = new Emprestimo();
		BeanUtils.copyProperties(emprestimoDto, emprestimoModel);
		emprestimoModel.setreferencia_bancaria(emprestimoModelOptional.get().getreferencia_bancaria());
		return ResponseEntity.status(HttpStatus.OK).body(repositorio_emprestimo.save(emprestimoModel));
	}

	
	/*@DeleteMapping
	public void excluir(Emprestimo emprestimo) {
		repositorio_emprestimo.delete(emprestimo);
	} */

	@DeleteMapping("/{referencia_bancaria}")
	public ResponseEntity<Object> deleteEmprestimo(@PathVariable(value = "referencia_bancaria") long referência_bancaria){
		Optional<Emprestimo> emprestimoModelOptional = repositorio_emprestimo.findById(referência_bancaria);
		if(!emprestimoModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado");
		}
		repositorio_emprestimo.delete(emprestimoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Emprestimo deletado");
	}


}