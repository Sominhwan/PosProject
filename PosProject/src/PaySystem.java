import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaySystem {
	public PaySystem(){}
	
	public void getPayMentEmail(String toEmail,String setMessage){
		PayMentEmail pay=new PayMentEmail();
		pay.SMTP(toEmail,setMessage);
	}

	public int idcheck(String  username) { //���̵� Ȯ��
		   Statement stmt;
		   ResultSet rs;
			try {
				db_connect dbc = new db_connect();
				dbc.Connect();
				String id = null;
				String sql = "select * from �� where �ƾƵ� = '"+username+"'" ;
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
	
	public int pointuse(int point,String username) { //����Ʈ ���//����Ʈ 0���� ó���ؾ���.
		   Statement stmt;
		   ResultSet rs;
		   
			try {
				db_connect dbc = new db_connect();
				dbc.Connect();
				String sql = "UPDATE �� SET ����Ʈ = ����Ʈ -" +point+" where �ƾƵ� = '"+username+"' and ����Ʈ>="+point ;
				stmt = dbc.con.createStatement();
				return stmt.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();			
			}return -1;			
		}
}
