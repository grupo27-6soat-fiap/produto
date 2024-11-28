package br.com.fiap.foodtech.produtoservicefase4.infra.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.foodtech.produtoservicefase4.domain.enums.CategoriaEnum;
import br.com.fiap.foodtech.produtoservicefase4.infra.db.entities.ProdutoEntity;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByCategoria(CategoriaEnum categoria);
    List<ProdutoEntity> findAll();
}
