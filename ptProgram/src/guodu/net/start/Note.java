package guodu.net.start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Note implements ActionListener {
	
	public static void main(String[] args){
		new Note();
	}
	
	private JButton submit = new JButton("onload");
	private JButton reset = new JButton("reset");
	private JLabel nameLab = new JLabel("username");
	private JLabel passLab = new JLabel("password");
	private JLabel infoLab = new JLabel("用户登录系统");
	
	
	private static ArrayList<String> list = null;
	private static File file = null;

	private JTextArea area = new JTextArea(8, 10); // �����ı�������
	private JFrame frame = new JFrame("����жϲ��"); // ʵ�����������
	private JButton open = new JButton("ѡ������ļ�");
	private JButton save = new JButton("ƥ�����");
	private JLabel label = new JLabel("��ѡ���ļ�"); // �����ǩ����ʵ�ļ���Ϣ
	private JPanel butPan = new JPanel(); // �������

	public Note() {
		this.butPan.add(open);
		this.butPan.add(save);
		// ���ô����еĲ��� ������ΪBorderLayout,���е����ˮƽ�ʹ�ֱ����Ϊ3
		frame.setLayout(new BorderLayout(3, 3));
		frame.add(this.label, BorderLayout.NORTH); // ����������Ϸ�
		frame.add(this.butPan, BorderLayout.SOUTH); // ����������·�
		frame.add(new JScrollPane(this.area), BorderLayout.CENTER);
		frame.add(nameLab);
		this.frame.setSize(330, 180);
		this.frame.setVisible(true); // ��ʵ����
		this.frame.addWindowFocusListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) { // ��д���ڹرշ���
				System.exit(1);
			}
		});
		this.open.addActionListener(this);
		this.save.addActionListener(this);
		frame.setSize(300, 200);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) { // ��ť�¼�����
		// TODO Auto-generated method stub
		int result = 0;
		JFileChooser fileChooser = new JFileChooser(); // ʵ�����ļ�ѡ���
		if (e.getSource() == this.open) {
			this.area.setText("");
			fileChooser.setApproveButtonText("ȷ��");
			fileChooser.setDialogTitle("���ļ�");
			result = fileChooser.showOpenDialog(this.frame);
			if (result == JFileChooser.APPROVE_OPTION) // ѡ����ȷ����ť
			{
				file = fileChooser.getSelectedFile();


				// System.out.println("�򿪵��ļ���"+file.getPath());
				this.label.setText("���ļ����Ϊ��" + file.getName());
			} else { // result == JFileChooser.ERROR_OPTION
				this.label.setText("û��ѡ���ļ�");
			}
			if (file != null) {

			}
			try {
				Scanner scan = new Scanner(new FileInputStream(file));
				scan.useDelimiter("\n");
				while (scan.hasNext()) {
					this.area.append(scan.next());
					this.area.append("\n");
				}
				scan.close();
			} catch (Exception ex) {
				this.label.setText("�ļ���ȡ����");
			}
		}

		if (e.getSource() == this.save) // �ж��ǲ���save��ť
		{
			if (file == null || file.equals("")) {
				this.area.setText("");
				this.area.append("û��ѡ���ļ�!");
				return;
			}

			result = fileChooser.showSaveDialog(this.frame);
			if (result == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				// System.out.println("�洢���ļ���"+file.getPath());
				this.label.setText("ѡ���ļ���Ϊ��" + file.getName());
			} else if (result == JFileChooser.CANCEL_OPTION) { // �ж��Ƿ�ʱȡ��
				this.label.setText("û��ѡ���ļ�");
			} else {
				this.label.setText("��������");
			}
			if (file != null) {
				File f = new File(file.getPath());
				OutputStream out;
				try {
					out = new FileOutputStream(f);
					String aa = "�ֻ����" + (char) 9 + "��غ�";
					byte mobile1[] = aa.getBytes();
					for (int k = 0; k < mobile1.length; k++) {
						out.write(mobile1[k]);
					}
					for (int s = 0; s < list.size(); s++) {
						if (list.get(s) != null && !list.get(s).equals("")) {

							byte mobile[] = aa.getBytes();
							for (int k = 0; k < mobile.length; k++) {
								out.write(mobile[k]);
							}
						}
					}
					out.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}
			this.area.setText("");
			this.area.append("�������!");
		}
	}

}
