import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CompletePayUI{
	private BackgroundImagePanel completePayUI=new BackgroundImagePanel(new ImageIcon(".\\image\\completePayUI.jpg").getImage());
	private ImageIcon checkbutton = new ImageIcon((".\\image\\completeCheckButton.jpg"));
    private JButton checkButton  = new JButton(checkbutton);    

    public PayUI price = new PayUI();
    public PayUI point = new PayUI();

	public CompletePayUI(int pricesum){
		JFrame frame=new JFrame("Pos");
		
		frame.setSize(1280,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(completePayUI);
		completePayUI.setLayout(null);
		completePayUI.setBounds(0, 0, 1280, 800);
       
		LocalDateTime now = LocalDateTime.now();
		String formatedNow=now.format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 hh시 mm분"));
		Label L=new Label("- 결제일시:"+"  "+formatedNow);
		L.setBounds(350,400,400,50);
		L.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		L.setBackground(Color.WHITE);
		completePayUI.add(L);
    	
		Label L2= new Label("- 최종결제금액:"+"  "+pricesum+"원");
		L2.setBounds(350,440,274,50);
		L2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		L2.setBackground(Color.WHITE);
		completePayUI.add(L2);
		
		Label L3= new Label("- 결제수단:"+"  "+"현금");
		L3.setBounds(350,480,274,50);
		L3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		L3.setBackground(Color.WHITE);
		completePayUI.add(L3);
		
		Label L4= new Label("- 구매처:"+"  "+"java pos");
		L4.setBounds(350,505,300,80);
		L4.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		L4.setBackground(Color.WHITE);
		completePayUI.add(L4);
		
		checkButton.setBounds(340,600,590,85);
		checkButton.setIcon(checkbutton);
		completePayUI.add(checkButton);
		
		checkButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				new MainUI();
			}
			});
	}
}