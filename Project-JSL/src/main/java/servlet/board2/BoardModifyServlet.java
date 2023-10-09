package servlet.board2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.board2.Board2DAO;
import model.board2.Board2DTO;


/**
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/Board/board_modify.do")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int nowpage = Integer.parseInt(request.getParameter("page"));

		Board2DAO dao = Board2DAO.getInstance();
		
		Board2DTO board = dao.boardView(idx);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("board", board);
		RequestDispatcher rd = request.getRequestDispatcher("board_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Board2DAO dao = Board2DAO.getInstance();
		Board2DTO board = new Board2DTO();
		board.setIdx(Integer.parseInt(request.getParameter("idx")));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		int boardid=Integer.parseInt(request.getParameter("boardid"));
		board.setBoardid(boardid);
		int nowpage = Integer.parseInt(request.getParameter("page"));

		
		int row = dao.boardUpdate(board);
		
		response.sendRedirect("/Board/board_list.do?page="+nowpage);
	}

}
