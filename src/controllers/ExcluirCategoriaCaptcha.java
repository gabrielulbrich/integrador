package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.CategoriaModel;

@WebServlet("/exccategoria")
public class ExcluirCategoriaCaptcha extends HttpServlet {
	CategoriaModel cat = new CategoriaModel();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/exccategoria.xhtml").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cat.setCateg(request.getParameter("categ"));
		if (cat.Excluir()) {
			HttpSession session = request.getSession();
			session.setAttribute("mensagem", cat.getCateg() + " excluido com sucesso!");
			response.sendRedirect("/Integrador/exccategoria");

		} else {
			invalidaSessao(request, response);
		}
		
		}

	public static void invalidaSessao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.setAttribute("mensagem", "Erro ao se cadastrar");
	}

}