package com.os.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.os.memory.ContinualMemoryManage;
import com.os.memory.Memory;
import com.os.memory.MemorySpan;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class MemoryFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private int displayLineLength = 500;
	private int displayLineHeight = 95;
	private int memorySize;
	private Memory memory;
	private ArrayList<MemorySpan> memoryParts;
	private JTextField tbSize;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MemoryFrame frame = new MemoryFrame(10000);
//					frame.setVisible(true);
//				//	Process_page pp=new Process_page();
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MemoryFrame(int memorySize) {
		this.memorySize = memorySize;
		memory = new Memory(memorySize, ContinualMemoryManage.FirstFit);
		memoryParts = memory.getMemoryParts();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2, 315, 541, 208);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 10, displayLineLength, displayLineHeight);
		panel.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(361, 138, 122, 21);
		comboBox.addItem(new String("最先适应算法"));
		comboBox.addItem(new String("最佳适应算法"));
		comboBox.addItem(new String("最差适应算法"));
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int select = comboBox.getSelectedIndex();
				switch (select) {
				case 0:
					MemoryFrame.this.memory
							.setManageRule(ContinualMemoryManage.FirstFit);
					break;
				case 1:
					MemoryFrame.this.memory
							.setManageRule(ContinualMemoryManage.BestFit);
					break;
				case 2:
					MemoryFrame.this.memory
							.setManageRule(ContinualMemoryManage.WorstFit);
					break;
				}
			}
		});
		contentPane.add(comboBox);

		textField_1 = new JTextField();
		textField_1.setBounds(20, 140, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label = new JLabel("\u65B0\u8FDB\u7A0B\u5927\u5C0F");
		label.setBounds(20, 115, 66, 15);
		contentPane.add(label);

		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				frush();
			}
		});

		btnNewButton.setBounds(89, 139, 93, 23);
		contentPane.add(btnNewButton);

		textField_2 = new JTextField();
		textField_2.setBounds(192, 140, 66, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblid_1 = new JLabel("\u91CA\u653E\u8FDB\u7A0Bid");
		lblid_1.setBounds(192, 115, 66, 15);
		contentPane.add(lblid_1);

		JButton button = new JButton("\u91CA\u653E");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				// 更新之前一定要记得清空
				panel.removeAll();

				try {
					MemoryFrame.this.memory.releaseTask(Integer
							.parseInt(MemoryFrame.this.textField_2.getText()));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				setDisplayLine(panel);
				textField_2.setText("");
				MemoryStatistics.arr=memory.getMemoryParts();
			}
		});
		button.setBounds(262, 139, 93, 23);
		contentPane.add(button);

		JLabel label_1 = new JLabel(
				"\u5F53\u524D\u5185\u5B58\u603B\u5927\u5C0F\uFF1A");
		label_1.setBounds(288, 110, 122, 25);
		contentPane.add(label_1);

		tbSize = new JTextField();
		tbSize.setBounds(385, 112, 66, 21);
		contentPane.add(tbSize);
		tbSize.setColumns(10);
		tbSize.setText(String.valueOf(this.memorySize));

		JButton btnModifySize = new JButton("\u4FEE\u6539");
		btnModifySize.setBounds(452, 111, 58, 23);
		btnModifySize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int size = Integer.parseInt(tbSize.getText());
				if (size != memorySize) {
					MemoryFrame mf = new MemoryFrame(size);
					mf.setVisible(true);
					MemoryFrame.this.dispose();
				}
			}
		});
		contentPane.add(btnModifySize);
		setDisplayLine(panel);
		MemoryStatistics.arr=memory.getMemoryParts();
	}
	
	public void frush() {
		// 更新之前一定要记得清空
		panel.removeAll();

		MemoryFrame.this.memory.disposeTask(-1, Integer
				.parseInt(MemoryFrame.this.textField_1.getText()));
		setDisplayLine(panel);
		// textField_1.setText("");
		MemoryStatistics.arr=memory.getMemoryParts();
	}
	
	public void frush(int size,int id) {
		panel.removeAll();
		MemoryFrame.this.memory.disposeTask(id, size);
		setDisplayLine(panel);
		// textField_1.setText("");
		MemoryStatistics.arr=memory.getMemoryParts();
	}
	
	public void frush(int id) {
		panel.removeAll();
		try {
			MemoryFrame.this.memory.releaseTask(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDisplayLine(panel);
		// textField_1.setText("");
		MemoryStatistics.arr=memory.getMemoryParts();
	}

	private void setDisplayLine(JPanel base) {
		ArrayList<MemorySpan> memorys = MemoryFrame.this.memory
				.getMemoryParts();
		JPanel panel1 = new JPanel();
		for (MemorySpan memorySpan : memorys) {
			int width = (int) ((memorySpan.getSize() * 1.0 / memorySize)
					* displayLineLength);
			int x = (int) ((memorySpan.getStartPosition() * 1.0 / memorySize)
					* displayLineLength);
			panel1 = new JPanel();
			panel1.setBorder(new LineBorder(Color.BLACK));
			panel1.setBounds(x, 0, width, displayLineHeight);
			JLabel label1 = new JLabel("size:"+String.valueOf(memorySpan.getSize()));
			label1.setVisible(true);
			label1.setBounds(1, displayLineHeight-1, width, 40);
			panel1.add(label1);
			if (memorySpan.isUsed() == false)
				panel1.setBackground(Color.GREEN);
			else {
				panel1.setBackground(Color.RED);
				label1 = new JLabel("id:"+memorySpan.getTaskId().toString());
				label1.setVisible(true);
				label1.setBounds(1, 0, width, 40);
				panel1.add(label1);
			}
			base.add(panel1);

			// 记得有这句刷新界面
			base.updateUI();
		}
	}
}
