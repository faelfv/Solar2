package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FuncaoColaborador;
import service.FuncaoColaboradorService;

/**
 * Servlet implementation class ManterRecuperarSenhaController
 */
@WebServlet("/ManterRecuperarSenha.do")
public class ManterRecuperarSenhaController extends HttpServlet {
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
        String pSenha = request.getParameter("senha");
        String pSenhax = request.getParameter("senhax");
		
        //instanciar o javabean
        FuncaoColaborador colaborador = new FuncaoColaborador();
        colaborador.setLogin(pLogin);
        colaborador.setSenha(pSenha);
		colaborador.setSenha(pSenhax);
        
        //instanciar o service
        FuncaoColaboradorService cs = new FuncaoColaboradorService();
        cs.recuperar(colaborador);
        colaborador = cs.carregar(colaborador.getLogin());
        
        //enviar para o jsp
        request.setAttribute("Colaborador", colaborador);
        
        RequestDispatcher view = 
        request.getRequestDispatcher("Colaborador.jsp");
        view.forward(request, response);

	}

}
