package br.com.fiap.lanchonete.produtoservicefase4.domain.usecase;

import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.provider.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class GetProdutoByIdUsecaseTest {

    private GetProdutoByIdUsecase getProdutoByIdUsecase;

    @Mock
    private ProdutoRepositoryPort produtoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getProdutoByIdUsecase = new GetProdutoByIdUsecase(produtoPort);
    }

    @Test
    public void getProdutoById_HappyPath() {
        Produto produto = new Produto();
        when(produtoPort.get(1L)).thenReturn(produto);

        Produto result = getProdutoByIdUsecase.get(1L);

        assertEquals(produto, result);
    }

    @Test
    public void getProdutoById_NullId() {
        when(produtoPort.get(null)).thenReturn(null);

        Produto result = getProdutoByIdUsecase.get(null);

        assertNull(result);
    }
}