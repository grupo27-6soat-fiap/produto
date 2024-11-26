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

public class UpdateProdutoUsecaseTest {

    private UpdateProdutoUsecase updateProdutoUsecase;

    @Mock
    private ProdutoRepositoryPort produtoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        updateProdutoUsecase = new UpdateProdutoUsecase(produtoPort);
    }

    @Test
    public void updateProduto_HappyPath() {
        Produto produto = new Produto();
        when(produtoPort.get(1L)).thenReturn(produto);
        when(produtoPort.save(produto)).thenReturn(produto);

        Produto result = updateProdutoUsecase.update(1L, produto);

        assertEquals(produto, result);
    }

    @Test
    public void updateProduto_NullId() {
        Produto produto = new Produto();
        Produto result = updateProdutoUsecase.update(null, produto);

        assertNull(result);
    }



    @Test
    public void updateProduto_NonExistingId() {
        Produto produto = new Produto();
        when(produtoPort.get(1L)).thenReturn(null);

        Produto result = updateProdutoUsecase.update(1L, produto);

        assertNull(result);
    }
}
