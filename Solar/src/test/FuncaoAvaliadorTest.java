package test;

import static org.junit.Assert.assertEquals;
import service.FuncaoAvaliadorService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import model.FuncaoAvaliador;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncaoAvaliadorTest {
	FuncaoAvaliadorService funcaoAvaliadorService;
		FuncaoAvaliador avaliador, copia;
		static String login = "";

		@Before
		public void setUp() throws Exception {
			System.out.println("setup");
			avaliador = new  FuncaoAvaliador();
			avaliador.setLogin(login);
			avaliador.setSetor(1);
			avaliador.setNome("lucas");
			avaliador.setSenha("qwe");
			copia = new FuncaoAvaliador();
			copia.setLogin(login);
			copia.setSetor(1);
			copia.setNome("lucas");
			copia.setSenha("qwe");
			System.out.println(avaliador);
			System.out.println(copia);
			System.out.println(login);
		}
		/*
		*/
		@Test
		public void test00Carregar() {
			System.out.println("carregar");
			FuncaoAvaliador fixture = new  FuncaoAvaliador();
			fixture.setLogin(login);
			fixture.setSetor(3);
			fixture.setNome("jose");
			fixture.setSenha("asd");
			FuncaoAvaliadorService novoService = new FuncaoAvaliadorService();
			FuncaoAvaliador novo = novoService.carregar("abc");
			assertEquals("testa inclusao", novo, fixture);
		}
		
		@Test
		public void test01Criar() {
			System.out.println("criar");
			login = funcaoAvaliadorService.incluir(avaliador);
			System.out.println(login);
			copia.setLogin(login);
			assertEquals("testa criacao", avaliador, copia);
		}
		
		@Test
		public void test02Atualizar() {
			System.out.println("atualizar");
			avaliador.setSenha("ttttf");
			copia.setSenha("ttttf");		
			funcaoAvaliadorService.atualizar(avaliador);
			avaliador = funcaoAvaliadorService.carregar(avaliador.getLogin());
			assertEquals("testa atualizacao", avaliador, copia);
		}
	
		@Test
		public void test03Excluir() {
			System.out.println("excluir");
			copia.setLogin("");
			copia.setSetor(-1);
			copia.setNome(null);
			copia.setSenha(null);
			funcaoAvaliadorService.excluir(login);
			avaliador = funcaoAvaliadorService.carregar(login);
			assertEquals("testa exclusao", avaliador, copia);
		}

}
