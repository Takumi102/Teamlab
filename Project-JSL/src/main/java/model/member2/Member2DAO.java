package model.member2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class Member2DAO {
	private Member2DAO() {
	}

	private static Member2DAO instance = new Member2DAO();

	public static Member2DAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 회원목록 (번호 ID 이름 전화번호 등록일자 최근접속일)
	public List<Member2DTO> memberList() {
		List<Member2DTO> list = new ArrayList<>();
		String query = "select * from tbl_member2 order by first_time desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member2DTO dto = new Member2DTO();
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setFirsttime(rs.getString("first_time"));
				dto.setLasttime(rs.getString("last_time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 검색후 회원목록 (번호 ID 이름 전화번호 등록일자 최근접속일)
	public List<Member2DTO> memberList(String search, String key) {
		List<Member2DTO> list = new ArrayList<>();
		String query = "select * from tbl_member2 where " + search + " like ? order by first_time desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member2DTO dto = new Member2DTO();
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setFirsttime(rs.getString("first_time"));
				dto.setLasttime(rs.getString("last_time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 페이지인덱싱 회원목록 (번호 ID 이름 전화번호 등록일자 최근접속일)
	public List<Member2DTO> memberList(int pagestart, int endpage) {
		List<Member2DTO> list = new ArrayList<>();
		String query = "select X.* from(\r\n" 
				+ "   	 select rownum rnum, A.* from(\r\n"
				+ "        select * from tbl_member2 order by first_time desc) A\r\n"
				+ "            where rownum <=?) X where X.rnum>=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, pagestart);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member2DTO dto = new Member2DTO();
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setFirsttime(rs.getString("first_time"));
				dto.setLasttime(rs.getString("last_time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 페이지인덱싱 검색후 회원목록 (번호 ID 이름 전화번호 등록일자 최근접속일)
	public List<Member2DTO> memberList(String search, String key, int pagestart, int endpage) {
		List<Member2DTO> list = new ArrayList<>();
		String query = "select X.* from(\r\n" 
				+ "   	 select rownum rnum, A.* from(\r\n"
				+ "        select * from tbl_member2 order by first_time desc) A\r\n"
				+ "            where "+search+" like ? and rownum <=?) X where "+search+" like ? and X.rnum>=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%" + key + "%");
			pstmt.setInt(4, pagestart);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member2DTO dto = new Member2DTO();
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setFirsttime(rs.getString("first_time"));
				dto.setLasttime(rs.getString("last_time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 중복체크
	public int idcheck(String userid) {
		int row = 0;
		String query = "select count(*) from tbl_member2 where userid=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 회원정보 가져오기
	public Member2DTO memberSelect(String userid) {
		Member2DTO dto = new Member2DTO();
		String query = "select * from tbl_member2 where userid=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setUserid(rs.getString("userid"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}
	// 회원정보 수정

	public int memberModify(Member2DTO dto) {
		int row = 0;
		String query = "update tbl_member2 set name=?, userid=?,email=?,passwd=? ";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPasswd());

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 회원정보 등록
	public int memberInsert(Member2DTO dto) {
		int row = 0;
		String query = "insert into tbl_member2(name,userid,email,passwd)values(?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPasswd());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	//로그인 체크
	public int memberLogin(String userid, String passwd)  {

		String query = "select passwd from tbl_member2 where userid = ?";
		int row=0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userid);		
			rs = pstmt.executeQuery();
			if(rs.next()){
				String dbpass = rs.getString("passwd");
				if(dbpass.equals(passwd)){  //로그인에 성공하면 최근접속일자 지정
					query = "update tbl_member2 set last_time = sysdate where userid = ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1,userid);		
					pstmt.executeUpdate();
					row = 1;
				}else{ //비밀번호가 다른 경우
					row = 0;
				}
			}else{  //아이디가 없는 경우
				row = -1;
			}
		
		} catch(SQLException e)	{
			e.printStackTrace();
		} finally	{
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}
	//특정ID 검색
	public Member2DTO MemberSelect(String userid) {
		String query = "";
		Member2DTO dto = new Member2DTO();

		try {
			conn = DBManager.getConnection();
			query = "select * from tbl_member2 where userid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userid);		
			rs = pstmt.executeQuery();
			if(rs.next()){
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setPasswd(rs.getString("passwd"));	
				dto.setEmail(rs.getString("email"));	
				dto.setFirsttime(rs.getString("first_time"));	
				dto.setLasttime(rs.getString("last_time"));	
			}
		} catch(SQLException e)	{
			e.printStackTrace();
		} finally	{
			DBManager.close(conn, pstmt,rs);
		}
		return dto;
	}
}
