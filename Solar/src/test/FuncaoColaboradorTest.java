package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import service.FuncaoColaboradorService;
import model.FuncaoColaborador;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncaoColaboradorTest {
			FuncaoColaboradorService funcaoColaboradorService;
			FuncaoColaborador colaborador, copia;
			static String login = " ";
			
			@Before
			public void setUp() throws Exception {
				System.out.println("setup");
				colaborador = new  FuncaoColaborador();
				colaborador.setLogin(login);
				colaborador.setNome("lucas");
				colaborador.setCpf("413.444.567-41");
				colaborador.setSexo("M");
				colaborador.setTel("(11)3244-3344");
				colaborador.setEmail("lucas@hotmail.com");
				colaborador.setSenha("asd");
				copia = new  FuncaoColaborador();
				copia.setLogin(login);
				copia.setNome("lucas");
				copia.setCpf("413.444.567-41");
				copia.setSexo("M");
				copia.setTel("(11)3244-3344");
				copia.setEmail("lucas@hotmail.com");
				copia.setSenha("asd");
				System.out.println(colaborador);
				System.out.println(copia);
				System.out.println(login);
			}
			
			@Test
			public void test00Carregar() {
				System.out.println("carregar");
				FuncaoColaborador fixture = new FuncaoColaborador();
				fixture.setLogin(login);
				fixture.setNome("roger");
				fixture.setCpf("123.456.321-99");
				fixture.setSexo("M");
				fixture.setTel("(11)5467-1423");
				fixture.setEmail("roger@hotmail.com");
				fixture.setSenha("rty");
				FuncaoColaboradorService novoService = new FuncaoColaboradorService();
				FuncaoColaborador novo = novoService.carregar("zxc");
				assertEquals("testa inclusao", novo, fixture);
			}
		
			@Test
			public void test01Criar() {
				System.out.println("criar");
				login = funcaoColaboradorService.criar(colaborador);
				login = colaborador.getLogin();
				System.out.println(login);
				copia.setLogin(login);
				assertEquals("testa criacao", colaborador, copia);
			}
			
			@Test
			public void test02Atualizar() {
				System.out.println("atualizar");
				colaborador.setSenha("ttttf");
				copia.setSenha("ttttf");		
				funcaoColaboradorService.atualizar(colaborador);
				colaborador = funcaoColaboradorService.carregar(colaborador.getLogin());
				assertEquals("testa atualizacao", colaborador, copia);
			}
			
			@Test
			public void test03RecuperarSenha(){
				System.out.println("recuperar");
				colaborador.setSenha("ggd");
				copia.setSenha("ggd");
				funcaoColaboradorService.atualizar(colaborador);
				colaborador = funcaoColaboradorService.carregar(colaborador.getLogin());
				assertEquals("testa recuperar", colaborador, copia);
				
			}

			@Test
			public void test04Excluir() {
				System.out.println("excluir");
				copia.setLogin("");
				copia.setNome(null);
				copia.setCpf(null);
				copia.setSexo(null);
				copia.setTel(null);
				copia.setEmail(null);
				copia.setSenha(null);
				funcaoColaboradorService.excluir(login);
				colaborador = funcaoColaboradorService.carregar(login);
				assertEquals("testa exclusao", colaborador, copia);
			}
		
			
	}


