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
	private BackgroundImagePanel payPanel=new BackgroundImagePanel(new ImageIcon(".\\image\\payUI.jpg").getImage());//���� �̹���	
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
		JLabel prizeSum=new JLabel(pricesum+"��");
		prizeSum.setFont(new Font("���� ���", Font.PLAIN, 40));
		prizeSum.setBounds(52, 150, 150, 100);
		payPanel.add(prizeSum);
		
		savePointIdSearch.setBounds(200, 505, 150, 30);  //����Ʈ �������� ȸ�� ���̵� �޴� �ؽ�Ʈâ
		usingPointIdSearch.setBounds(200, 635, 150, 30); 	//����Ʈ ����ϱ⿡�� ȸ�� ���̵� �޴� �ؽ�Ʈâ
		pointTextField.setBounds(200, 685, 150, 30);  //����� ����Ʈ  �޴� �ؽ�Ʈâ
		
		nextButton.setBounds(873,678,390,80);	// ������ư
		checkButton.setBounds(380,500,58,30);	// ����Ʈ���� Ȯ�� ��ư
		inquiryButton.setBounds(380,635,58,30);	// ȸ�����̵� ��ȸ ��ư
		usingButton.setBounds(380,685,58,30);	// ����Ʈ ��� ��ư
		cashPayButton.setBounds(80,325,165,80);	//���ݰ��� ��ư
		backButton.setBounds(20, 32, 54, 33);	//�ڷΰ��� ��ư	
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);	 
		
		backButton.addActionListener(new ActionListener(){	//�ڷΰ��� ��ư
	    	public void actionPerformed(ActionEvent e){	    		
	    		dispose();
	    		new PrizeUI();		
	    	} 
	    	});
				
		nextButton.addActionListener(new ActionListener(){ // ���� ��ư// ����Ʈ ���� db ����
	    	public void actionPerformed(ActionEvent e){		
	    		Statement stmt;
	 		   	ResultSet rs;
	 		   	String username = savePointIdSearch.getText();
	 			try {
	 				db_connect dbc = new db_connect();
	 				dbc.Connect();
	 				String sql = "UPDATE �� SET ����Ʈ = ����Ʈ +" +pricesum*0.01+" where �ƾƵ� = '"+username+"'" ;
	 				stmt = dbc.con.createStatement();
	 				rs = stmt.executeQuery(sql);	 								
	 			} catch (SQLException e1) {
	 					
	 			} 
	 			
	    		dispose();
	    
	        	FinalPayUI finalpay=new FinalPayUI();	
	        	finalpay.initialize(prizename,prizeprice,pricesum);
	    	} 
	    	});
		
		checkButton.addActionListener(new ActionListener(){	// ����Ʈ ���� ���̵� Ȯ��
 	    	public void actionPerformed(ActionEvent e) {
 	    		PaySystem pay=new PaySystem();
 	    		String username = savePointIdSearch.getText();	
 	    		int result=pay.idcheck(username);
 				if(result == 1) {
					JOptionPane.showMessageDialog(null, "���̵� Ȯ��");
				}else {
					JOptionPane.showMessageDialog(null, "���̵� Ȯ�� ����");
				}
 	    	}
 	    });
		
		inquiryButton.addActionListener(new ActionListener(){ 	//����Ʈ ��� ���̵� Ȯ�ι�ư
 	    	public void actionPerformed(ActionEvent e) {
 	    		PaySystem pay=new PaySystem();
 	    		String username = usingPointIdSearch.getText();	 				 				
 	    		int result=pay.idcheck(username);
 				if(result == 1) {
					JOptionPane.showMessageDialog(null, "���̵� Ȯ��");
				}else {
					JOptionPane.showMessageDialog(null, "���̵� Ȯ�� ����");
				}
 	    	}
 	    });
		
		usingButton.addActionListener(new ActionListener(){	// ����Ʈ ����ư
 	    	public void actionPerformed(ActionEvent e) {
 	    		PaySystem pay=new PaySystem();
 	    		int point =Integer.parseInt(pointTextField.getText());	 				 				
 	    		int result=pay.pointuse(point, usingPointIdSearch.getText());
 				if(result == 1) {
					JOptionPane.showMessageDialog(null, "����Ʈ ���");
					pricesum-=point;
				}else {
					JOptionPane.showMessageDialog(null, "����Ʈ ������");
				}
 	    	}
 	    });
		
		JLabel[] la=new JLabel[prizename.length];// ��ǰ��, ����, ���� ���
		JLabel[] la1=new JLabel[prizename.length];
		
		for (int i = 0;i<prizename.length;i++) {
			int price = Integer.parseInt(amount[i])*Integer.parseInt(prizeprice[i]);
			int line = 30;
			la[i]=new JLabel(prizename[i]+" X  "+ amount[i]);
			
			la1[i] = new JLabel("                                        "+price+"��");
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