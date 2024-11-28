package br.com.fiap.foodtech.produtoservicefase4.domain.usecase;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.fiap.foodtech.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.foodtech.produtoservicefase4.domain.provider.ProdutoRepositoryPort;


@Service
@Transactional
@RequiredArgsConstructor
public class CreateProdutoUsecase {

    private final ProdutoRepositoryPort produtoPort;

    public Produto create(Produto produto) {
        return produtoPort.save(produto);
    }

}