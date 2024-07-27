package es.cic.curso.ejerc005;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SaludosControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void testListar() throws Exception {
                mvc.perform(get("/api/saludo")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testLeer() throws Exception {
        long id = 1;
        MvcResult mvcResult = mvc.perform(get("/api/saludo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        Saludo saludoResultado = objectMapper.readValue(body, Saludo.class);
        
        assertFalse(saludoResultado.isEscuchado());
    }

    @Test
    void testBorrar() throws Exception {
        mvc.perform(delete("/api/saludo/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testCrear() throws Exception {
        //String mensaje = "{\"id\":1,\"dniCifNie\":\"777777X\",\"escuchado\":false,\"mensaje\":\"Hola\"}";
        Saludo saludo = new Saludo(null, "dni", false, "Adios");
        String mensaje = objectMapper.writeValueAsString(saludo);

        mvc.perform(post("/api/saludo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().string("1"))
        .andReturn();
    }

    /*@Test
    void testCrearDNIValidaton() throws Exception {
        Saludo saludo = new Saludo(null, "dnidnidnidnidnidnidnidni", false, "Adios");
        String mensaje = objectMapper.writeValueAsString(saludo);

        mvc.perform(post("/api/saludo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().string("1"))
        .andReturn();
    }*/

    @Test
    void testActualizar() throws Exception{
        Saludo saludo = new Saludo(3l, "dni", false, "Adios");
        String mensaje = objectMapper.writeValueAsString(saludo);

        mvc.perform(put("/api/saludo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mensaje))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    }

}
