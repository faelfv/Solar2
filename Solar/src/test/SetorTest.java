package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Setor;
import service.SetorService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetorTest {
	SetorService setorS;
	Setor setor, copia;
	static int idSetor = 0;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		setor = new  Setor();
		setor.setIdSetor(idSetor);
		setor.setNome("Recursos Humanos");
		setor.setFone("1122222222");
		copia = new  Setor();
		copia.setIdSetor(idSetor);
		copia.setNome("Recursos Humanos");
		copia.setFone("1122222222");
		System.out.println(setor);
		System.out.println(copia);
		System.out.println(idSetor);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		Setor fixture = new Setor();
		fixture.setIdSetor(idSetor);
		fixture.setNome("Patrimonio");
		fixture.setFone("1122222223");
		SetorService novoService = new SetorService();
		Setor novo = novoService.carregar(2);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		idSetor = setorS.incluir(setor);
		idSetor = setor.getIdSetor();
		System.out.println(setor);
		copia.setIdSetor(2);
		assertEquals("testa criacao", setor, copia);
	}
	
	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		setor.setFone("1133333333");
		setor.setNome("Patrimonio2");
		copia.setFone("1133333333");
		copia.setNome("Patrimonio2");
		setorS.atualizar(setor);
		setor = setorS.carregar(setor.getIdSetor());
		assertEquals("testa atualizacao", setor, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setIdSetor(0);
		copia.setNome(null);
		copia.setFone(null);
		setorS.excluir(idSetor);
		setor = setorS.carregar(idSetor);
		assertEquals("testa exclusao", setor, copia);
	}
	/*
	@Test
	public void test04CarregarSugestoes(){
		System.out.print("carregar sugestoes");
		
	}
	*/


}
