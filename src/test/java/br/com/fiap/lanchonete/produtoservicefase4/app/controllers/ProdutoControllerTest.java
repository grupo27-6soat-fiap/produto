package br.com.fiap.lanchonete.produtoservicefase4.app.controllers;

import br.com.fiap.lanchonete.produtoservicefase4.app.dto.ProdutoDto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.enums.CategoriaEnum;
import br.com.fiap.lanchonete.produtoservicefase4.domain.usecase.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProdutoControllerTest {

    private ProdutoController produtoController;

    @Mock
    private GetProdutoByIdUsecase getProdutoByIdUsecase;

    @Mock
    private FindProdutoByCategoriaUsecase findProdutoByCategoriaUsecase;

    @Mock
    private CreateProdutoUsecase createProdutoUsecase;

    @Mock
    private UpdateProdutoUsecase updateProdutoUsecase;

    @Mock
    private DeleteProdutoUsecase deleteProdutoUsecase;

    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        produtoController = new ProdutoController(getProdutoByIdUsecase, findProdutoByCategoriaUsecase, createProdutoUsecase, updateProdutoUsecase, deleteProdutoUsecase, modelMapper);
    }

    @Test
    public void getProdutoById_HappyPath() {
        Produto produto = new Produto();
        when(getProdutoByIdUsecase.get(1L)).thenReturn(produto);
       // when(modelMapper.map(produto, ProdutoDto.class)).thenReturn(new ProdutoDto());

        ResponseEntity<ProdutoDto> response = produtoController.get(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void searchProdutoByCategoria_HappyPath() {
        when(findProdutoByCategoriaUsecase.findByCategoria(any(CategoriaEnum.class))).thenReturn(new ArrayList<>());

        ResponseEntity<List<ProdutoDto>> response = produtoController.search(CategoriaEnum.BEBIDA.name());

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void createProduto_HappyPath() {
        Produto produto = new Produto();
        ProdutoDto produtoDto = new ProdutoDto();
        when(createProdutoUsecase.create(any(Produto.class))).thenReturn(produto);
       // when(modelMapper.map(produtoDto, Produto.class)).thenReturn(produto);
       // when(modelMapper.map(produto, ProdutoDto.class)).thenReturn(produtoDto);

        ResponseEntity<ProdutoDto> response = produtoController.post(produtoDto);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void updateProduto_HappyPath() {
        Produto produto = new Produto();
        ProdutoDto produtoDto = new ProdutoDto();
        when(updateProdutoUsecase.update(any(Long.class), any(Produto.class))).thenReturn(produto);
       // when(modelMapper.map(produtoDto, Produto.class)).thenReturn(produto);
       // when(modelMapper.map(produto, ProdutoDto.class)).thenReturn(produtoDto);

        ResponseEntity<ProdutoDto> response = produtoController.put(1L, produtoDto);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void deleteProduto_HappyPath() {
        Produto produto = new Produto();
        when(getProdutoByIdUsecase.get(any(Long.class))).thenReturn(produto);

        ResponseEntity<Void> response = produtoController.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void getProdutoById_EntityNotFoundException() {

        when(getProdutoByIdUsecase.get(1L)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> produtoController.get(1L));
    }


    @Test
    public void postProduto_Exception() {
       // when(produtoController.post(any(ProdutoDto.class))).thenThrow(new RuntimeException("Error creating product"));

        assertThrows(RuntimeException.class, () -> produtoController.post(new ProdutoDto()));
    }
}