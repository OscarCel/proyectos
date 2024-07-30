package com.example.DTO;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.DTO.Controller.UserController;
import com.example.DTO.Service.UserService;
import com.example.DTO.model.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDTO userDTO;
    private UserDTO savedUserDTO;

    @BeforeEach
    public void setUp() {
        // Configurar los datos simulados
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");

        savedUserDTO = new UserDTO();
        savedUserDTO.setId(2L);
        savedUserDTO.setName("Jane Doe");
        savedUserDTO.setEmail("jane.doe@example.com");

        // Configurar las respuestas simuladas para UserService
        when(userService.getUserById(anyLong())).thenReturn(userDTO);
        when(userService.saveUser(any(UserDTO.class))).thenReturn(savedUserDTO);
    }

    @AfterEach
    public void tearDown() {
        // En este caso, no es necesario limpiar nada específico, ya que no estamos usando la base de datos.
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDTO.getId()))
                .andExpect(jsonPath("$.name").value(userDTO.getName()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTOToCreate = new UserDTO();
        userDTOToCreate.setName(savedUserDTO.getName());
        userDTOToCreate.setEmail(savedUserDTO.getEmail());

        String userJson = objectMapper.writeValueAsString(userDTOToCreate);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }
}
