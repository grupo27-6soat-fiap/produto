package br.com.fiap.lanchonete.produtoservicefase4;


import br.com.fiap.lanchonete.produtoservicefase4.app.dto.ProdutoDto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.enums.CategoriaEnum;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest
public class CreateProductStepDefs   {

@Autowired
    private MockMvc mockMvc;


@Autowired
    private ObjectMapper objectMapper;

    private ProdutoDto productPayload;
    private MvcResult mvcResult;

    @Before
    public void setUp() {
        productPayload = new ProdutoDto();
        productPayload.setId(1L);
        productPayload.setNome("Hamburguer");
        productPayload.setCategoria(CategoriaEnum.LANCHE);
        productPayload.setPreco(BigDecimal.valueOf(10.0));
        productPayload.setDescricao("Hamburguer de carne com queijo");


        ;
    }

    @Given("I have a product payload")
    public void i_have_a_product_payload() {
        // set the necessary fields in the productPayload object
    }

    @When("I send a POST request to {string} with the product payload")
    public void i_send_a_post_request_to_with_the_product_payload(String url) throws Exception {
        String productPayloadJson = objectMapper.writeValueAsString(productPayload);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productPayloadJson))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int statusCode) {
        assertEquals(statusCode, mvcResult.getResponse().getStatus());
    }

    @Then("the response body should match the product payload")
    public void the_response_body_should_match_the_product_payload() throws Exception {
        ProdutoDto returnedProduct = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProdutoDto.class);
        assertEquals(productPayload, returnedProduct);
    }

}