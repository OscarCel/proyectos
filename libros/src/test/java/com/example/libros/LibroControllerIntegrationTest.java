package com.example.libros;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.example.libros.model.Libro;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LibroControllerIntegrationTest {

     @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    private Libro libro;


    @BeforeEach
    public void setUp(){
        libro = new Libro(null, "libro", true, "Hola");
        entityManager.persist(libro);
        entityManager.flush();
    }

    @AfterEach
    public void tearDown(){
        entityManager.createQuery("DELETE FROM Libro").executeUpdate();
    }

    @Test
    void testCrear() throws Exception {
        Libro lib = new Libro (null, "libro2", true, "Adios");
        String mensaje = objectMapper.writeValueAsString(lib);

        mvc.perform(post("/api/libro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").isNumber())
        .andReturn();
    }

    @Test
    void testListar() throws Exception {
        mvc.perform(get("/api/libro")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testLeer() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/libro/{id}", libro.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(libro.getId()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        Libro libroResultado = objectMapper.readValue(body, Libro.class);
        
        assertTrue(libroResultado.isDisponible());
    }

    @Test
    void testActualizar() throws Exception {
        Libro lib = new Libro(1L, "libro", false, "Hola mundo");
        String mensaje = objectMapper.writeValueAsString(lib);

        mvc.perform(put("/api/libro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    }

    @Test
    void testBorrar() throws Exception {
        mvc.perform(delete("/api/libro/{id}", libro.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
