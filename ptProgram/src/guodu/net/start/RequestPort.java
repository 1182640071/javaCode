package guodu.net.start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import guodu.net.send.SendMessage;
import guodu.net.send.SendPotMessqge;
import guodu.net.util.Util;


public class RequestPort implements ActionListener{
	
	private String mobile;
	private String bumen;
	private String username;
	private String beizhu;
	private String name ;
	private String password ;
	
	private JFrame frame = new JFrame("端口申请工具") ; 
	private JTable table = null ;
	private DefaultTableModel tableModel ;	// TableModel
	private String[] titles = {"源地址","目的地址","开放服务端/端口","预计租期(天)","所在机房","真是主机","备注"} ;
	private Object [][] userInfo = {{"","","","","","",""}} ;	// 定义数据
	private JButton addRowBtn = new JButton("增加行") ;	 // 定义按钮
	private JButton removeRowBtn = new JButton("删除行") ;	 // 定义按钮
	private JButton rightColBtn = new JButton("提交") ;	 // 提交按钮
	private JButton returnColBtn = new JButton("返回") ;	 // 提交按钮
	private JLabel tishi = new JLabel("请输入申请参数") ;	 // 提交按钮

	public RequestPort( String name ,String password , String mobile , String bumen , String username , String beizhu){
		this.name     = name;
		this.password = password;
		this.mobile   = mobile;
		this.bumen    = bumen;
		this.username = username;
		this.beizhu   = beizhu;
		
		Font fnt = new Font("Serief" , Font.BOLD , 10);
		tishi.setFont(fnt);
		tishi.setForeground(Color.red);   //前景颜色，就是文字颜色了
		
		this.tableModel = new DefaultTableModel(this.userInfo,this.titles) ;
		this.table = new JTable(this.tableModel) ;
		JScrollPane scr = new JScrollPane(this.table) ;
		JPanel toolBar = new JPanel() ;
		toolBar.add(this.addRowBtn) ;
		toolBar.add(this.removeRowBtn) ;
		toolBar.add(this.rightColBtn);
		toolBar.add(this.returnColBtn);
		toolBar.add(this.tishi);
		frame.add(toolBar,BorderLayout.NORTH) ;	// 加入组件
		frame.add(scr,BorderLayout.CENTER) ;	// 加入组件
		frame.setSize(570,220) ;
		frame.setVisible(true) ;
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(1) ;
			}
		}) ;
		this.addRowBtn.addActionListener(this) ;
		this.removeRowBtn.addActionListener(this) ;
		this.rightColBtn.addActionListener(this) ;
		this.returnColBtn.addActionListener(this) ;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.rightColBtn){
			Util ut = new Util();
			try {
				for(int x = 0 ; x < this.tableModel.getRowCount() ; x ++ ){
					for(int y = 0 ; y < 7; y++){
						if(ut.judgeNull(tableModel.getValueAt(x, y).toString())){
							tishi.setText("参数不能为空，空值坐标：[" + x+1 + " , " + y + "]");
							return;
						}
					}
				}
			} catch (Exception e1) {
				tishi.setText("参数不能为空,请检查输入参数是否有误");
				return;
			}
			String result = new SendPotMessqge().sendMessages(name , password , mobile , bumen ,username ,tableModel.getDataVector().toString().replace("], [", "!|").replace("]", "").replace("[", ""));
			if("0".equals(result) || "0" == result){
				tishi.setText("提交成功");
			}else{
				tishi.setText("提交失败");
			}
		}
		
		if(e.getSource() == this.returnColBtn){
			new UserInfomation(name , password);
		}
		
		if(e.getSource() == this.addRowBtn){
			this.tableModel.addRow(new Object[]{}) ;
		}
		if(e.getSource()==this.removeRowBtn){
			int rowCount = this.tableModel.getRowCount() - 1 ;
			if(rowCount>=0){	// 判断是否还有行可以删除
				this.tableModel.removeRow(rowCount) ;
				this.tableModel.setRowCount(rowCount) ;	// 设置新的行数
			}
		}
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}	
	
}
