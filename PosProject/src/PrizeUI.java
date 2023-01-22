import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

class db_connect{
	 Connection con = null;
	 Statement stmt=null;
	 ResultSet rs=null;
	 
	   String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   String id = "pos"; String password = "1234";
	   public db_connect() {
		     try {
		    	Class.forName("oracle.jdbc.driver.OracleDriver");
		        System.out.println("����̹� ���� ����");
		     } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
		   }
	   public void Connect() {
		     try {
		         con = DriverManager.getConnection(url, id, password);
		         System.out.println("DB ���� ����");
		     } catch (SQLException e) {         
		    	 System.out.println("Connection Fail");      
		     }
		     }
	   public ResultSet select(String query) throws SQLException
	   {
		     Connect();
		     stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		             ResultSet.CONCUR_UPDATABLE);
			 rs = stmt.executeQuery(query);
			 return rs;
	   }
	   public void close() throws SQLException
	   {
		   stmt.close();
		   rs.close();
		   con.close();
	   }
}

class PrizeUI{
	JFrame MainUI;	
	JPanel buy;
	JPanel prize_list;
	public String btnImage = ".\\image\\";
	
	public PrizeUI(JFrame prizeUI){
		int r=0;
  		int c=0;
  		JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(10);	
  		JViewport view=o.getViewport();
  		buy=((JPanel)view.getComponent(0));
  		JScrollPane o2=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
  		JViewport view2=o2.getViewport(); 
  		prize_list =((JPanel)view2.getComponent(0));
  		String inquiry=((JTextField)prizeUI.getContentPane().getComponent(5)).getText();

	    prizeSystem obj=new prizeSystem();
	    if(inquiry.equals("������ǰ")||inquiry.equals("���ڷ�")||inquiry.equals("�ַ�")||inquiry.equals("��ȭ")||inquiry.equals("���")){
	    	try {
	    		obj=obj.getPrize("select * from ��ǰ where ī�װ�='"+inquiry+"'");
	    		} catch (SQLException e1) {
	    			e1.printStackTrace();
	    		}
	    }
	    else{
	    	try{
	    		obj=obj.getPrize("select * from ��ǰ where ��ǰ��='"+inquiry+"'");
	    		} catch (SQLException e1) {
	    			e1.printStackTrace();
	    		}
	    }
	       
	    JButton []btn=new JButton[obj.getPrizeList().length];
	    for(int i=0; i<obj.getPrizeList().length; i++){
	    	btn[i]=new JButton();
	        btn[i].addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
		    buy.setLayout(new GridLayout(0,1));
		    if(buy.getComponentCount()<=4){
		    	buy.setLayout(null);
		    	}
		    
		    int v = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
	  	    int h = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
	  	
	  	    JPanel list=new JPanel();   
	  	    JButton btn=new JButton("+");
	  	    JButton btn2=new JButton("-");
	  	    JButton btn3=new JButton("X");
	  	    JButton btn4= (JButton)e.getSource();
	  	            
	  	    String prize_name=((JLabel)btn4.getComponent(0)).getText();
	  	    String price=((JLabel)btn4.getComponent(1)).getText();
	  	    int temp=Integer.valueOf(price);
	  	    int result= Integer.valueOf(((JLabel)(prizeUI.getContentPane().getComponent(1))).getText());
	  	    result=result+temp;
	  	    ((JLabel)(prizeUI.getContentPane().getComponent(1))).setText(Integer.toString(result));
	  	            
	  	    JLabel Prize_name=new JLabel(prize_name);
	  	    JLabel Price=new JLabel(price);
	  	    Prize_name.setBounds(140,15,50,50);
	  	    Price.setBounds(140,30,50,50);

	  	    list.setLayout(null);
	  	    list.add(Prize_name);
	  	    list.add(Price);

	  	    btn.setBounds(50,25,45,45);
	  	    btn2.setBounds(230,25,45,45);
	  	    btn3.setBounds(305,8,45,45);
	  	    btn3.setBorderPainted(false);
	  	    btn3.setContentAreaFilled(false);
	  	    btn3.setDefaultCapable(false);
	  	    btn3.setFocusPainted(false);
	  	    btn.setFont(new Font("Arial", Font.PLAIN, 15));
	  	    btn2.setFont(new Font("Arial", Font.PLAIN, 15));
	  	    btn3.setFont(new Font("Arial", Font.PLAIN, 15));
	  	    btn.setBackground(Color.WHITE);
	  	    btn2.setBackground(Color.WHITE);
	  	    btn3.setBackground(Color.WHITE);
		 
	  	 	btn.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			int result= Integer.valueOf(((JLabel)(prizeUI.getContentPane().getComponent(1))).getText());
        			Container obj=btn.getParent();
        			JPanel list=(JPanel)obj;
        			JLabel amount=(JLabel)list.getComponent(1);
     
        			result=result+Integer.valueOf(amount.getText()); 
        			((JLabel)prizeUI.getContentPane().getComponent(1)).setText(Integer.toString(result));
        			((JLabel)list.getComponent(2)).setText(Integer.toString(Integer.valueOf(((JLabel)list.getComponent(2)).getText())+1));	  

        			prizeUI.repaint();
        	 } 
        	});
    
        	btn2.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e){
        			int result= Integer.valueOf(((JLabel)(prizeUI.getContentPane().getComponent(1))).getText());
        			
        			if(Integer.valueOf(((JLabel)list.getComponent(2)).getText())>1){
        				Container obj=btn.getParent();
        				JPanel list=(JPanel)obj;
        				JLabel amount=(JLabel)list.getComponent(1);
        				result=result-Integer.valueOf(amount.getText()); 
        				((JLabel)prizeUI.getContentPane().getComponent(1)).setText(Integer.toString(result));
        				((JLabel)list.getComponent(2)).setText(Integer.toString(Integer.valueOf(((JLabel)list.getComponent(2)).getText())-1));	  
        			}
        	 } 
        	});
        	 
	  	    btn3.addActionListener(new ActionListener() {
	  	    	public void actionPerformed(ActionEvent e){
	  	          Container obj=btn3.getParent();
     			 
     			 int result=Integer.valueOf((((JLabel)(prizeUI.getContentPane().getComponent(1))).getText()));
     			 JPanel list=(JPanel)obj;
     			 JLabel price=(JLabel)list.getComponent(1);
     			 result=result-Integer.valueOf(price.getText())*Integer.valueOf((((JLabel)list.getComponent(2))).getText());
     			 ((JLabel)prizeUI.getContentPane().getComponent(1)).setText(Integer.toString(result));
     			 
     			 System.out.println("ȣ��");
     			 buy.setLayout(new GridLayout(buy.getComponentCount()+5,1));
     			 buy.remove(obj);
     			 prizeUI.setVisible(false);
     			 prizeUI.setVisible(true);
	  	            } 
	  	    	});
		 
	  	    JLabel amount=new JLabel("1");
	  	    amount.setBounds(185,-2,100,100);
	  	    list.add(amount);
     	 
	  	    list.add(btn3);
	  	    list.add(btn);
	  	    list.add(btn2);
	  	    list.setPreferredSize(new Dimension(50,100));
	  	    list.setBounds(4,buy.getComponentCount()*100+3,355,100);
	  	    list.setBorder(new LineBorder(Color.black));
	  	    buy.add(list);
	  	    prizeUI.repaint();
	  	    }
	        });
	             
	    prize_list.setLayout(null);
	    if(obj.getPrizeList().length>25){
	    	prize_list.setLayout(new GridLayout(0,5,10,10));		  			 
	    	}
		
	    btn[i].setPreferredSize(new Dimension(102,102));
		btn[i].setBounds(c,r,173,110);
		c=c+181; 
		if(btn[i].getLocation().x==724){
			c=0;
		    r=r+120;
		    }
		
		btn[i].setBackground(Color.WHITE);

		JLabel prize_name=new JLabel();
		JLabel price=new JLabel();  		 
		prize_name.setBounds(70,20,50,20);
		price.setBounds(70,60,50,20);
	    prize_name.setText(obj.getPrize(i).getPrize_name());
		price.setText(obj.getPrize(i).getPrice());
		btn[i].setLayout(null);
		btn[i].add(prize_name);
		btn[i].add(price);
		}
	    
	    for(int i=0; i<obj.getPrizeList().length; i++){
	    	prize_list.add(btn[i]);
	    	}
	    prizeUI.repaint();
	    
	}


	public PrizeUI() {
		JFrame prizeUI=new JFrame("Pos");
		JPanel category=new JPanel();
	 	
		category.setBounds(0,93,900,113);
		category.setLayout(null);
		category.setBorder( new MatteBorder(0, 3, 0, 1,Color.GRAY));	
		category.setBackground(Color.WHITE);

		JLabel label=new JLabel("        ��ǰ");
		label.setFont(new Font("���� ���", Font.PLAIN, 30));
		label.setBounds(0,1,1280,90);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setBorder(new MatteBorder(15, 5, 15, 5, Color.BLACK));	
	    
		JTextField inquiry=new JTextField();
		inquiry.setBounds(300,108,350,38);

	    JLabel prizeName=new JLabel(new ImageIcon(btnImage+"prizeName.png"));
	    prizeName.setBounds(219,109,80,37);
	    prizeUI.add(prizeName);
	 
	    JButton prizePayButton=new JButton(new ImageIcon(btnImage+"prizePayButton.jpg"));
	    JLabel prizePay=new JLabel("      �����ϱ� : ");
	    prizePay.setBounds(950,725,1000,200);
		prizePay.setFont(new Font("���� ���", Font.BOLD, 25));
	    prizePay.setForeground(Color.WHITE);
	    prizePayButton.setBounds(900,625,363,400);
	    prizePayButton.setBorderPainted(false);
	    prizePayButton.setContentAreaFilled(false);
	    prizePayButton.setFocusPainted(false);
	    
	    prizePayButton.addActionListener(new ActionListener() {//�����ϱ� ��ư
	         public void actionPerformed(ActionEvent e) {
	        	String prizename[] = new String[buy.getComponentCount()]; 
	        	String prizeprice[]=new String[buy.getComponentCount()];
	        	String amount[]=new String[buy.getComponentCount()];
	        	for(int i = 0; i<buy.getComponentCount();i++) {
	        		prizename[i]=((JLabel)((JPanel)buy.getComponent(i)).getComponent(0)).getText();
	        		prizeprice[i]=((JLabel)((JPanel)buy.getComponent(i)).getComponent(1)).getText();
	        		amount[i]=((JLabel)((JPanel)buy.getComponent(i)).getComponent(2)).getText();
	        	}
	        	PayUI pui = new PayUI();
	            pui.paymentFrame(prizename,prizeprice,amount);
	            prizeUI.setVisible(false);      
	             
	         }
	         });
	    
	    JLabel p1=new JLabel("0");
        p1.setBounds(1130,726,800,200);
        p1.setFont(new Font("���� ���", Font.BOLD, 25));
	    p1.setForeground(Color.WHITE);
	    prizeUI.add(p1);
	    JLabel p2=new JLabel(")");
	    p2.setBounds(1070,725,800,200);
        p2.setFont(new Font("���� ���", Font.BOLD, 25));
	    p2.setForeground(Color.WHITE);
	    
	    prizeUI.add(prizePay);
	    prizeUI.add(prizePayButton);   
	    JPanel search=new JPanel();
	    search.setBounds(648,108,60,38);
	    search.setLayout(null);
	    JButton prizeSearch=new JButton(new ImageIcon(btnImage+"prizeSearch.png"));
	    prizeSearch.setBounds(0,0,60,37);
	    
	    search.setBackground(Color.WHITE);
	    search.setBorder(new MatteBorder(1, 0, 1, 1,Color.GRAY));
	    prizeSearch.setBorderPainted(false);
	    inquiry.requestFocus(false);
	    inquiry.setBorder(new MatteBorder(1, 1, 1, 0,Color.GRAY));
	    inquiry.setFont(new Font("���� ���", Font.PLAIN, 25));
	    prizeSearch.requestFocus(true);
	    search.add(prizeSearch);
	    prizeUI.add(search);
	    prizeSearch.setBackground(Color.WHITE);
	    prizeSearch.setContentAreaFilled(false);
	    prizeSearch.setFocusPainted(false);	  
	    prizeUI.add(inquiry);
	  
	    prizeSearch.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e)
	    	 {
	    	  JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
	    	  JViewport view=o.getViewport();

	    	  ((JPanel)view.getComponent(0)).removeAll();
	    	
	    	  prizeUI.repaint();
	          new PrizeUI(prizeUI);
	         } 
	         });
	        
	    JButton backButton=new JButton(new ImageIcon(btnImage+"backButton.png"));
	    backButton.setBorderPainted(false);
	    backButton.setContentAreaFilled(false);
	    backButton.setFocusPainted(false);	  
	    backButton.setBounds(20, 32, 54, 33);
	    prizeUI.add(backButton);
	    
	    backButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		
	    		prizeUI.setVisible(false);
	    		new MainUI();		
	    	} 
	    	});
		
		JPanel all_delete=new JPanel();
		all_delete.setLayout(null);
		all_delete.setBounds(900,93,380,112);
		all_delete.setBackground(Color.WHITE);
		JButton deletButton=new JButton(new ImageIcon(btnImage+"deleteButton.png"));
		deletButton.setBorderPainted(false);
		deletButton.setContentAreaFilled(false);
		deletButton.setFocusPainted(false);	  
		deletButton.setBounds(300,-30,115,100);
        all_delete.add(deletButton);
		
        deletButton.addActionListener(new ActionListener() {
          	 public void actionPerformed(ActionEvent e){
          		 buy.removeAll();
          		((JLabel)prizeUI.getContentPane().getComponent(1)).setText("0");
          		 prizeUI.repaint();
            } 
            });
        
        JLabel payList=new JLabel("���� ���");
        payList.setForeground(new Color(0, 0, 128));
        payList.setFont(new Font("���� ���", Font.BOLD, 18));
        payList.setBounds(20,-8,150,60);
        all_delete.add(payList);
		
		JButton process_food=new JButton(new ImageIcon(btnImage+"category1.png"));
		process_food.setBounds(20,70,160,42);
		process_food.setBorderPainted(false);
		process_food.setContentAreaFilled(false);
		process_food.setFocusPainted(false);	  
		category.add(process_food);
		JButton confectionery=new JButton(new ImageIcon(btnImage+"category2.png"));
		confectionery.setBorderPainted(false);
		confectionery.setContentAreaFilled(false);
		confectionery.setFocusPainted(false);	  
		confectionery.setBounds(220,70,115,42);
        category.add(confectionery);
        JButton liquor=new JButton(new ImageIcon(btnImage+"category3.png"));
        liquor.setBorderPainted(false);
        liquor.setContentAreaFilled(false);
        liquor.setFocusPainted(false);	  
        liquor.setBounds(350,70,115,42);
        category.add(liquor);

        JButton chandlery=new JButton(new ImageIcon(btnImage+"category4.png"));
        chandlery.setBorderPainted(false);
        chandlery.setContentAreaFilled(false);
        chandlery.setFocusPainted(false);	  
        chandlery.setBounds(460,70,115,42);
        category.add(chandlery);

        JButton tobacco=new JButton(new ImageIcon(btnImage+"category5.png"));
        tobacco.setBorderPainted(false);
        tobacco.setContentAreaFilled(false);
        tobacco.setFocusPainted(false);	  
        tobacco.setBounds(570,70,115,42);
        category.add(tobacco);
        
        process_food.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e){
       		 inquiry.setText("������ǰ");

             JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
	    	  JViewport view=o.getViewport();

     	  ((JPanel)view.getComponent(0)).removeAll();
          new PrizeUI(prizeUI);
    		 inquiry.setText("");
          
         } 
         });
                    
        confectionery.addActionListener(new ActionListener(){
       	 public void actionPerformed(ActionEvent e){
       		 inquiry.setText("���ڷ�");
   
             JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
	    	 JViewport view=o.getViewport();

	    	 ((JPanel)view.getComponent(0)).removeAll();
	    	 new PrizeUI(prizeUI);
	    	 inquiry.setText("");      
         } 
         });
        
        liquor.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e){
       		 inquiry.setText("�ַ�");
             
             JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
	    	 JViewport view=o.getViewport();

	    	 ((JPanel)view.getComponent(0)).removeAll();
	    	 new PrizeUI(prizeUI);
	    	 inquiry.setText("");
         } 
         });
          
        chandlery.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e){
       		 inquiry.setText("��ȭ");
    
             JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
	    	 JViewport view=o.getViewport();

	    	 ((JPanel)view.getComponent(0)).removeAll();
	    	 new PrizeUI(prizeUI);
	    	 inquiry.setText("");

         } 
         });
        
        tobacco.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e){
       		 inquiry.setText("���");
       		 
             JScrollPane o=(JScrollPane)prizeUI.getContentPane().getComponent(11);	
	    	 JViewport view=o.getViewport();

	    	 ((JPanel)view.getComponent(0)).removeAll();
	    	 new PrizeUI(prizeUI);
	    	 inquiry.setText(""); 
       	 } 
         });
        	
        prizeUI.add(category);
        prizeUI.add(label);
        prizeUI.add(all_delete);
		
	  	buy=new JPanel();
        buy.setLayout(new GridLayout(0,1));
	    buy.setBorder(new LineBorder(Color.GRAY,1));
	    JPanel prize_list=new JPanel();
        prize_list.setLayout(null);
	  	int v = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
	  	int h = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;  
      	JScrollPane jsp=new JScrollPane(prize_list);

	  	int r=0;
  		int c=0;

        JScrollPane scroll2=new JScrollPane();
        scroll2.setViewportView(buy);
        scroll2.setBounds(900, 207, 380, 580);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        prizeUI.add(scroll2);
         
        prizeSystem obj=new prizeSystem();
        
        try {
			obj=obj.getPrize("select * from ��ǰ where ī�װ�='������ǰ'");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
         
        JButton []btn=new JButton[obj.getPrizeList().length];

  	  	for(int i=0; i<obj.getPrizeList().length; i++){
  	  		btn[i]=new JButton();
            btn[i].addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 buy.setLayout(new GridLayout(0,1));
            	 if(buy.getComponentCount()<=4){
            		 buy.setLayout(null);
            	 }
            	 
            	 int v = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
            	 int h = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
  		 
            	 JPanel list=new JPanel();   
            	 JButton btn=new JButton("+");
            	 JButton btn2=new JButton("-");
            	 JButton btn3=new JButton("X");
            	 JButton btn4= (JButton)e.getSource();
	  
            	 String prize_name=((JLabel)btn4.getComponent(0)).getText();
            	 String price=((JLabel)btn4.getComponent(1)).getText();
            	 int temp=Integer.valueOf(price);
            	 int result= Integer.valueOf(((JLabel)(prizeUI.getContentPane().getComponent(1))).getText());
            	 result=result+temp;
            	 ((JLabel)(prizeUI.getContentPane().getComponent(1))).setText(Integer.toString(result));
      
            	 JLabel Prize_name=new JLabel(prize_name);
            	 JLabel Price =new JLabel(price);
            	 Prize_name.setBounds(140,15,50,50);
            	 Price.setBounds(140,30,50,50);	
	 
            	 list.setLayout(null);
            	 list.add(Prize_name);
            	 list.add(Price);
     
            	 JLabel amount=new JLabel("1"); 
            	 amount.setBounds(185,-2,100,100);
            	 list.add(amount);

            	 btn.addActionListener(new ActionListener() {
            		 public void actionPerformed(ActionEvent e) {
            			 int result= Integer.valueOf(((JLabel)(prizeUI.getContentPane().getComponent(1))).getText());
            			 Container obj=btn.getParent();
            			 JPanel list=(JPanel)obj;
            			 JLabel amount=(JLabel)list.getComponent(1);
         
            			 result=result+Integer.valueOf(amount.getText()); 
            			 ((JLabel)prizeUI.getContentPane().getComponent(1)).setText(Integer.toString(result));
            			 ((JLabel)list.getComponent(2)).setText(Integer.toString(Integer.valueOf(((JLabel)list.getComponent(2)).getText())+1));	  
 
            			 prizeUI.repaint();
            		 } 
            	 });
        
            	 btn2.addActionListener(new ActionListener() {
            		 public void actionPerformed(ActionEvent e){
            			 int result= Integer.valueOf(((JLabel)(prizeUI.getContentPane().getComponent(1))).getText());
                			
            			 if(Integer.valueOf(((JLabel)list.getComponent(2)).getText())>1){
            				 Container obj=btn.getParent();
            				 JPanel list=(JPanel)obj;
            				 JLabel amount=(JLabel)list.getComponent(1);
            				 result=result-Integer.valueOf(amount.getText()); 
            				 ((JLabel)prizeUI.getContentPane().getComponent(1)).setText(Integer.toString(result));
            				 ((JLabel)list.getComponent(2)).setText(Integer.toString(Integer.valueOf(((JLabel)list.getComponent(2)).getText())-1));	  
            			 }
            		 } 
            	 });
            	 
            	 btn.setBounds(50,25,45,45);
            	 btn2.setBounds(230,25,45,45); 
            	 btn3.setBounds(305,8,45,45);
            	 btn3.setBorderPainted(false);
            	 btn3.setContentAreaFilled(false);
            	 btn3.setDefaultCapable(false);
            	 btn3.setFocusPainted(false);
            	 btn.setFont(new Font("Arial", Font.PLAIN, 15));
            	 btn2.setFont(new Font("Arial", Font.PLAIN, 15));
            	 btn3.setFont(new Font("Arial", Font.PLAIN, 15));
            	 btn.setBackground(Color.WHITE);
            	 btn2.setBackground(Color.WHITE);
            	 btn3.setBackground(Color.WHITE);
	 
            	 btn3.addActionListener(new ActionListener() {
            		 public void actionPerformed(ActionEvent e){
            			 Container obj=btn3.getParent();
            			 
            			 int result=Integer.valueOf((((JLabel)(prizeUI.getContentPane().getComponent(1))).getText()));
            			 JPanel list=(JPanel)obj;
            			 JLabel price=(JLabel)list.getComponent(1);
            			 result=result-Integer.valueOf(price.getText())*Integer.valueOf((((JLabel)list.getComponent(2))).getText());
            			 ((JLabel)prizeUI.getContentPane().getComponent(1)).setText(Integer.toString(result));
            			 
            			 System.out.println("ȣ��");
            			 buy.setLayout(new GridLayout(buy.getComponentCount()+5,1));
            			 buy.remove(obj);
            			 prizeUI.setVisible(false);
            			 prizeUI.setVisible(true);
            		 } 
            	 });
            	   	 
            	 list.add(btn3);
            	 list.add(btn);
            	 list.add(btn2);
	    
            	 list.setPreferredSize(new Dimension(50,100));
            	 list.setBounds(4,buy.getComponentCount()*100+3,355,100);
            	 list.setBorder(new LineBorder(Color.black));
            	 buy.add(list);
            	 prizeUI.setVisible(false);
            	 prizeUI.setVisible(true);	
             }
            });

            if(obj.getPrizeList().length>25){
            	prize_list.setLayout(new GridLayout(0,5,10,10));
            }
	  		 
            btn[i].setPreferredSize(new Dimension(102,102));
            btn[i].setBounds(c,r,173,110);
            c=c+181; 
	  
            if(btn[i].getLocation().x==724){
            	c=0;
            	r=r+120;
            }
	 	 	
            btn[i].setBackground(Color.WHITE);
	 
            JLabel Prize_name=new JLabel();
            JLabel Price=new JLabel();

            Prize_name.setBounds(70,20,50,20);
            Price.setBounds(70,60,50,20);
            Prize_name.setText(obj.getPrize(i).getPrize_name());
            Price.setText(obj.getPrize(i).getPrice());
            btn[i].setLayout(null);
            btn[i].add(Prize_name);
            btn[i].add(Price);
  	  		}
  	  	
  	  		jsp.setBounds(0,207,900,660);
	  	 

  	  		for(int i=0; i<obj.getPrizeList().length; i++) {
  	  			prize_list.add(btn[i]);
  	  		}
	  
  	  		prizeUI.add(jsp);
	  		prizeUI.setLayout(null);
	  		prizeUI.setVisible(true);
	  		prizeUI.setResizable(false);
	  		prizeUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  		prizeUI.setSize(1295, 905);
	  		prizeUI.setLocationRelativeTo(null);
			
	  		}

	protected static void setVisible(boolean b) {
		// TODO Auto-generated method stub	
	}
}

class Prize{
	private String prize_name;
		private String price;

		public String getPrize_name(){
			return prize_name; 
		}
		
		public String getPrice(){
			return price;
		}

		public void setPrize_name(String prize_name){
			this.prize_name=prize_name;
		}
		
		public void setprice(String price){
			this.price=price;
		}
 }

class prizeSystem extends db_connect{
	private Prize prize[];

	public Prize[] getPrizeList(){
		return prize;
	}
	
	public Prize getPrize(int idx){
		return prize[idx];
	}
	
	public prizeSystem getPrize(String query) throws SQLException{
		int size=0;    
        ResultSet rs=null;
        rs=select(query);
   
		rs.last();
	    size = rs.getRow();
        rs.beforeFirst();
        prize=new Prize[size];
          
        for(int i=0; i<size; i++){
        	prize[i]=new Prize();
		} 
		
	    int j=0;
		while( rs.next()){
			prize[j].setPrize_name(rs.getString("��ǰ��"));
			prize[j].setprice(Integer.toString(rs.getInt("����")));	       
			j++;				
        }	
		 		 
	      close();
			 
	      return this; 	    	 
	    }
}
