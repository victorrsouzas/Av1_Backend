package com.example.demo.resources;

import java.util.*;

import com.example.demo.dto.MeioPagamentosDto;
import com.example.demo.models.Emprestimo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@Autowired
	MeioPagamentoRepository repositorio;

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
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

	@GetMapping("/consulta1")
	public ResponseEntity<Map<String, Object>> getmeioPagamentosByCodigoAndDescricao(@RequestParam String codigo,
																 @RequestParam String descricao, @RequestParam(defaultValue = "0") int page,
																					  @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id,asc") String[] sort) {
		try{
			List<Sort.Order> orders = new ArrayList<Sort.Order>();

			if (sort[0].contains(",")) {
				for (String sortOrder : sort) {
					String[] _sort = sortOrder.split(",");
					orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
			}

			List<MeioPagamentos> meioPagamentos = new ArrayList<MeioPagamentos>();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			Page<MeioPagamentos> pageTuts;
			if (codigo == null || descricao == null)
				pageTuts = repositorio.findAll(pagingSort);
			else
				pageTuts = repositorio.findByCodigoAndDescricao(codigo, descricao, pagingSort);

			meioPagamentos = pageTuts.getContent();

			if (meioPagamentos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response1 = new HashMap<>();
			response1.put("Meio Pagamentos", meioPagamentos);
			response1.put("currentPage", pageTuts.getNumber());
			response1.put("totalItems", pageTuts.getTotalElements());
			response1.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/consulta2")
	public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
			@RequestParam(required = false) String codigo,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "codigo,desc") String[] sort) {

		try {
			List<Sort.Order> orders = new ArrayList<Sort.Order>();

			if (sort[0].contains(",")) {
				for (String sortOrder : sort) {
					String[] _sort = sortOrder.split(",");
					orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
			}

			List<MeioPagamentos> meioPagamentos = new ArrayList<MeioPagamentos>();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			Page<MeioPagamentos> pageTuts;
			if (codigo == null)
				pageTuts = repositorio.findAll(pagingSort);
			else
				pageTuts = repositorio.findByCodigoContaining(codigo, pagingSort);

			meioPagamentos = pageTuts.getContent();

			if (meioPagamentos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("Meio Pagamentos", meioPagamentos);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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