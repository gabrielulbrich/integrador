package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.CategoriaModel;
import models.RegisterModel;

@WebServlet("/categoria")
public class CadastrarCategoriaCaptcha extends HttpServlet {
	CategoriaModel cat = new CategoriaModel();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/categoria.xhtml").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cat.setCateg(request.getParameter("categ"));
		if (cat.Gravar()) {
			HttpSession session = request.getSession();
			session.setAttribute("mensagem", cat.getCateg() + " cadastrado com sucesso!");
			response.sendRedirect("/Integrador/categoria");

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