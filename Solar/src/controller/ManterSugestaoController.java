package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FuncaoSugestao;
import model.Sugestao;
import service.SugestaoService;

/**
 * Servlet implementation class ManterSugestaoController
 */
@WebServlet("/ManterSugestao.do")
public class ManterSugestaoController extends HttpServlet {
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
		    String pId = request.getParameter("id");
			String pSetorid = request.getParameter("setorId");
			String pTitulo = request.getParameter("titulo");
			String pSetor = request.getParameter("setor");
			String pColab = request.getParameter("colab");
			String pAval = request.getParameter("aval");
	        String pDescricao = request.getParameter("descricao");
			String pAprovado = request.getParameter("aprovado");
	        
	        //instanciar o javabean
	        Sugestao sugestao = new Sugestao();
	        FuncaoSugestao fsug = new FuncaoSugestao();
	        if(pId == null){
	        	int pIdd = Integer.parseInt(pId);
	        	sugestao.setId(pIdd);
	        }
	        if(pSetorid == null){
	        	int pSetoridd = Integer.parseInt(pSetorid);
	        	sugestao.setSetorid(pSetoridd);
	        }
	        sugestao.setTitulo(pTitulo);
	        sugestao.setSetor(pSetor);
	        sugestao.setColab(pColab);
	        sugestao.setAval(pAval);
	        sugestao.setDescricao(pDescricao);
	        fsug.setAprovado(pAprovado);
	        
	        //instanciar o service
	        SugestaoService cs = new SugestaoService();
	        cs.incluir(sugestao);
	        sugestao = cs.carregar(sugestao.getId());
	        
	        //enviar para o jsp
	        request.setAttribute("sugestao", sugestao);
	        
	        RequestDispatcher view = 
	        request.getRequestDispatcher("Sugestao.jsp");
	        view.forward(request, response);
	}

}
