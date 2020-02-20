package com.mdo.cadastroplaneta.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdo.cadastroplaneta.api.entities.Planeta;
import com.mdo.cadastroplaneta.api.repositories.PlanetaRepository;
import com.mdo.cadastroplaneta.api.services.PlanetaService;


// anotação pra definir classe como serviço
@Service
public class PlanetaServiceImpl  implements PlanetaService {
	
	// adicionando log como boas praticas
	private static final Logger log = LoggerFactory.getLogger(PlanetaServiceImpl.class);
	
	@Autowired
	PlanetaRepository planetaRepository;

	@Override
	public Optional<Planeta> buscarPorNome(String nome) {
		log.info("buscar uma planeta por nome {}", nome );
		return Optional.ofNullable(planetaRepository.findByNome(nome));
	}

	@Override
	public Planeta persistir(Planeta planeta) {
		log.info("salvar nome na base {} ", planeta.toString());
		return (Planeta) this.planetaRepository.insert(planeta);
	}

	@Override
	public List<Planeta> list() {
		log.info("lista de planeta");
		return this.planetaRepository.findAll();
	}
	
	

}
