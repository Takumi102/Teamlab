package servlet.member2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member2.Member2DAO;
import model.member2.Member2DTO;
import model.util.UserSHA256;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/Member/userinfo_login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("userinfo_login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member2DAO dao = Member2DAO.getInstance();
		
		String userid = request.getParameter("userid");
		String passwd = UserSHA256.getSHA256(request.getParameter("password"));
		
		int row = dao.memberLogin(userid, passwd);
		
		System.out.println("row : " + row);
		
		if(row==1) {//세션객체 생성
			Member2DTO member = dao.memberSelect(userid);
			HttpSession session = request.getSession();
			session.setAttribute("user", member);
			session.setMaxInactiveInterval(1800);//30분
		}
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("userlogin_pro.jsp");
		rd.forward(request, response);
	}

}
