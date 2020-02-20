package com.mdo.cadastroplaneta.api.services;

import java.util.List;
import java.util.Optional;

import com.mdo.cadastroplaneta.api.entities.Planeta;

public interface PlanetaService {
	// assinatura do método para implemntação da classe que a implementar. 
	/**
	 * 
	 * @param nome
	 * @return
	 */
	Optional<Planeta> buscarPorNome(String nome);
	/**
	 * 
	 * @param empresa
	 * @return
	 */
	Planeta persistir(Planeta planeta);
	
	/**
	 * 
	 * @return
	 */
	List<Planeta> list();

}
