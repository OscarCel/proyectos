package com.example.tablas;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.tablas.models.PrimeraDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PrimeraControllerIntregrationTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired()
	private MockMvc mvc;

	@Test
	void testLeer() throws Exception {
		long id = 3;

		MvcResult mvcResult = mvc.perform(get("/api/primera/{1}", id)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String body = mvcResult.getResponse().getContentAsString();
		PrimeraDTO primeraResultado = objectMapper.readValue(body, PrimeraDTO.class);

		assertNotNull(primeraResultado.getSegundaDTO());

	}
}