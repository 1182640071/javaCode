package guodu.net.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import guodu.net.util.Util;

public class UserInfomation {

	private String username;
	private String password;
	
	private JFrame frame = new JFrame("端口申请");
	private JButton submit = new JButton("确定");
	private JButton reset = new JButton("reset");
	private JLabel nameLab = new JLabel("申请人");
	private JLabel department = new JLabel("部门");
	private JLabel mobile = new JLabel("申请人号码");
	private JLabel remark = new JLabel("备注");
	private JLabel infoLabuser = new JLabel("输入姓名");
	private JLabel infoLabdepar = new JLabel("输入部门");
	private JLabel mobLabdepar = new JLabel("输入申请人号码");
	private JTextField nameText = new JTextField(); //申请人
	private JTextField deparText = new JTextField(); //部门
	private JTextField mobileText = new JTextField(); //备注
	private JTextField remarkText = new JTextField(); //备注
	
	public UserInfomation(final String username , final String password){
		this.username = username;
		this.password = password;
		
		Font fnt = new Font("Serief" , Font.BOLD , 10);
		infoLabuser.setFont(fnt);
		infoLabuser.setForeground(Color.red);   //前景颜色，就是文字颜色了
		infoLabdepar.setFont(fnt);
		infoLabdepar.setForeground(Color.red);   //前景颜色，就是文字颜色了
		mobLabdepar.setFont(fnt);
		mobLabdepar.setForeground(Color.red);   //前景颜色，就是文字颜色了
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String tname = null;
				String depar = null;
				String remar = null;
				String mob   = null;
				Boolean flag = true;
				if(arg0.getSource() == submit){
					tname = nameText.getText();
					depar = deparText.getText();
					remar = remarkText.getText();
					mob   = mobileText.getText();
					Util ut = new Util();
					if(ut.judgeNull(tname)){
						infoLabuser.setText("申请人不能为空");
						flag = false;
					}
					if(ut.judgeNull(depar)){
						infoLabdepar.setText("部门、备注不能为空");
						flag = false;
					}
//					if(ut.judgeNull(remar)){
//						infoLabdepar.setText("部门、备注不能为空");
//						flag = false;
//					}
					if(ut.judgeNull(mob)){
						mobileText.setText("申请人号码不能为空");
						flag = false;
					}
					if(!flag){
						return;
					}
				}
				new RequestPort( username , password , mob , depar , tname , remar);
				frame.setVisible(false);	
			}
		});
		
		reset.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){

				if(arg0.getSource() == reset){
					nameText.setText("");
					deparText.setText("");
					remarkText.setText("");
					infoLabuser.setText("输入姓名");
					infoLabdepar.setText("输入部门");
					mobileText.setText("输入申请人号码");
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0){
				System.exit(1);
			}
			
		});
		
		
		frame.setLayout(null);
		nameLab.setBounds(5, 5, 50, 30);
		nameText.setBounds(60, 5, 250, 30);
		infoLabuser.setBounds(315, 5, 100, 30);
		department.setBounds(5, 60, 50, 30);
		deparText.setBounds(60, 60, 250, 30);
		infoLabdepar.setBounds(315, 60, 100, 30);
	
		mobile.setBounds(5, 115, 50, 30);
		mobileText.setBounds(60, 115, 250, 30);
		mobLabdepar.setBounds(315, 115, 100, 30);
		
		remark.setBounds(5, 170, 50, 30);
		remarkText.setBounds(60,170 , 430 , 30);
		submit.setBounds(5, 215, 245, 30);
		reset.setBounds(250, 215, 245, 30);

		frame.add(nameLab);
		frame.add(nameLab);
		frame.add(infoLabuser);
		frame.add(nameText);
		frame.add(department);
		frame.add(infoLabdepar);
		frame.add(mobile);
		frame.add(mobileText);
		frame.add(mobLabdepar);
		frame.add(deparText);
		frame.add(remark);
		frame.add(remarkText);
		frame.add(submit);
		frame.add(reset);
		frame.setSize(500, 270);
		frame.setVisible(true);	
	};
	
	
	public static void main(String args[]){
//		new UserInfomation();
	}
}
