import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaySystem {
	public PaySystem(){}
	
	public void getPayMentEmail(String toEmail,String setMessage){
		PayMentEmail pay=new PayMentEmail();
		pay.SMTP(toEmail,setMessage);
	}

	public int idcheck(String  username) { //아이디 확인
		   Statement stmt;
		   ResultSet rs;
			try {
				db_connect dbc = new db_connect();
				dbc.Connect();
				String id = null;
				String sql = "select * from 고객 where 아아디 = '"+username+"'" ;
				stmt = dbc.con.createStatement();
				rs = stmt.executeQuery(sql);				
				if(rs.next()) {
					return 1;
				}else {}				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return -1;
	}
	
	public int pointuse(int point,String username) { //포인트 사용//포인트 0이하 처리해야함.
		   Statement stmt;
		   ResultSet rs;
		   
			try {
				db_connect dbc = new db_connect();
				dbc.Connect();
				String sql = "UPDATE 고객 SET 포인트 = 포인트 -" +point+" where 아아디 = '"+username+"' and 포인트>="+point ;
				stmt = dbc.con.createStatement();
				return stmt.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();			
			}return -1;			
		}
}
