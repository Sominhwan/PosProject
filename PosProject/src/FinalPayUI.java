import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FinalPayUI {
	private JFrame frame;
	private String btnImage = ".\\image\\";
	private BackgroundImagePanel finalPayUIPanel=new BackgroundImagePanel(new ImageIcon(".\\image\\finalPayUI.jpg").getImage());//최종 결제 이미지	
	private String toEmail;
	private String setMessage;
		
	public FinalPayUI(){
		
	}
	
	public void initialize(String[] prizename, String[] prizeprice, int pricesum){
		JFrame finalPayUI=new JFrame("Pos");
		finalPayUI.setSize(1280,800);
		finalPayUI.setTitle("Pos");
		finalPayUI.setLocationRelativeTo(null);
		finalPayUI.setResizable(false);
		finalPayUI.setVisible(true);
		finalPayUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		finalPayUI.add(finalPayUIPanel);
	
		finalPayUIPanel.setLayout(null);
		finalPayUIPanel.setBounds(0, 0, 1280, 800);
				
		JTextField emailTextField = new JTextField();
		emailTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 21));
		emailTextField.setBounds(216, 381, 323, 34);
		finalPayUIPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		JButton payButton = new JButton();
		payButton.setIcon(new ImageIcon(btnImage+"pay.jpg"));
		payButton.setRolloverIcon(new ImageIcon(btnImage+"pay2.jpg"));
		payButton.setBounds(73, 650, 738, 75);
		finalPayUIPanel.add(payButton);

		JLabel prizeSum=new JLabel(pricesum+"원");
		prizeSum.setForeground(new Color(0, 0, 128));
		prizeSum.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
		prizeSum.setBounds(995, 614, 150, 100);
		finalPayUIPanel.add(prizeSum);
			
		JLabel prizeSum2=new JLabel(pricesum+"원");
		prizeSum2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prizeSum2.setBounds(910, 260, 150, 100);
		finalPayUIPanel.add(prizeSum2);
		
		JLabel prizeName=new JLabel(prizename[0]+" 외 "+prizename.length +" 개");
		prizeName.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prizeName.setBounds(900, 180, 150, 100);
		finalPayUIPanel.add(prizeName);
				
		payButton.addActionListener(new ActionListener() {//결제하기 버튼 누를시
			public void actionPerformed(ActionEvent e) {
				PaySystem pay=new PaySystem();
				toEmail=emailTextField.getText();
				setMessage="java 포스기에서 "+prizename[0]+" 외 "+prizename.length +" 개의 상품이 결제되었습니다.";
						
				System.out.println(toEmail);
				int i=0;
				   if(toEmail.length()!=i){
					   pay.getPayMentEmail(toEmail,setMessage);
					   //결재내역 화면으로 이동
					   finalPayUI.setVisible(false);
					   new CompletePayUI(pricesum);
					   }
				   else{
					 //결재내역 화면으로 이동
					   finalPayUI.setVisible(false);
					   new CompletePayUI(pricesum);
				   }           	
			}
		});			
	}	
}



	
