package br.com.fiap.lanchonete.produtoservicefase4.domain.provider;



import br.com.fiap.lanchonete.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.produtoservicefase4.domain.enums.CategoriaEnum;

import java.util.List;


public interface ProdutoRepositoryPort {
	void delete(Produto produto);

	List<Produto> findAll();

	List<Produto> findByCategoria(CategoriaEnum categoria);

	Produto get(Long id);

	Produto save(Produto produto);
}
