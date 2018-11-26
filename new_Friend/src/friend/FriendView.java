package friend;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class FriendView extends JFrame {
	public int selectedNum;
	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel lb1;
	private JLabel lb2;
	private JLabel lb3;
	private JLabel lb4;
	private JTextField tfName;
	private JTextField tfBirth;
	private JTextField tfPhone;
	private JTextField tfAddr;
	private JButton btnView;
	private JButton btnInsert;
	private JSplitPane splitPane_1;
	private JScrollPane scrollPane;
	private JTextArea taView;
	private JPanel panel_1;
	private JComboBox cbSel;
	private JTextField tfSearch;
	private JButton btnSearch;
	
	FriendDBAImpl dba = new FriendDBAImpl(); 
	private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendView frame = new FriendView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FriendView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getSplitPane());
	}
	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getSplitPane_1());
			splitPane.setDividerLocation(280);
		}
		return splitPane;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setFont(panel.getFont().deriveFont(panel.getFont().getStyle() & ~Font.BOLD));
			panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "\uCE5C\uAD6C\uB4F1\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setLayout(null);
			panel.add(getLb1());
			panel.add(getLb2());
			panel.add(getLb3());
			panel.add(getLb4());
			panel.add(getTfName());
			panel.add(getTfBirth());
			panel.add(getTfPhone());
			panel.add(getTfAddr());
			panel.add(getBtnView());
			panel.add(getBtnInsert());
		}
		return panel;
	}
	private JLabel getLb1() {
		if (lb1 == null) {
			lb1 = new JLabel("이름");
			lb1.setHorizontalAlignment(SwingConstants.RIGHT);
			lb1.setFont(lb1.getFont().deriveFont(lb1.getFont().getStyle() | Font.BOLD));
			lb1.setBounds(12, 41, 57, 15);
		}
		return lb1;
	}
	private JLabel getLb2() {
		if (lb2 == null) {
			lb2 = new JLabel("생일");
			lb2.setHorizontalAlignment(SwingConstants.RIGHT);
			lb2.setFont(lb2.getFont().deriveFont(lb2.getFont().getStyle() | Font.BOLD));
			lb2.setBounds(12, 81, 57, 15);
		}
		return lb2;
	}
	private JLabel getLb3() {
		if (lb3 == null) {
			lb3 = new JLabel("전화번호");
			lb3.setHorizontalAlignment(SwingConstants.RIGHT);
			lb3.setFont(getLb2().getFont().deriveFont(getLb2().getFont().getStyle() | Font.BOLD));
			lb3.setBounds(12, 122, 57, 15);
		}
		return lb3;
	}
	private JLabel getLb4() {
		if (lb4 == null) {
			lb4 = new JLabel("주소");
			lb4.setHorizontalAlignment(SwingConstants.RIGHT);
			lb4.setFont(getLb2().getFont().deriveFont(getLb2().getFont().getStyle() | Font.BOLD));
			lb4.setBounds(12, 165, 57, 15);
		}
		return lb4;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setMinimumSize(new Dimension(6, 15));
			tfName.setBounds(81, 35, 174, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JTextField getTfBirth() {
		if (tfBirth == null) {
			tfBirth = new JTextField();
			tfBirth.setMinimumSize(new Dimension(6, 15));
			tfBirth.setBounds(81, 75, 174, 21);
			tfBirth.setColumns(10);
		}
		return tfBirth;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setMinimumSize(new Dimension(6, 15));
			tfPhone.setBounds(81, 116, 174, 21);
			tfPhone.setColumns(10);
		}
		return tfPhone;
	}
	private JTextField getTfAddr() {
		if (tfAddr == null) {
			tfAddr = new JTextField();
			tfAddr.setMinimumSize(new Dimension(6, 15));
			tfAddr.setBounds(81, 159, 174, 21);
			tfAddr.setColumns(10);
		}
		return tfAddr;
	}
	private JButton getBtnView() {
		if (btnView == null) {
			btnView = new JButton("추가");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					taView.setText("");
					ArrayList<Friend> f = dba.friendView();
					for(int i=0;i<f.size();i++) {
						taView.append(f.get(i).getNum()+"\n");
						taView.append(f.get(i).getName()+"\n");
						taView.append(f.get(i).getBirth()+"\n");
						taView.append(f.get(i).getPhone()+"\n");
						taView.append(f.get(i).getAddr()+"\n\n");
					}
				}
			});
			btnView.setFont(new Font("굴림", Font.BOLD, 10));
			btnView.setBounds(37, 200, 91, 23);
		}
		return btnView;
	}
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("전체보기");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Friend f = new Friend();
					f.setName(tfName.getText());
					f.setBirth(tfBirth.getText());
					f.setPhone(tfPhone.getText());
					f.setAddr(tfAddr.getText());
					dba.friendInsert(f);
				}
			});
			btnInsert.setFont(new Font("굴림", Font.BOLD, 10));
			btnInsert.setBounds(149, 200, 91, 23);
		}
		return btnInsert;
	}
	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getScrollPane());
			splitPane_1.setRightComponent(getPanel_1_1());
			splitPane_1.setDividerLocation(220);
		}
		return splitPane_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(new TitledBorder(null, "\uC804\uCCB4\uBCF4\uAE30", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollPane.setViewportView(getTaView());
		}
		return scrollPane;
	}
	private JTextArea getTaView() {
		if (taView == null) {
			taView = new JTextArea();
		}
		return taView;
	}
	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getCbSel());
			panel_1.add(getTfSearch());
			panel_1.add(getBtnSearch());
		}
		return panel_1;
	}
	private JComboBox getCbSel() {
		if (cbSel == null) {
			cbSel = new JComboBox();
			cbSel.setModel(new DefaultComboBoxModel(new String[] {"선택", "번호", "이름", "주소"}));
			cbSel.setBounds(12, 11, 51, 21);
		}
		return cbSel;
	}
	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(63, 11, 98, 21);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int type = cbSel.getSelectedIndex();//0?��?�� //1번호//2?���?//3주소
					if(type == 0) tfSearch.setText("?��?�� ?��?��?��주세?��");
					ArrayList<Friend> arr = dba.friendSearch(type, tfSearch.getText());
					taView.setText("");
					for(int i=0;i<arr.size();i++) {
					taView.append(arr.get(i).getNum()+"\n");
					taView.append(arr.get(i).getName()+"\n");
					taView.append(arr.get(i).getBirth()+"\n");;
					taView.append(arr.get(i).getPhone()+"\n");
					taView.append(arr.get(i).getAddr()+"\n\n");
					//selectedNum = f.getNum();
					}
				}
			});
			btnSearch.setBounds(161, 10, 73, 23);
		}
		return btnSearch;
	}
}
