package com.example.demo;


import com.example.demo.models.Emprestimo;
import com.example.demo.resources.EmprestimoResource;
import com.example.demo.resources.MeioPagamentoResource;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.example.demo.models.MeioPagamentos;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
class PayrollApplicationTests {
	private MockMvc mockMvc;

	@Autowired
	private EmprestimoResource emprestimoResource;

	@Before("")
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(emprestimoResource).build();
	}

	@Test
	public void PostTestEmprestimo() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8090/Emprestimo")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"cnpj_cliente\": \"12332112332112\", \"dt_emprestimo\": \"2022-10-17\"}")
				).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(jsonPath("$.cnpj_cliente").value("12332112332112"));
	}

//	@Test
//	void contextLoads() {
//	}
	

}
