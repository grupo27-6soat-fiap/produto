package br.com.fiap.lanchonete.produtoservicefase4.app.controllers;

import br.com.fiap.lanchonete.produtoservicefase4.app.dto.ProdutoDto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.enums.CategoriaEnum;
import br.com.fiap.lanchonete.produtoservicefase4.infra.db.entities.ProdutoEntity;
import br.com.fiap.lanchonete.produtoservicefase4.infra.db.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired

    private ProdutoRepository produtoRepository;


    @BeforeEach
    public void setup() throws Exception {
        // Assuming ProdutoDto has a default constructor and setters
        ProdutoEntity produtoEntity = new ProdutoEntity();
        // Fill the fields of produtoDto here
        produtoEntity.setCategoria(CategoriaEnum.ACOMPANHAMENTO);
        produtoEntity.setId(3L);
        produtoEntity.setNome("Produto 1");
        produtoEntity.setPreco(BigDecimal.valueOf(10.0));
        produtoEntity.setDescricao("Descricao do produto 1");
        produtoEntity.setImagens(null);

        produtoRepository.save(produtoEntity);



    }





    @Test
    public void getProdutoById_EntityNotFoundException() throws Exception {
      //  when(getProdutoByIdUsecase.get(1L)).thenThrow(new EntityNotFoundException("Produto nao encontrado para o id :: " + 1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/{id}", 19L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void search_EnumValidationException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos")
                        .param("categoria", "INVALID_CATEGORY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(1)
    public void updateProduto_Success() throws Exception {
        // Assuming ProdutoDto has a default constructor and setters

        ProdutoDto produtoDto = new ProdutoDto();
        // Fill the fields of produtoDto here

        produtoDto.setCategoria(CategoriaEnum.ACOMPANHAMENTO);
        produtoDto.setId(1L);
        produtoDto.setNome("Produto 1");
        produtoDto.setPreco(BigDecimal.valueOf(10.0));
        produtoDto.setDescricao("Descricao do produto 2");
        produtoDto.setImagens(null);



        mockMvc.perform(MockMvcRequestBuilders.put("/produtos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(produtoDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void createProduto_Success() throws Exception {
        // Assuming ProdutoDto has a default constructor and setters
        ProdutoDto produtoDto = new ProdutoDto();
        // Fill the fields of produtoDto here
        produtoDto.setCategoria(CategoriaEnum.ACOMPANHAMENTO);
        produtoDto.setId(2L);
        produtoDto.setNome("Produto 1");
        produtoDto.setPreco(BigDecimal.valueOf(10.0));
        produtoDto.setDescricao("Descricao do produto 1");
        produtoDto.setImagens(null);


        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(produtoDto)))
                .andExpect(status().isCreated());
    }

   @Test
   @DisplayName("Teste de busca de produtos por categoria")
    public void search_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos")
                        .param("categoria", "ACOMPANHAMENTO")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de busca todos os produtos")
    public void search_All() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/produtos")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    public void deleteProduto_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/{id}", 1L))
                .andExpect(status().isNoContent());
    }




}