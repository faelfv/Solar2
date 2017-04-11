package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Sugestao;
import service.SugestaoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SugestaoTest {
	SugestaoService sugestaoS;
	Sugestao sugestao, copia;
	static int id = 0;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		sugestao = new  Sugestao();
		sugestao.setId(id);
		sugestao.setTitulo("Reclamação");
		sugestao.setSetor("Recursos Humanos");
		sugestao.setColab("asd");
		sugestao.setAval("qwe");
		sugestao.setDescricao("Atendemento foi horrível.");
		sugestao.setSetorid(1);
		sugestao.setStatus("S");
		copia = new  Sugestao();
		copia.setId(id);
		copia.setTitulo("Reclamação");
		copia.setSetor("Recursos Humanos");
		copia.setColab("asd");
		copia.setAval("qwe");
		copia.setDescricao("Atendemento foi horrível.");
		copia.setSetorid(1);
		copia.setStatus("S");
		System.out.println(sugestao);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		Sugestao fixture = new Sugestao();
		fixture.setId(id);
		fixture.setTitulo("Aquisição de Bolsa");
		fixture.setSetor("Bolsa");
		fixture.setColab("zxc");
		fixture.setAval("abc");
		fixture.setDescricao("Quais são os requisitos para conseguir uma bolsa da faculdade.");
		fixture.setSetorid(3);
		fixture.setStatus("S");
		SugestaoService novoService = new SugestaoService();
		Sugestao novo = novoService.carregar(2);
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		id = sugestaoS.incluir(sugestao);
		id = sugestao.getId();
		System.out.println(sugestao);
		copia.setId(2);
		assertEquals("testa criacao", sugestao, copia);
	}
	
	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		sugestao.setTitulo("Bolsa");
		sugestao.setSetor("Bolssa2");
		sugestao.setColab("abc");
		sugestao.setAval("asd");
		copia.setTitulo("Bolsa");
		copia.setSetor("Bolssa2");
		copia.setColab("abc");
		copia.setAval("asd");
		sugestaoS.atualizar(sugestao);
		sugestao = sugestaoS.carregar(sugestao.getId());
		assertEquals("testa atualizacao", sugestao, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(0);
		copia.setTitulo(null);
		copia.setSetor(null);
		copia.setColab(null);
		copia.setAval(null);
		copia.setDescricao(null);
		copia.setSetorid(0);
		copia.setStatus(null);
		sugestaoS.excluir(id);
		sugestao = sugestaoS.carregar(id);
		assertEquals("testa exclusao", sugestao, copia);
	}
}
