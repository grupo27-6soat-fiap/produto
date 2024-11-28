package br.com.fiap.foodtech.produtoservicefase4.domain.usecase;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.fiap.foodtech.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.foodtech.produtoservicefase4.domain.provider.ProdutoRepositoryPort;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteProdutoUsecase {

	private final ProdutoRepositoryPort produtoPort;

    public void delete(Produto produto) {
        if(Objects.nonNull(produto))
        	produtoPort.delete(produto);
    }

}
