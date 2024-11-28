package br.com.fiap.foodtech.produtoservicefase4.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import br.com.fiap.foodtech.produtoservicefase4.domain.enums.CategoriaEnum;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDto {
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private CategoriaEnum categoria;
	@NotNull
	private BigDecimal preco;
	private String descricao;
	private List<String> imagens;
}
