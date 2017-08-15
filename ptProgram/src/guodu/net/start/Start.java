package guodu.net.start;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import guodu.net.send.SendMessage;
import guodu.net.util.ConfigContainer;

public class Start {
	
	private JFrame frame = new JFrame("端口申请");
	private JButton submit = new JButton("onload");
	private JButton reset = new JButton("reset");
	private JLabel nameLab = new JLabel("username");
	private JLabel passLab = new JLabel("password");
	private JLabel infoLab = new JLabel("用户登录系统");
	private JTextField nameText = new JTextField();
	private JPasswordField passText = new JPasswordField();
	
	public Start(){
		
		Font fnt = new Font("Serief" , Font.BOLD , 12);
		infoLab.setFont(fnt);
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				if(arg0.getSource() == submit){
					String tname = nameText.getText();
					String tpass = new String(passText.getPassword());
					String result = new SendMessage().sendMessages(tname , tpass);
					if("0".equals(result)){
						infoLab.setText("登录成功");
						frame.setVisible(false);	
						new UserInfomation(tname , tpass);
					}else{
						infoLab.setText("用户名或密码有误");
					}
				}
			}
		});
		
		reset.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){

				if(arg0.getSource() == reset){
					nameText.setText("");
					passText.setText("");
					infoLab.setText("用户登录系统");
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0){
				System.exit(1);
			}
			
		});
		
		frame.setLayout(null);
		nameLab.setBounds(5, 5, 65, 20);
		passLab.setBounds(5, 30, 65, 20);
		infoLab.setBounds(5, 65, 220, 30);
		nameText.setBounds(70, 5, 100, 20);
		passText.setBounds(70, 30 , 100, 20);
		submit.setBounds(170, 5, 60, 20);
		reset.setBounds(170, 30, 60, 20);
		frame.add(nameLab);
		frame.add(passLab);
		frame.add(infoLab);
		frame.add(nameText);
		frame.add(passText);
		frame.add(submit);
		frame.add(reset);
		frame.setSize(280, 130);
		frame.setVisible(true);	
	};
	
	
	
	
	public static void main(String args[]){
 		ConfigContainer cf = ConfigContainer.getInstance();
 		cf.load();
		new Start();
	}
};

