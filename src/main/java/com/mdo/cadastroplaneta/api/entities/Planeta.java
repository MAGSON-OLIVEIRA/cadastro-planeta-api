package com.mdo.cadastroplaneta.api.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("planeta")
public class Planeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;

	private Date dataCriacao;
	private Date dataAtualizacao;
	private String nome;
	private String clima;
	private String terreno;
	private Integer quantidadeDeAparicoesStarWars; 
	
	public Planeta() {
		
	}
	
	@Transient
	public Optional<String> getTerrenoOpt() {
		return Optional.ofNullable(terreno);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="dataCriacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(name="dataAtualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePresist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Column(name="nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="dataAtualizacao", nullable = false)
	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	@Column(name="dataAtualizacao", nullable = false)
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	@Column(name="dataAtualizacao", nullable = false)
	public Integer getQuantidadeDeAparicoesStarWars() {
		return quantidadeDeAparicoesStarWars;
	}

	public void setQuantidadeDeAparicoesStarWars(Integer quantidadeDeAparicoesStarWars) {
		this.quantidadeDeAparicoesStarWars = quantidadeDeAparicoesStarWars;
	}

	@Override
	public String toString() {
		return "Planeta [id=" + id + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", nome="
				+ nome + ", clima=" + clima + ", terreno=" + terreno + ", quantidadeDeAparicoesStarWars="
				+ quantidadeDeAparicoesStarWars + "]";
	}


	
}
