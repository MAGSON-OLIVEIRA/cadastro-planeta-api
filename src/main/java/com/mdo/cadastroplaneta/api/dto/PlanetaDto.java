package com.mdo.cadastroplaneta.api.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class PlanetaDto {
	
	private String id;
	private String nome;
	private String clima;
	// são optional por isso usar o optional, o campo ser vazio
	private Optional<String>  terreno = Optional.empty();
	
	private Integer quantidadeDeAparicoesStarWars; 
	
	private boolean statusAparicoesStarWars = false;
	
	public boolean isStatusAparicoesStarWars() {
		return statusAparicoesStarWars;
	}
	public void setStatusAparicoesStarWars(boolean statusAparicoesStarWars) {
		this.statusAparicoesStarWars = statusAparicoesStarWars;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome dever conter entre 3 e 200 caracteres.")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty(message = "Clina não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Clima dever conter entre 3 e 200 caracteres.")
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	
	public Optional<String> getTerreno() {
		return terreno;
	}
	public void setTerreno(Optional<String> terreno) {
		this.terreno = terreno;
	}
	public Integer getQuantidadeDeAparicoesStarWars() {
		return quantidadeDeAparicoesStarWars;
	}
	public void setQuantidadeDeAparicoesStarWars(Integer quantidadeDeAparicoesStarWars) {
		this.quantidadeDeAparicoesStarWars = quantidadeDeAparicoesStarWars;
	}
	
}
