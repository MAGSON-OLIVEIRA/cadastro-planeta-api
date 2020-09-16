package com.mdo.cadastroplaneta.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdo.cadastroplaneta.api.dto.PlanetaDto;
import com.mdo.cadastroplaneta.api.entities.Planeta;
import com.mdo.cadastroplaneta.api.response.Response;
import com.mdo.cadastroplaneta.api.services.ApiGerraNasEstrelasServices;
import com.mdo.cadastroplaneta.api.services.PlanetaService;

@RestController
@RequestMapping("api/planeta")
@CrossOrigin(origins="*")	
public class CadastroPlanetaController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPlanetaController.class);
	
	@Autowired
	private PlanetaService planetaService;
	@Autowired(required = true)
	private ApiGerraNasEstrelasServices apiGerraNasEstrelasServiceRest;

	
	public CadastroPlanetaController() {
		
	}

	@PostMapping
	public ResponseEntity<Response<PlanetaDto>> cadastrar(@Valid @RequestBody PlanetaDto dto,
			BindingResult result) {
		Response<PlanetaDto> response = new Response<PlanetaDto>();
		Planeta planeta = new Planeta();
		extractedPlaneta(dto, planeta);
		validarDadosExistentes(dto, result);
		if (result.hasErrors()) {
			log.info("Error");
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		planeta.setReturnStarWars(apiGerraNasEstrelasServiceRest.getListaFilmesGerraNasEstrelas(dto.getNome()));
		this.planetaService.persistir(planeta);
		response.setData(extractedDto(planeta));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Response<List<PlanetaDto>>> list() {
		Response<List<PlanetaDto>> list = new Response<List<PlanetaDto>>();
		List<Planeta> listPlaneta = this.planetaService.list();
		List<PlanetaDto> listDto = extractedListPlaneta(listPlaneta);
		list.setData(listDto);
		return ResponseEntity.ok(list);
	}
	
	private List<PlanetaDto> extractedListPlaneta(List<Planeta> listPlaneta) {
		List<PlanetaDto> listDto = new ArrayList<PlanetaDto>();
		listPlaneta.forEach(planeta -> listDto.add(extractedDto(planeta)));
		return listDto;
	}

	private PlanetaDto extractedDto(Planeta planeta) {
		PlanetaDto planetaDto = new PlanetaDto();
		planetaDto.setClima(planeta.getClima());
		planetaDto.setId(planeta.getId());
		planetaDto.setNome(planeta.getNome());
		planetaDto.setQuantidadeDeAparicoesStarWars(planeta.getReturnStarWars().getQtdFilmes());
		planetaDto.setStatusAparicoesStarWars(planeta.getReturnStarWars().isStatus());
		planeta.getTerrenoOpt().ifPresent(var -> planetaDto.setTerreno(Optional.of(var)));
		return planetaDto;
	}

	private void extractedPlaneta(PlanetaDto planetaDto, Planeta planeta) {
		planeta.setClima(planetaDto.getClima());
		planeta.setNome(planetaDto.getNome());
		planetaDto.getTerreno().ifPresent(var -> planeta.setTerreno(var));
	}

	private void validarDadosExistentes(PlanetaDto dto, BindingResult result) {
		Optional<Planeta> planeta = this.planetaService.buscarPorNome(dto.getNome());
		log.info(planeta.toString());
		if (planeta.isPresent()) {
			result.addError(new ObjectError("Nome: ", "Nome já cadastrado."));
		}
		
	}
	
}
