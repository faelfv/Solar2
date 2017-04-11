package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FuncaoAvaliador;
import service.FuncaoAvaliadorService;

/**
 * Servlet implementation class ManterAvaliadorController
 */
@WebServlet("/ManterAvaliador.do")
public class ManterAvaliadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pLogin = request.getParameter("login");
		String pSetor = request.getParameter("setor");
        String pNome = request.getParameter("nome");
        String pSenha = request.getParameter("senha");
        
        //instanciar o javabean
        FuncaoAvaliador avaliador = new FuncaoAvaliador();
        avaliador.setLogin(pLogin);
		if(pSetor == null){
         int pSetoor = Integer.parseInt(pSetor);
         	avaliador.setSetor(pSetoor);
		
		}
        avaliador.setNome(pNome);
        avaliador.setSenha(pSenha);
        
        //instanciar o service
        FuncaoAvaliadorService cs = new FuncaoAvaliadorService();
        cs.incluir(avaliador);
        avaliador = cs.carregar(avaliador.getLogin());
        
        //enviar para o jsp
        request.setAttribute("avaliador", avaliador);
        
        RequestDispatcher view = 
        request.getRequestDispatcher("Avaliador.jsp");
        view.forward(request, response);
	}

}
