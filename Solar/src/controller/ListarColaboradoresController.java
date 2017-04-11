package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FuncaoColaborador;

import service.FuncaoColaboradorService;

/**
 * Servlet implementation class ListarColaboradoresController
 */
@WebServlet("/ListarColaboradores.do")
public class ListarColaboradoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	FuncaoColaboradorService cs = new FuncaoColaboradorService();
		
		List<FuncaoColaborador> lista = cs.carregarTodosColab();
		
		
        //enviar para o jsp
        request.setAttribute("lista", lista);
        
        RequestDispatcher view = 
        request.getRequestDispatcher("ListarColab.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
