package testBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDAO {
	private String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "jsp", pwd="1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public TestDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<TestDTO> list() {//매개변수 값 받아서 ? 처리
		//String sql="select * from paging";
		//String sql="select * from paging order by num desc";
		String sql= "select  B.* from(select rownum rn, A.* from(select * from paging order by num desc)A)B where rn between ? and ?";
    
		ArrayList<TestDTO> listDto = new ArrayList<TestDTO>();
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, 3);
			rs = ps.executeQuery();
			while(rs.next()) {
				TestDTO dto = new TestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setCount(rs.getInt("count"));
				dto.setPdate(rs.getString("pdate"));
				listDto.add(dto);
				}
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listDto;
	}
	public void insert(TestDTO dto) {
		String sql ="insert into paging(num,title,pdate,count)"+
						"values(test_num.nextval,?,sysdate,0)";//넘버링
		try {
		con = DriverManager.getConnection(url,user,pwd);
		ps = con.prepareStatement(sql);
		ps.setString(1, dto.getTitle());
		ps.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	public void count(String num) {
		String sql ="update paging set count = count+1 where num="+num;
		try {
		con = DriverManager.getConnection(url,user,pwd);
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int getTotalPage() {
		String sql = "select count(*) from paging";
		int totPage = 0;
		double total=0;
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
				totPage= (int)Math.ceil(total/3);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return totPage;
	}
	public ArrayList<TestDTO> nextList(int start){
		String sql= "select  B.* from(select rownum rn, A.* from(select * from paging order by num desc)A)B where rn between ? and ?";
	    
		ArrayList<TestDTO> nextListDto = new ArrayList<TestDTO>();
		try {
			con = DriverManager.getConnection(url,user,pwd);
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, 3);
			rs = ps.executeQuery();
			while(rs.next()) {
				TestDTO dto = new TestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setCount(rs.getInt("count"));
				dto.setPdate(rs.getString("pdate"));
				nextListDto.add(dto);
				}
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return nextListDto;
	}	
}