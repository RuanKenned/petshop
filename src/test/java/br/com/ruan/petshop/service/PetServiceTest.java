package br.com.ruan.petshop.service;

import br.com.ruan.petshop.controller.api.v1.PetController;
import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.ruan.petshop.model.Pet;
import br.com.ruan.petshop.repository.PetRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PetServiceTest {

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Mock
    private PetRepository petRepository;

    private PetService petService;

    @Mock
    private PetController petController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.petService = new PetService(petRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
	void getAll_Sucess() throws Exception{
        List<Pet> listaPets = pets();
        Mockito.when(petService.findAll()).thenReturn(listaPets);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/pet/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("meu header", "valor do meu header"))
                .andExpect(status().isOk());
	}

    @Test
    void getById_Sucess() throws Exception{
        Pet pet = pets().get(0);
        Mockito.when(petService.findById(pet.getId())).thenReturn(Optional.of(pet));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/pet/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("meu header", "valor do meu header"))
                .andExpect(status().isOk());
    }

    @Test
    void save_Sucess() throws Exception{
        Pet pet = new Pet();
        pet.setNome("Gato");
        pet.setDono("Ruan");
        Mockito.when(petService.save(pet)).thenReturn(pet);

        String content = objectMapper.writer().writeValueAsString(pet);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/api/v1/pet/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value(notNullValue()))
                .andExpect(jsonPath("$.nome").value("Ruan"));
    }

    private List<Pet> pets() {
        List<Pet> lista = new ArrayList<>();

        Pet pet1 = new Pet(1L, "Ruan", "Cachorro");
        Pet pet2 = new Pet(2L, "Rillary", "Passarinho");

        lista.add(pet1);
        lista.add(pet2);

        return lista;
    }
}
