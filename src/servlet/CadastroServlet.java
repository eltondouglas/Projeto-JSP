package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;
import utils.DateUtil;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao = new UsuarioDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastroServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String usuario = request.getParameter("usuario");

		if (acao != null) {
			switch (acao) {
			case "delete": {
				dao.delete(usuario);
				dispatcherHandler(request, response);
				break;
			}

			case "alter": {
				Usuario user = dao.buscar(usuario);
				request.setAttribute("user", user);
				dispatcherHandler(request, response);
				break;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + acao);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("reset")) {
			
			dispatcherHandler(request, response);
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String dataNasc = request.getParameter("datanasc");
			String cep = request.getParameter("cep");
			String cidade = request.getParameter("cidade");
			String rua = request.getParameter("rua");
			Usuario usuario = new Usuario(nome, sobrenome, DateUtil.stringToDate(dataNasc, DateUtil.PATTERN), cep,
					cidade, rua);

			if (id == null || id.isEmpty() && dao.validarNome(usuario.getNome())) {
				dao.incluir(usuario);
				dispatcherHandler(request, response);
				return;
			} else if(id != null && !id.isEmpty()) {
				usuario.setId(id);
				dao.atualizar(usuario);
				dispatcherHandler(request, response);
				return;
			}
			request.setAttribute("msg", "O usuário já existe!");
			dispatcherHandler(request, response);
		}

	}

	private void dispatcherHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrousuario.jsp");
		try {
			request.setAttribute("lista", dao.listarTudo());
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}

}
