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
 * Servlet implementation class ManterColaboradorController
 */
@WebServlet("/ManterColaborador.do")
public class ManterColaboradorController extends HttpServlet {
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
		String pNome = request.getParameter("nome");
		String pCpf = request.getParameter("cpf");
		String pSexo = request.getParameter("sexo");
		String pTel = request.getParameter("tel");
		String pEmail = request.getParameter("email");
		String pLogin = request.getParameter("login");
        String pSenha = request.getParameter("senha");
        
        //instanciar o javabean
        FuncaoColaborador colaborador = new FuncaoColaborador();
        colaborador.setNome(pNome);
		colaborador.setCpf(pCpf);
		colaborador.setSexo(pSexo);
		colaborador.setTel(pTel);
		colaborador.setEmail(pEmail);
		colaborador.setLogin(pLogin);
        colaborador.setSenha(pSenha);
        
        //instanciar o service
        FuncaoColaboradorService cs = new FuncaoColaboradorService();
        cs.criar(colaborador);
        colaborador = cs.carregar(colaborador.getLogin());
        
        //enviar para o jsp
        request.setAttribute("colaborador", colaborador);
        
        RequestDispatcher view = 
        request.getRequestDispatcher("Colaborador.jsp");
        view.forward(request, response);
	}

}
