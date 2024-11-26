package br.com.fiap.lanchonete.produtoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.provider.ProdutoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class DeleteProdutoUsecaseTest {

    private DeleteProdutoUsecase deleteProdutoUsecase;

    @Mock
    private ProdutoRepositoryPort produtoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        deleteProdutoUsecase = new DeleteProdutoUsecase(produtoPort);
    }

    @Test
    public void deleteProduto_HappyPath() {
        Produto produto = new Produto();
        doNothing().when(produtoPort).delete(any(Produto.class));

        deleteProdutoUsecase.delete(produto);
    }

    @Test
    public void deleteProduto_NullProduto() {
        doThrow(IllegalArgumentException.class).when(produtoPort).delete(null);

        deleteProdutoUsecase.delete(null);
    }
}
