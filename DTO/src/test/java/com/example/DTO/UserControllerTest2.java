package com.example.DTO;

import com.example.DTO.Service.UserService;
import com.example.DTO.model.UserDTO;
import com.example.DTO.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;
    private UserDTO savedUserDTO;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        userRepository.deleteAll();
        
        // Configurar los datos de prueba
        userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");

        savedUserDTO = new UserDTO();
        savedUserDTO.setName("Jane Doe");
        savedUserDTO.setEmail("jane.doe@example.com");

        // Guardar un usuario en la base de datos para la prueba
        savedUserDTO = userService.saveUser(savedUserDTO);
    }

    @AfterEach
    public void tearDown() {
        // Limpiar la base de datos después de cada prueba
        userRepository.deleteAll();
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/" + savedUserDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedUserDTO.getId()))
                .andExpect(jsonPath("$.name").value(savedUserDTO.getName()))
                .andExpect(jsonPath("$.email").value(savedUserDTO.getEmail()));
    }

    @Test
    public void testCreateUser() throws Exception {
        String userJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/users")
                    .contentType("application/json")
                    .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userDTO.getName()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }
}
