

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;

public class FriendDBAImpl implements FriendDBA{
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	//?îîÎπÑÏÖã?åÖ
	public FriendDBAImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//?ìú?ùº?ù¥Î≤?
			String url = "jdbc:oracle:thin:@localhost:1521:xe";//?ò§?ùº?Å¥Î≤ÑÏ†Ñ@?ïÑ?ù¥?îº:?è¨?ä∏Î≤àÌò∏:sid
			String user = "scott";//?ïÑ?ù¥?îî
			String pw = "TIGER";//ÎπÑÎ?Î≤àÌò∏

			conn = DriverManager.getConnection(url, user , pw);//Ïª§ÎÑ•?Öò

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ÏπúÍµ¨Ï∂îÍ?
	@Override
	public void friendInsert(Friend f) {
		String sql = "INSERT into friend values(FRIEND_SEQ.nextval, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getName());
			pstmt.setString(2, f.getBirth());
			pstmt.setString(3, f.getPhone());
			pstmt.setString(4, f.getAddr());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	//ÏπúÍµ¨?†ÑÏ≤¥Î≥¥Í∏?
	@Override
	public ArrayList<Friend> friendView() {
		String sql = "select * from friend";
		ArrayList<Friend> arr = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Friend f = new Friend();
				f.setNum(rs.getInt("num"));
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
				arr.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	//ÏπúÍµ¨Í≤??Éâ
	@Override
	public ArrayList<Friend> friendSearch(int type,String str) {
		String sql=null;
		Friend f = null;
		ArrayList<Friend> arr = null;
		switch(type) {
		case 0:
			return null;
		case 1:
			sql = "select * from friend where num like '%"+str+"%'";
			break;
		case 2:
			sql = "select * from friend where name like '%"+str+"%'";
			break;
		case 3:
			sql = "select * from friend where addr like '%"+str+"%'";
			break;
		}
		try {
			arr = new ArrayList<>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				f = new Friend(rs.getInt("num"),rs.getString("name"),rs.getString("birth"),rs.getString("phone"),rs.getString("addr"));
				arr.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	@Override
	public void friendUpdate(int num,JTextField tf1,JTextField tf2,JTextField tf3,JTextField tf4) {
		String sql = "Update friend set name = ?,birth = ?, phone = ?,addr = ? where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tf1.getText());
			pstmt.setString(2, tf2.getText());
			pstmt.setString(3, tf3.getText());
			pstmt.setString(4, tf4.getText());
			pstmt.setInt(5, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void friendDelete(int num) {
		String sql = "delete from friend where num ="+num;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Friend friendDetail(int num) {
		String sql = "select * from friend where num = "+num;
		Friend f=null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				f = new Friend(rs.getInt("num"),rs.getString("name"),rs.getString("birth"),rs.getString("phone"),rs.getString("addr"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return f;
	}

}

