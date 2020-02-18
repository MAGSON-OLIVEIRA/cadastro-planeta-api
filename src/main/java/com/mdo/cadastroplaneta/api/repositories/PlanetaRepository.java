package com.mdo.cadastroplaneta.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mdo.cadastroplaneta.api.entities.Planeta;

@Transactional(readOnly = true)
public interface PlanetaRepository  extends MongoRepository<Planeta, String> {
	// transactional ajuda na perfomace  quando for consulta. Nao bloqueia o banco de dado 
	// como os 3 metodos cusotmizados são de apenas consulta. 
	@Transactional(readOnly = true)
	Planeta findByNome(String nome);

}

