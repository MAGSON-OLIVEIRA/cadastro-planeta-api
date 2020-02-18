package com.mdo.cadastroplaneta.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mdo.cadastroplaneta.api.dto.PlanetsDto;
import com.mdo.cadastroplaneta.api.services.ApiGerraNasEstrelasServices;
//anotação pra definir classe como serviço
@Service
public class ApiGerraNasEstrelasServiceRest implements ApiGerraNasEstrelasServices {
	
	@Autowired(required = true)
	RestTemplate restTemplate = new RestTemplate();
	
	public final static String  URL_PLA = "https://swapi.co/api/planets";
	private int qtdFilmes = 0;
	private Boolean status = false;
	
	@Override
	public int getListaFilmesGerraNasEstrelas(String name) {
		Optional<PlanetsDto> retorno = Optional.empty();
		int cont = 1;
		do {
			retorno = Optional.of(restTemplate
					.getForEntity(cont == 1 ? URL_PLA : URL_PLA + "/?page=" + cont, PlanetsDto.class).getBody());
			retorno.get().getResults().forEach(result -> {
				if (result.getName().equals(name)) {
					qtdFilmes = result.getFilms().size();
					status = true;
				}
			});
			cont++;
			if (status) {
				break;
			}
		} while (retorno.get().getNext() != null);
		return qtdFilmes;
	}
	
	

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
	    return builder.build();
	}	
}
