package servlet.member2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member2.Member2DAO;
import model.member2.Member2DTO;
import model.util.UserSHA256;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/Member/userinfo_insert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("userinfo_insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member2DAO dao = Member2DAO.getInstance();
		Member2DTO dto = new Member2DTO();
		
		request.setCharacterEncoding("utf-8");
		
		dto.setName(request.getParameter("name"));
		dto.setUserid(request.getParameter("userid"));
		String passwd = UserSHA256.getSHA256(request.getParameter("password"));
		dto.setPasswd(passwd);
		dto.setEmail(request.getParameter("email"));
		int row= dao.memberInsert(dto);
		
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("userinfo_insert_pro.jsp");
		rd.forward(request, response);
	}

}
