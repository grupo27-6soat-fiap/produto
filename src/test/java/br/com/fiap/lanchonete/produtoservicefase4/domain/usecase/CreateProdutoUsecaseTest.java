package br.com.fiap.lanchonete.produtoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.provider.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateProdutoUsecaseTest {

    private CreateProdutoUsecase createProdutoUsecase;

    @Mock
    private ProdutoRepositoryPort produtoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createProdutoUsecase = new CreateProdutoUsecase(produtoPort);
    }

    @Test
    public void createProduto_HappyPath() {
        Produto produto = new Produto();
        when(produtoPort.save(any(Produto.class))).thenReturn(produto);

        Produto result = createProdutoUsecase.create(produto);

        assertEquals(produto, result);
    }

    @Test
    public void createProduto_NullProduto() {
        when(produtoPort.save(null)).thenReturn(null);

        Produto result = createProdutoUsecase.create(null);

        assertEquals(null, result);
    }
}