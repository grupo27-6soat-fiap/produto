package br.com.fiap.lanchonete.produtoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.provider.ProdutoRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProdutoByIdUsecase {

	private final ProdutoRepositoryPort produtoPort;

	public Produto get(Long id) {
		return produtoPort.get(id);
	}

}
