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
 * Servlet implementation class BoardWirteServlet
 */
@WebServlet("/Board/board_write.do")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("page", nowpage);
		RequestDispatcher rd = request.getRequestDispatcher("board_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Board2DAO dao = Board2DAO.getInstance();
		Board2DTO board = new Board2DTO();
		
		board.setUserid(request.getParameter("userid"));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		int boardid=Integer.parseInt(request.getParameter("boardid"));
		board.setBoardid(boardid);
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int row = dao.boardWrite(board);
		
		request.setAttribute("row", row);
		request.setAttribute("page", nowpage);
		System.out.println(board.getContents());
		RequestDispatcher rd = request.getRequestDispatcher("board_write_pro.jsp");
		rd.forward(request, response);
	}

}
