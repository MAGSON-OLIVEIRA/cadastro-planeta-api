package com.mdo.cadastroplaneta.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mdo.cadastroplaneta.api.entities.Planeta;
import com.mdo.cadastroplaneta.api.repositories.PlanetaRepository;
import com.mdo.cadastroplaneta.api.services.PlanetaService;
// carregar o contexto de texto do spring
// carregando o profiles de "test"
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PlanetaServiceTest {
	// usar o MockBean para mockar o repositorio, por que a classe reposytory ja foi testada em outro arquivo de test. 
	// mocka o repository, então vamos somente simular suas chamadas. 
	@MockBean
	private PlanetaRepository planetaRepository;
	
	// vamos usar uma stancia do service para testarmos. 
	@Autowired
	private PlanetaService planetaService;
	
	
	
	@Before
	public void setUp() throws Exception {
		// criar os mock para as chamatas que vamos usar. do repository
		// usar o BDD Mockito
		BDDMockito.given(this.planetaRepository.insert(Mockito.any(Planeta.class))).willReturn(new Planeta());
		BDDMockito.given(this.planetaRepository.findByNome(Mockito.anyString())).willReturn(new Planeta());

	}
	
	@Test
	public void salvar()throws Exception{
		Planeta planeta = this.planetaService.persistir(new Planeta());
		assertNotNull(planeta);
	}
	
	@Test
	public void buscarPorCnpj() throws Exception {
		Optional<Planeta> empresa = this.planetaService.buscarPorNome("");
		assertTrue(empresa.isPresent());
	}
	
	
}
