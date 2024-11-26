package br.com.fiap.lanchonete.produtoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.enums.CategoriaEnum;
import br.com.fiap.lanchonete.produtoservicefase4.domain.provider.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindProdutoByCategoriaUsecaseTest {

    private FindProdutoByCategoriaUsecase findProdutoByCategoriaUsecase;

    @Mock
    private ProdutoRepositoryPort produtoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        findProdutoByCategoriaUsecase = new FindProdutoByCategoriaUsecase(produtoPort);
    }

    @Test
    public void findByCategoria_HappyPath() {
        List<Produto> produtos = Collections.singletonList(new Produto());
        when(produtoPort.findByCategoria(any(CategoriaEnum.class))).thenReturn(produtos);

        List<Produto> result = findProdutoByCategoriaUsecase.findByCategoria(CategoriaEnum.BEBIDA);

        assertEquals(produtos, result);
    }

    @Test
    public void findByCategoria_NullCategoria() {
        List<Produto> produtos = Collections.singletonList(new Produto());
        when(produtoPort.findAll()).thenReturn(produtos);

        List<Produto> result = findProdutoByCategoriaUsecase.findByCategoria(null);

        assertEquals(produtos, result);
    }
}

