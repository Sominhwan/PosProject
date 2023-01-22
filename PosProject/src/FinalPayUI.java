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
	private BackgroundImagePanel finalPayUIPanel=new BackgroundImagePanel(new ImageIcon(".\\image\\finalPayUI.jpg").getImage());//���� ���� �̹���	
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
		emailTextField.setFont(new Font("���� ���", Font.PLAIN, 21));
		emailTextField.setBounds(216, 381, 323, 34);
		finalPayUIPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		JButton payButton = new JButton();
		payButton.setIcon(new ImageIcon(btnImage+"pay.jpg"));
		payButton.setRolloverIcon(new ImageIcon(btnImage+"pay2.jpg"));
		payButton.setBounds(73, 650, 738, 75);
		finalPayUIPanel.add(payButton);

		JLabel prizeSum=new JLabel(pricesum+"��");
		prizeSum.setForeground(new Color(0, 0, 128));
		prizeSum.setFont(new Font("���� ���", Font.PLAIN, 40));
		prizeSum.setBounds(995, 614, 150, 100);
		finalPayUIPanel.add(prizeSum);
			
		JLabel prizeSum2=new JLabel(pricesum+"��");
		prizeSum2.setFont(new Font("���� ���", Font.PLAIN, 20));
		prizeSum2.setBounds(910, 260, 150, 100);
		finalPayUIPanel.add(prizeSum2);
		
		JLabel prizeName=new JLabel(prizename[0]+" �� "+prizename.length +" ��");
		prizeName.setFont(new Font("���� ���", Font.PLAIN, 20));
		prizeName.setBounds(900, 180, 150, 100);
		finalPayUIPanel.add(prizeName);
				
		payButton.addActionListener(new ActionListener() {//�����ϱ� ��ư ������
			public void actionPerformed(ActionEvent e) {
				PaySystem pay=new PaySystem();
				toEmail=emailTextField.getText();
				setMessage="java �����⿡�� "+prizename[0]+" �� "+prizename.length +" ���� ��ǰ�� �����Ǿ����ϴ�.";
						
				System.out.println(toEmail);
				int i=0;
				   if(toEmail.length()!=i){
					   pay.getPayMentEmail(toEmail,setMessage);
					   //���系�� ȭ������ �̵�
					   finalPayUI.setVisible(false);
					   new CompletePayUI(pricesum);
					   }
				   else{
					 //���系�� ȭ������ �̵�
					   finalPayUI.setVisible(false);
					   new CompletePayUI(pricesum);
				   }           	
			}
		});			
	}	
}



	
