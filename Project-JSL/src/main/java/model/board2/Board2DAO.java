package model.board2;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class Board2DAO {
	// 싱글톤
	private Board2DAO() {
		
	}

	private static Board2DAO instance = new Board2DAO();// 자기자신의 객체를 생성

	public static Board2DAO getInstance() {
		return instance;
	}

	// DB 관련
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 메소드 정의

	// 게시판 글 총수 계산
	public int boardCount(int boardid) {
		// 리턴타입
		int row = 0;
		// 쿼리
		String query = "select count(*) from tbl_board2 where boardid=? ";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardid);
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
	// 검색후 게시판 글 총수 계산
		public int boardCount(String search, String key, int boardid) {
			// 리턴타입
			int row = 0;
			// 쿼리
			String query = "select count(*) from tbl_board2 where "+search+" like ? and boardid=?";
//			String query = "select count(*) from tbl_board where "+search+" like ? '%" + key +"%'";
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+key+"%");
				pstmt.setInt(2, boardid);
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

	// 게시판 전체 글목록 가져오기
	public List<Board2DTO> boardList(int boardid) {
		// 리턴타입
		List<Board2DTO> list = new ArrayList<>();
		// 쿼리
		String query = "select * from tbl_board2 where boardid=? order by idx desc";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardid);
			rs = pstmt.executeQuery();
			Board2DTO dto = null;
			while (rs.next()) {
				dto = new Board2DTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserid(rs.getString("userid"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	// 검색기능 추가 게시판 전체 글목록 가져오기
			public List<Board2DTO> boardList(String search, String key, int boardid) {
				// 리턴타입
				List<Board2DTO> list = new ArrayList<>();
				// 쿼리
				String query = "select * from tbl_board2 where "+search+" like ? and boardid=? order by idx desc";			

				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, "%" + key + "%");
					pstmt.setInt(2, boardid);
					rs = pstmt.executeQuery();
					Board2DTO dto = null;
					while (rs.next()) {
						dto = new Board2DTO();
						dto.setIdx(rs.getInt("idx"));
						dto.setSubject(rs.getString("subject"));
						dto.setUserid(rs.getString("userid"));
						dto.setRegdate(rs.getString("regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
						list.add(dto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt, rs);
				}
				return list;
			}
	// 게시판 글목록 가져오기(페이지 인덱싱)
		public List<Board2DTO> boardList(int pagestart,int endpage, int boardid) {
			// 리턴타입
			List<Board2DTO> list = new ArrayList<>();
			// 쿼리
			String query = "select X.* from(\r\n"
					+ "    select rownum rnum, A.* from(\r\n"
					+ "        select * from tbl_board2 where boardid=? order by idx desc) A\r\n"
					+ "            where rownum <=?) X where X.rnum>=?";

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardid);
				pstmt.setInt(2, endpage);
				pstmt.setInt(3, pagestart);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board2DTO dto = new Board2DTO();
					dto.setIdx(rs.getInt("idx"));
					dto.setSubject(rs.getString("subject"));
					dto.setUserid(rs.getString("userid"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setReadcnt(rs.getInt("readcnt"));
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
		//게시판 글목록 가져오기(페이지 인덱싱+ 검색기능 추가)
		public List<Board2DTO> boardList(String search,String key,int pagestart,int endpage,int boardid) {
			// 리턴타입
			List<Board2DTO> list = new ArrayList<>();
			// 쿼리
			String query = "select X.* from(\r\n"
					+ "    select rownum rnum, A.* from(\r\n"
					+ "        select * from tbl_board2 where boardid=? order by idx desc) A\r\n"
					+ "            where "+search+" like ? and rownum <=?) X where "+search+" like ? and X.rnum>=?";

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardid);
				pstmt.setString(2, "%"+key+"%");
				pstmt.setInt(3, endpage);
				pstmt.setString(4, "%"+key+"%");
				pstmt.setInt(5, pagestart);
				
				rs = pstmt.executeQuery();
				Board2DTO dto = null;
				while (rs.next()) {
					dto = new Board2DTO();
					dto.setIdx(rs.getInt("idx"));
					dto.setSubject(rs.getString("subject"));
					dto.setUserid(rs.getString("userid"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setReadcnt(rs.getInt("readcnt"));
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
	

	// 게시판 글쓰기
	public int boardWrite(Board2DTO dto) {
		// 리턴타입
		int row = 0;
		// 쿼리
		String query = "INSERT INTO tbl_board2(idx,boardid,subject,contents)VALUES(tbl_board2_seq_idx.nextval,?,?,?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getBoardid());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	// 글내용 가져오기
	public Board2DTO boardView(int idx) {
		// 리턴타입
		Board2DTO dto = new Board2DTO();
		// 쿼리
		String query = "select * from tbl_board2 where idx=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setUserid(rs.getString("userid"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setContents(rs.getString("contents"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}

	// 조회수 증가
	public void boardReadcnt(int idx) {
		// 쿼리
		String query = "update tbl_board2 set readcnt=readcnt+1 where idx=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	// 글 수정
	public int boardUpdate(Board2DTO dto) {
		// 쿼리
		String query = "update tbl_board2 set subject=?, contents=? , boardid=? where idx=?";
		int row=0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContents());
			pstmt.setInt(3, dto.getBoardid());
			pstmt.setInt(4, dto.getIdx());
			row=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}return row;
	}
	//글삭제
	public int boardDelete(int idx) {
		// 쿼리
		String query = "delete from tbl_board2 where idx=?";
		int row=0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			row=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}return row;
	}
	
	
	
	
	
	
	
	
	// 게시판 글 총수 계산
		public int boardCount() {
			// 리턴타입
			int row = 0;
			// 쿼리
			String query = "select count(*) from tbl_board2";

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
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
		// 검색후 게시판 글 총수 계산
			public int boardCount(String search, String key) {
				// 리턴타입
				int row = 0;
				// 쿼리
				String query = "select count(*) from tbl_board2 where "+search+" like ? ";
//				String query = "select count(*) from tbl_board where "+search+" like ? '%" + key +"%'";
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, "%"+key+"%");
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

		// 게시판 전체 글목록 가져오기
		public List<Board2DTO> boardList() {
			// 리턴타입
			List<Board2DTO> list = new ArrayList<>();
			// 쿼리
			String query = "select * from tbl_board2 order by idx desc";

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				Board2DTO dto = null;
				while (rs.next()) {
					dto = new Board2DTO();
					dto.setIdx(rs.getInt("idx"));
					dto.setSubject(rs.getString("subject"));
					dto.setUserid(rs.getString("userid"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setReadcnt(rs.getInt("readcnt"));
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
		// 검색기능 추가 게시판 전체 글목록 가져오기
				public List<Board2DTO> boardList(String search, String key) {
					// 리턴타입
					List<Board2DTO> list = new ArrayList<>();
					// 쿼리
					String query = "select * from tbl_board2 where "+search+" like ? order by idx desc";			

					try {
						conn = DBManager.getConnection();
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, "%" + key + "%");
						rs = pstmt.executeQuery();
						Board2DTO dto = null;
						while (rs.next()) {
							dto = new Board2DTO();
							dto.setIdx(rs.getInt("idx"));
							dto.setSubject(rs.getString("subject"));
							dto.setUserid(rs.getString("userid"));
							dto.setRegdate(rs.getString("regdate"));
							dto.setReadcnt(rs.getInt("readcnt"));
							list.add(dto);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBManager.close(conn, pstmt, rs);
					}
					return list;
				}
		// 게시판 글목록 가져오기(페이지 인덱싱)
			public List<Board2DTO> boardList(int pagestart,int endpage) {
				// 리턴타입
				List<Board2DTO> list = new ArrayList<>();
				// 쿼리
				String query = "select X.* from(\r\n"
						+ "    select rownum rnum, A.* from(\r\n"
						+ "        select * from tbl_board2  order by idx desc) A\r\n"
						+ "            where rownum <=?) X where X.rnum>=?";

				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, endpage);
					pstmt.setInt(2, pagestart);
					
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Board2DTO dto = new Board2DTO();
						dto.setIdx(rs.getInt("idx"));
						dto.setSubject(rs.getString("subject"));
						dto.setUserid(rs.getString("userid"));
						dto.setRegdate(rs.getString("regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
						list.add(dto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt, rs);
				}
				return list;
			}
			//게시판 글목록 가져오기(페이지 인덱싱+ 검색기능 추가)
			public List<Board2DTO> boardList(String search,String key,int pagestart,int endpage) {
				// 리턴타입
				List<Board2DTO> list = new ArrayList<>();
				// 쿼리
				String query = "select X.* from(\r\n"
						+ "    select rownum rnum, A.* from(\r\n"
						+ "        select * from tbl_board2 order by idx desc) A\r\n"
						+ "            where "+search+" like ? and rownum <=?) X where "+search+" like ? and X.rnum>=?";

				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, "%"+key+"%");
					pstmt.setInt(2, endpage);
					pstmt.setString(3, "%"+key+"%");
					pstmt.setInt(4, pagestart);
					
					rs = pstmt.executeQuery();
					Board2DTO dto = null;
					while (rs.next()) {
						dto = new Board2DTO();
						dto.setIdx(rs.getInt("idx"));
						dto.setSubject(rs.getString("subject"));
						dto.setUserid(rs.getString("userid"));
						dto.setRegdate(rs.getString("regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
						list.add(dto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt, rs);
				}
				return list;
			}
		


}

		

		



