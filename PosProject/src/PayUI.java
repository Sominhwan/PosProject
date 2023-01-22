import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PayUI extends JFrame {
	public int pricesum;
	private BackgroundImagePanel payPanel=new BackgroundImagePanel(new ImageIcon(".\\image\\payUI.jpg").getImage());//결제 이미지	
	private ImageIcon nextbutton = new ImageIcon((".\\image\\nextButton.jpg"));
	private ImageIcon backbutton = new ImageIcon((".\\image\\backButton.png"));
	private ImageIcon cashpaybutton = new ImageIcon((".\\image\\cashPay.png"));
	private ImageIcon checkbutton = new ImageIcon((".\\image\\check.png"));
	private ImageIcon inquirybutton = new ImageIcon((".\\image\\inquiry.png"));
	private ImageIcon usingbutton = new ImageIcon((".\\image\\using.png"));
	
	private JButton nextButton  = new JButton(nextbutton);
	private JButton checkButton = new JButton(checkbutton);
	private JButton inquiryButton = new JButton(inquirybutton);
	private JButton usingButton = new JButton(usingbutton);
	private JButton cashPayButton = new JButton(cashpaybutton);
	private JButton backButton = new JButton(backbutton);
	private JTextField savePointIdSearch = new JTextField();
	private JTextField usingPointIdSearch = new JTextField();
	private JTextField pointTextField = new JTextField();	
		
	public PayUI() {
	
	}
	
	public void paymentFrame(String[] prizename,String[] prizeprice, String[] amount){	 
		setTitle("Pos");		
		setSize(1280,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(payPanel);
		payPanel.setLayout(null);
		payPanel.setBounds(0, 0, 1280, 800);
		
		for (int i =0;i<prizename.length;i++) {
			pricesum += Integer.parseInt(amount[i])*Integer.parseInt(prizeprice[i]);
		}
		JLabel prizeSum=new JLabel(pricesum+"원");
		prizeSum.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
		prizeSum.setBounds(52, 150, 150, 100);
		payPanel.add(prizeSum);
		
		savePointIdSearch.setBounds(200, 505, 150, 30);  //포인트 적립에서 회원 아이디 받는 텍스트창
		usingPointIdSearch.setBounds(200, 635, 150, 30); 	//포인트 사용하기에서 회원 아이디 받는 텍스트창
		pointTextField.setBounds(200, 685, 150, 30);  //사용할 포인트  받는 텍스트창
		
		nextButton.setBounds(873,678,390,80);	// 다음버튼
		checkButton.setBounds(380,500,58,30);	// 포인트적립 확인 버튼
		inquiryButton.setBounds(380,635,58,30);	// 회원아이디 조회 버튼
		usingButton.setBounds(380,685,58,30);	// 포인트 사용 버튼
		cashPayButton.setBounds(80,325,165,80);	//현금결제 버튼
		backButton.setBounds(20, 32, 54, 33);	//뒤로가기 버튼	
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);	 
		
		backButton.addActionListener(new ActionListener(){	//뒤로가기 버튼
	    	public void actionPerformed(ActionEvent e){	    		
	    		dispose();
	    		new PrizeUI();		
	    	} 
	    	});
				
		nextButton.addActionListener(new ActionListener(){ // 다음 버튼// 포인트 적립 db 적용
	    	public void actionPerformed(ActionEvent e){		
	    		Statement stmt;
	 		   	ResultSet rs;
	 		   	String username = savePointIdSearch.getText();
	 			try {
	 				db_connect dbc = new db_connect();
	 				dbc.Connect();
	 				String sql = "UPDATE 고객 SET 포인트 = 포인트 +" +pricesum*0.01+" where 아아디 = '"+username+"'" ;
	 				stmt = dbc.con.createStatement();
	 				rs = stmt.executeQuery(sql);	 								
	 			} catch (SQLException e1) {
	 					
	 			} 
	 			
	    		dispose();
	    
	        	FinalPayUI finalpay=new FinalPayUI();	
	        	finalpay.initialize(prizename,prizeprice,pricesum);
	    	} 
	    	});
		
		checkButton.addActionListener(new ActionListener(){	// 포인트 적립 아이디 확인
 	    	public void actionPerformed(ActionEvent e) {
 	    		PaySystem pay=new PaySystem();
 	    		String username = savePointIdSearch.getText();	
 	    		int result=pay.idcheck(username);
 				if(result == 1) {
					JOptionPane.showMessageDialog(null, "아이디 확인");
				}else {
					JOptionPane.showMessageDialog(null, "아이디 확인 실패");
				}
 	    	}
 	    });
		
		inquiryButton.addActionListener(new ActionListener(){ 	//포인트 사용 아이디 확인버튼
 	    	public void actionPerformed(ActionEvent e) {
 	    		PaySystem pay=new PaySystem();
 	    		String username = usingPointIdSearch.getText();	 				 				
 	    		int result=pay.idcheck(username);
 				if(result == 1) {
					JOptionPane.showMessageDialog(null, "아이디 확인");
				}else {
					JOptionPane.showMessageDialog(null, "아이디 확인 실패");
				}
 	    	}
 	    });
		
		usingButton.addActionListener(new ActionListener(){	// 포인트 사용버튼
 	    	public void actionPerformed(ActionEvent e) {
 	    		PaySystem pay=new PaySystem();
 	    		int point =Integer.parseInt(pointTextField.getText());	 				 				
 	    		int result=pay.pointuse(point, usingPointIdSearch.getText());
 				if(result == 1) {
					JOptionPane.showMessageDialog(null, "포인트 사용");
					pricesum-=point;
				}else {
					JOptionPane.showMessageDialog(null, "포인트 사용실패");
				}
 	    	}
 	    });
		
		JLabel[] la=new JLabel[prizename.length];// 상품명, 개수, 가격 띄움
		JLabel[] la1=new JLabel[prizename.length];
		
		for (int i = 0;i<prizename.length;i++) {
			int price = Integer.parseInt(amount[i])*Integer.parseInt(prizeprice[i]);
			int line = 30;
			la[i]=new JLabel(prizename[i]+" X  "+ amount[i]);
			
			la1[i] = new JLabel("                                        "+price+"원");
			la[i].setFont (la[i].getFont ().deriveFont (20.0f));
			la[i].setBounds(900,230+(line*i),390,80);
			la1[i].setFont (la[i].getFont ().deriveFont (20.0f));
			la1[i].setBounds(900,230+(line*i),390,80);
			payPanel.add(la[i]);
			payPanel.add(la1[i]);
		}
	
		payPanel.add(savePointIdSearch);
		payPanel.add(usingPointIdSearch);
		payPanel.add(pointTextField);
		
		payPanel.add(nextButton);
		payPanel.add(checkButton);
		payPanel.add(inquiryButton);
		payPanel.add(usingButton);
		payPanel.add(cashPayButton);
		payPanel.add(backButton);		
	}	
}