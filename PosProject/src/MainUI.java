import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainUI {
	private JFrame frame;
	private String btnImage = ".\\image\\";
	private BackgroundImagePanel mainPanel=new BackgroundImagePanel(new ImageIcon(".\\image\\mainUI.jpg").getImage());//���� �̹���	
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
				
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainUI(){
		initialize();
	}
	
	private void initialize(){
		frame =new JFrame();	
		frame.setSize(1280,800);
		frame.setTitle("Pos");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);//���� �̹��� �г� �߰�
		
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1280, 800);
		
		JButton prizeButton = new JButton();
		mainPanel.add(prizeButton);//��ǰ ��ư �߰�
		prizeButton.setBounds(245, 275, 308, 308);
		prizeButton.setIcon(new ImageIcon(btnImage+"prizeButton.jpg"));
		prizeButton.setRolloverIcon(new ImageIcon(btnImage+"prizeButton2.jpg"));
		prizeButton.setBorderPainted(false);

		prizeButton.addActionListener(new ActionListener() {//��ǰ ��ư
			public void actionPerformed(ActionEvent e) {
				PrizeUI p=new PrizeUI();
				frame.dispose();
			}
	      });
	    
		JButton memberButton = new JButton();
		mainPanel.add(memberButton);//ȸ����� ��ư �߰�
		memberButton.setBounds(710, 275, 308, 308);
		memberButton.setIcon(new ImageIcon(btnImage+"memberButton.jpg"));
		memberButton.setRolloverIcon(new ImageIcon(btnImage+"memberButton2.jpg"));
		memberButton.setBorderPainted(false);
		
		JButton adminModeButton = new JButton();
		mainPanel.add(adminModeButton);//ȸ����� ��ư �߰�
		adminModeButton.setBounds(990, 645, 215, 65);
		adminModeButton.setIcon(new ImageIcon(btnImage+"adminModeButton.png"));
		adminModeButton.setBorderPainted(false);
		adminModeButton.setContentAreaFilled(false);
	}
}
