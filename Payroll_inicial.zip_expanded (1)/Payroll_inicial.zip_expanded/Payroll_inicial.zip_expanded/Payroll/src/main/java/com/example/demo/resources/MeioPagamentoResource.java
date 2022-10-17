package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.MeioPagamentosDto;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.MeioPagamentos;
import com.example.demo.repositories.MeioPagamentoRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/MeioPagamento")
public class MeioPagamentoResource{

	private final MeioPagamentoRepository repositorio;

	public MeioPagamentoResource(MeioPagamentoRepository repositorio) {
		this.repositorio = repositorio;
	}

	//	private MeioPagamentoRepository meiopagamentoRepository;
//
//	public MeioPagamentoResource(MeioPagamentoRepository meiopagamentoRepository) {
//		super();
//		this.meiopagamentoRepository = meiopagamentoRepository ;
//	}
//
	@PostMapping
	//public void save(@RequestBody MeioPagamentos meiopagamento){repositorio.save(meiopagamento);
	//}
	public ResponseEntity<Object> saveMeioPagamentos(@RequestBody @Valid MeioPagamentosDto meioPagamentosDto){
		var meioPagamentoModel = new MeioPagamentos();
		BeanUtils.copyProperties(meioPagamentosDto, meioPagamentoModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(meioPagamentoModel));
	}

	@GetMapping
	public List<MeioPagamentos>listar(){
		return repositorio.findAll();
	}

	@GetMapping("/{descricao}")
	public ResponseEntity<Optional<MeioPagamentos>> getPaymentMethod(@PathVariable("descricao") String descricao) {
		return new ResponseEntity<>(repositorio.findPaymentMethodByDescricao(descricao), HttpStatus.OK);
	}

	@DeleteMapping
	public void excluir(MeioPagamentos meiopagamento) {
		repositorio.delete(meiopagamento);
	}

//	@GetMapping
//	public ResponseEntity<List<MeioPagamentos>> getById(@PathVariable Integer Id){
//		List<MeioPagamentos> meiopagamento = new ArrayList<>();
//		meiopagamento = meiopagamentoRepository.findAll();
//		return new ResponseEntity<>(meiopagamento, HttpStatus.OK);
//	}
}