package com.os.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.os.memory.MemorySpan;

import czxt.Device_List;
import czxt.Process_Comparetor;
import czxt.Process_List;
import czxt.Request_;
import czxt.Resourse_;
import czxt.SETTING;
import czxt.sdsd;

public class Process_page {

	private static JPanel contentPane;
	private static JPanel tablePanel;
	private static JScrollPane scrollPane;
	private static JScrollPane scrollPane1;
	private static JScrollPane scrollPane2;
	private static JTextArea textField;
	private static JTextArea textArea;
	private static JTable memoryTable;
	private static JTable processTable;
	public static JFrame frame;
	public static MemoryFrame mframe;
	private static String[] colName = { "进程号", "进程名", "状态", "优先级", "已运行时间",
			"总运行时间", "还需时间" };

	/**
	 * Launch the application.
	 */
	public static void Start(int processSize, String processName) {
		if (frame != null) {
			LinkedList<Request_> requests = new LinkedList<>();
			Resourse_ r = new Resourse_();
			switch (processName) {
			case "英雄联盟.exe":
				r.add("a", 2);
				r.add("b", 3);
				break;
			case "QQ飞车.exe":
				r.add("c", 5);
				break;
			case "绝地求生.exe":
				r.add("d", 5);
				break;
			case "qq.exe":
				r.add("a", 1);
				break;
			case "qq音乐.exe":
				r.add("b", 4);
				r.add("d", 4);
				break;
			case "微信.exe":
				r.add("c", 5);
				break;
			default:
				r.add("a", 3);
				r.add("b", 6);
				r.add("c", 6);
				r.add("d", 6);
			}
			requests.add(new Request_(1, r));
			int id = Process_List.add(new Random().nextInt(5), processName,
					1 + new Random().nextInt(5), requests, null);

			// creatProcessTable();
			processTable.removeAll();
			Object[][] datas = Process_List.gettable();
			processTable.setModel(new DefaultTableModel(datas, colName));
			processTable.updateUI();

			mframe.frush(processSize, id);
			tableFrush(memoryTable);
			scrollPane1.updateUI();
			return;
		}
		new sdsd();
		tablePanel = new JPanel();
		tablePanel.setBounds(14, 38, 851, 611);
		creatProcessTable();
		creatMemoryTable();
		creatDeviceTable();
		JPanel panelContainer = new JPanel();
		panelContainer.setForeground(Color.BLACK);
		panelContainer.setLayout(null);
		// 加入 middlePanel
		panelContainer.add(tablePanel);
		tablePanel.setLayout(null);
		tablePanel.add(scrollPane);

		JLabel label = new JLabel("");
		label.setBounds(434, 0, 434, 159);
		tablePanel.add(label);

		JMenuItem mntmNewMenuItem = new JMenuItem("进程所占内存");
		mntmNewMenuItem.setBounds(0, 201, 145, 31);
		tablePanel.add(mntmNewMenuItem);

		JComboBox comboBox = new JComboBox(Process_Comparetor.key);
		comboBox.setBounds(660, 177, 181, 31);
		tablePanel.add(comboBox);
		tablePanel.add(scrollPane1);
		tablePanel.add(scrollPane2);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("设备管理");
		mntmNewMenuItem_1.setBounds(424, 208, 145, 24);
		tablePanel.add(mntmNewMenuItem_1);

		JButton btnNewButton = new JButton("添加设备");
		btnNewButton.setBounds(617, 256, 113, 27);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				Device_List.add(textField.getName(),
						Integer.parseInt(textArea.getText()));
				deviceTable.removeAll();
				deviceTable.setModel(new DefaultTableModel(
						Device_List.gettable(),
						new String[] { "设备名", "总数量", "剩余数量", "已分配数量" }));
				deviceTable.updateUI();
			}
		});
		tablePanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("删除设备");
		btnNewButton_1.setBounds(617, 296, 113, 27);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);

			}
		});
		tablePanel.add(btnNewButton_1);

		textField = new JTextArea();
		textField.setBounds(517, 257, 86, 24);
		tablePanel.add(textField);
		textField.setColumns(10);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("设备名：");
		mntmNewMenuItem_2.setBounds(424, 256, 107, 24);
		tablePanel.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("数量：");
		mntmNewMenuItem_3.setBounds(424, 299, 93, 24);
		tablePanel.add(mntmNewMenuItem_3);

		textArea = new JTextArea();
		textArea.setBounds(517, 298, 86, 24);
		tablePanel.add(textArea);

		frame = new JFrame("进程管理器");
		panelContainer.setOpaque(true);
		frame.setBounds(500, 0, 897, 709);
		frame.setContentPane(panelContainer);

		JButton btnNewButton4 = new JButton("模拟时间片运行");
		btnNewButton4.setBounds(480, 177, 145, 24);
		tablePanel.add(btnNewButton4);
		btnNewButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SETTING.ready = Process_Comparetor.value[comboBox
						.getSelectedIndex()];
				SETTING.block = Process_Comparetor.value[comboBox
						.getSelectedIndex()];
				int id = Process_List.run();
				processTable.setModel(new DefaultTableModel(
						Process_List.gettable(), colName));
				processTable.updateUI();

				deviceTable.removeAll();
				deviceTable.setModel(new DefaultTableModel(
						Device_List.gettable(),
						new String[] { "设备名", "总数量", "剩余数量", "已分配数量" }));
				deviceTable.updateUI();
				if (id != -1) {
					mframe.frush(id);
					tableFrush(memoryTable);
					scrollPane1.updateUI();

				}
			}
		});
		JMenuItem menuItem = new JMenuItem("进程");
		menuItem.setBounds(14, 13, 80, 24);
		panelContainer.add(menuItem);
		frame.setVisible(true);

		mframe = new MemoryFrame(20);
		mframe.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public static void creatProcessTable() {

		processTable = new JTable(
				new DefaultTableModel(Process_List.gettable(), colName));
		scrollPane = new JScrollPane(processTable);
		scrollPane.setBounds(0, 0, 841, 174);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private static JTable deviceTable;

	public static void creatMemoryTable() {
		String[] colName1 = { "进程号", "起始地址", "终止地址", "所占内存" };
		Object[][] data1 = {};
		memoryTable = new JTable(data1, colName1);
		memoryTable.setBounds(14, 197, 338, 245);
		scrollPane1 = new JScrollPane(memoryTable);
		scrollPane1.setBounds(0, 234, 410, 377);
		scrollPane1.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public static void creatDeviceTable() {
		String[] colName2 = { "设备名", "总数量", "剩余数量", "已分配数量" };
		Object[][] data2 = Device_List.gettable();
		deviceTable = new JTable(data2, colName2);
		scrollPane2 = new JScrollPane(deviceTable);
		scrollPane2.setBounds(430, 335, 407, 276);
		scrollPane2.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private static void tableFrush(JTable jt) {
		int count = 0;

		for (MemorySpan ms : MemoryStatistics.arr) {
			if (ms.isUsed() == true) {
				count++;
			}
		}

		Object[][] datas = new Object[count][];
		int i = 0;
		for (MemorySpan ms : MemoryStatistics.arr) {
			if (ms.isUsed() == true) {
				ArrayList<Object> arr1 = new ArrayList<>();
				arr1.add(ms.getTaskId());
				arr1.add(ms.getStartPosition());
				arr1.add(ms.getEndPosition());
				arr1.add(ms.getSize());
				datas[i] = arr1.toArray();
				i++;
			}
		}

		jt.setModel(new DefaultTableModel(datas,
				new String[] { "进程号", "起始地址", "终止地址", "所占内存" }));
		jt.updateUI();
	}
}
