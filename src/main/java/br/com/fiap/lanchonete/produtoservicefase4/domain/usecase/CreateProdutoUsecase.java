package br.com.fiap.lanchonete.produtoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.provider.ProdutoRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class CreateProdutoUsecase {

    private final ProdutoRepositoryPort produtoPort;

    public Produto create(Produto produto) {
        return produtoPort.save(produto);
    }

}