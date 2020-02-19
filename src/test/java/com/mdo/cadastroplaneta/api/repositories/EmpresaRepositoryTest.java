package com.mdo.cadastroplaneta.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mdo.cadastroplaneta.api.entities.Planeta;



@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
	@Autowired
	private PlanetaRepository planetaRepository;
	
	private static final String NOME = "PLANETA TERRA";
	
	
	@Before
	public void setUp() throws Exception{
		Planeta planeta = new Planeta();
		planeta.setClima("FRIO");
		planeta.setTerreno("TERRA");
		planeta.setNome(NOME);
		this.planetaRepository.save(planeta);
	}
	
	@After
	public final void tearDown() {
		this.planetaRepository.deleteAll();
	}
	
	@Test
	public void testBuscaPorCnpj() {
		Planeta planeta = this.planetaRepository.findByNome(NOME);
		assertEquals(NOME, planeta.getNome());
	}
	
}
