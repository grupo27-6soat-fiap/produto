package br.com.fiap.foodtech.produtoservicefase4.domain.provider;



import java.util.List;

import br.com.fiap.foodtech.produtoservicefase4.domain.entities.Produto;
import br.com.fiap.foodtech.produtoservicefase4.domain.enums.CategoriaEnum;


public interface ProdutoRepositoryPort {
	void delete(Produto produto);

	List<Produto> findAll();

	List<Produto> findByCategoria(CategoriaEnum categoria);

	Produto get(Long id);

	Produto save(Produto produto);
}
