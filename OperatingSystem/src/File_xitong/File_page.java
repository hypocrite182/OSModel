package File_xitong;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.os.test.Process_page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class File_page {

	static Object[][] bitMap = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	static String[] head = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
	static JPanel topPanel;
	static JPanel bottomPanel;
	static JPanel middlePanel;
	static JTree tree;
	static File_file f;
	static JTextArea textArea1;
	private static JScrollPane scrollPane;

	private static JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		file_page();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public static void file_page() {
		final spaceDeal space = new spaceDeal(10, 10);
		f = new File_file("D:", "目录", 17);
		File_file ff = new File_file("QQ", "目录", 5);
		File_file f1 = new File_file("qq.exe", ".exe", 2);
		File_file f2 = new File_file("微信.exe", ".exe", 3);
		File_file f3 = new File_file("qq音乐.exe", ".exe", 2);
		File_file f4 = new File_file("英雄联盟.exe", ".exe", 1);
		File_file f5 = new File_file("游戏", "目录", 7);
		File_file f6 = new File_file("QQ飞车.exe", ".exe", 2);
		File_file f7 = new File_file("绝地求生.exe", ".exe", 3);
		File_file f8 = new File_file("聊天", "目录", 4);
		f5.addFile(f4);
		f5.addFile(f6);
		f5.addFile(f7);

		ff.addFile(f1);
		ff.addFile(f3);

		f8.addFile(f2);
		f.addFile(f5);
		f.addFile(ff);
		f.addFile(f8);
		File_file test = FileOpertion.findFile("D:/游戏/英雄联盟.exe", f);
		System.out.println(test.getName());
		createTopPanel();
		createMiddlePanel();
		ArrayList<File_file> filelist = new ArrayList<File_file>();
		FileOpertion.findFileofType(f, ".exe", filelist);
		for (int i = 0; i < filelist.size(); i++) {
			System.out.println(filelist.get(i).getName());
		}
		JPanel panelContainer = new JPanel();
		panelContainer.setForeground(Color.BLACK);
		panelContainer.setLayout(null);
		// 加入 topPanel
		panelContainer.add(topPanel);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.weightx = 1.0;
		c2.weighty = 0;
		c2.fill = GridBagConstraints.HORIZONTAL;
		// 加入 middlePanel
		panelContainer.add(middlePanel);
		middlePanel.setLayout(null);
		middlePanel.add(scrollPane);

		JPanel jBit = new JPanel();
		jBit.setLayout(null);
		middlePanel.add(jBit);

		table_1 = new JTable(space.bit, head);// 位示图
		table_1.setBounds(422, 53, 173, 160);
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (table_1.getModel().getValueAt(i, j).toString()
						.equals("1")) {
					String[] arr = { String.valueOf(i), String.valueOf(j) };
					list.add(arr);
				}
			}
		}
		EvenOddRenderer tablecellRender = new EvenOddRenderer(list);
		table_1.setDefaultRenderer(Object.class, tablecellRender);

		middlePanel.add(table_1);

		textArea1 = new JTextArea("位视图");
		textArea1.setForeground(Color.WHITE);
		textArea1.setBackground(Color.GRAY);
		textArea1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea1.setBounds(478, 13, 58, 31);
		middlePanel.add(textArea1);
		// 创建窗体
		JFrame frame = new JFrame("文件管理");
		panelContainer.setOpaque(true);
		frame.setSize(new Dimension(750, 450));
		frame.setContentPane(panelContainer);

		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 113, 433);
		panelContainer.add(panel);
		panel.setLayout(null);

		DefaultMutableTreeNode r = new DefaultMutableTreeNode(f.getName());

		FileOpertion.buildTree(f, r);
		tree = new JTree(r);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(final TreeSelectionEvent e) {
				tree.addMouseListener(new java.awt.event.MouseAdapter() {

					public void mouseClicked(java.awt.event.MouseEvent evt) {
						String path = e.getPath().toString();
						path = path.substring(1);
						path = path.replaceAll("]", "");
						path = path.replaceAll(" ", "");
						if (evt.getModifiers() == InputEvent.BUTTON1_MASK) {

							File_file test = FileOpertion.findFile(path, f);

							// if(test.getType().equals(".exe")) {
							// Process_page.Start();
							// }

							String[][] rowData1 = new String[10][4];

							FileOpertion.findFileNature(test, rowData1, 0);
							int sign = 0;
							for (int i = 0; i < 10; i++) {

								if (rowData1[i][0] == null)
									break;
								sign++;

							}
							String[][] rowData = new String[sign][4];
							for (int i = 0; i < sign; i++) {
								rowData[i] = rowData1[i];
							}
							// System.out.println(path);
							if (test != null) {
								// Object[] datas=new
								// Object[]{test.getName(),test.getType(),test.getSize(),test.getNewtime()};
								table.setModel(new DefaultTableModel(rowData,
										new String[] { "名称", "类型", "大小",
												"修改日期" }));
								table.updateUI();
							}

						}

						if (evt.getModifiers() == InputEvent.BUTTON3_MASK) {

							final String path1 = path;
							System.out.println(path);
							JMenuItem mAll, mCopy, mCut,mInprocess;
							final JPopupMenu menu;
							menu = new JPopupMenu();
							mAll = new JMenuItem("新建");
							if (!path.endsWith(".exe")) {

								menu.add(mAll);
							}
							mInprocess=new JMenuItem("加入进程");
							if (path.endsWith(".exe")) {
								menu.add(mInprocess);
							}
							mCopy = new JMenuItem("删除");
							menu.add(mCopy);
							mCut = new JMenuItem("重命名");
							menu.add(mCut);
							menu.show(tree, evt.getX(), evt.getY());
							mAll.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {

									String inputValue = JOptionPane
											.showInputDialog(
													"请输入文件名,文件类型,文件大小.并以空格分开");
									if (inputValue == null
											|| inputValue.trim().equals("")) {
										System.out.print("请输入数据");
										return;
									}

									String nature[] = inputValue.split(" ");
									// int a = nature[2];todo
									int a = Integer.valueOf(nature[2]);
									File_file file = new File_file(nature[0],
											nature[1], a);
									if (FileOpertion.insertFile(path1, f,
											file)) {
										System.out.print("插入成功");
										 JOptionPane.showMessageDialog(null, "成功插入！");
										DefaultMutableTreeNode r = new DefaultMutableTreeNode(
												f.getName());

										FileOpertion.buildTree(f, r);
										tree.setModel(new DefaultTreeModel(r));

										table_1.setModel(new DefaultTableModel(
												space.bit, head));
										table_1.updateUI();
										List<String[]> list = new ArrayList<String[]>();
										for (int i = 0; i < 10; i++) {
											for (int j = 0; j < 10; j++) {
												if (table_1.getModel()
														.getValueAt(i, j)
														.toString()
														.equals("1")) {
													String[] arr = {
															String.valueOf(i),
															String.valueOf(j) };
													list.add(arr);
												}
											}
										}
										EvenOddRenderer tablecellRender = new EvenOddRenderer(
												list);
										table_1.setDefaultRenderer(Object.class,
												tablecellRender);
									}

									else {
										 JOptionPane.showMessageDialog(null, "插入失败，文件重名！");
									}
								}
							});
							mCopy.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									File_file file = FileOpertion
											.findFile(path1, f);
									int a = file.getSize();
									if (FileOpertion.deleteFile(path1, f, a)) {
										System.out.println("删除成功");
										 JOptionPane.showMessageDialog(null, "删除成功！");
										DefaultMutableTreeNode r = new DefaultMutableTreeNode(
												f.getName());

										FileOpertion.buildTree(f, r);
										tree.setModel(new DefaultTreeModel(r));

										table_1.setModel(new DefaultTableModel(
												space.bit, head));
										table_1.updateUI();
										List<String[]> list = new ArrayList<String[]>();
										for (int i = 0; i < 10; i++) {
											for (int j = 0; j < 10; j++) {
												if (table_1.getModel()
														.getValueAt(i, j)
														.toString()
														.equals("1")) {
													String[] arr = {
															String.valueOf(i),
															String.valueOf(j) };
													list.add(arr);
												}
											}
										}
										EvenOddRenderer tablecellRender = new EvenOddRenderer(
												list);
										table_1.setDefaultRenderer(Object.class,
												tablecellRender);
									} else {
										 JOptionPane.showMessageDialog(null, "删除失败，系统异常！");
									}
								}
							});
							mInprocess.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									File_file file = FileOpertion
											.findFile(path1, f);
									Process_page.Start(file.getSize(),file.getName());
									Process_page.frame.setVisible(true);
									Process_page.mframe.setVisible(true);
								}
							});
							mCut.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									String inputValue = JOptionPane
											.showInputDialog("请输入文件名");
									if (FileOpertion.findFile(path1, f,
											inputValue)) {
										System.out.println("成功命名");
										 JOptionPane.showMessageDialog(null, "命名成功！");
										DefaultMutableTreeNode r = new DefaultMutableTreeNode(
												f.getName());

										FileOpertion.buildTree(f, r);
										tree.setModel(new DefaultTreeModel(r));
									} else {
										 JOptionPane.showMessageDialog(null, "命名失败，文件重名！");
									}
								}
							});
						}
					}
				});
			}
		});

		tree.setBounds(5, 5, 108, 415);

		panel.add(tree);
		tree.setEditable(true);
		tree.setRootVisible(true);
		frame.setVisible(true);
		Process_page.Start(0,"");
		Process_page.frame.setVisible(false);
		Process_page.mframe.setVisible(false);
	}

	static JTextArea textArea;

	static void createTopPanel() {
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 718, 37);
		topPanel.setLayout(null);

		textArea = new JTextArea("请输入文件名");
		textArea.setBounds(546, 6, 82, 24);
		textArea.setBackground(Color.LIGHT_GRAY);
		topPanel.add(textArea);

		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = textArea.getText();
				ArrayList<File_file> filelist = new ArrayList<File_file>();
				if (f.getName().equals(filename))
					filelist.add(f);
				FileOpertion.findFile(filename, f, filelist);
				if (filelist.size() == 0) {
					textArea.setText("");
					return;
				}

				String data[][] = new String[filelist.size()][4];
				for (int i = 0; i < filelist.size(); i++) {
					data[i][0] = filelist.get(i).getName();
					data[i][1] = filelist.get(i).getType();
					data[i][2] = filelist.get(i).getSize() + "";
					data[i][3] = filelist.get(i).getNewtime();
				}
				table.setModel(new DefaultTableModel(data,
						new String[] { "名称", "类型", "大小", "修改日期" }));
				table.updateUI();
				textArea.setText("");

			}
		});
		btnNewButton.setBounds(641, 6, 63, 24);
		topPanel.add(btnNewButton);
	}

	static JTable table;

	static void createMiddlePanel() {
		// 创建 middlePanel
		middlePanel = new JPanel();
		middlePanel.setBounds(137, 47, 595, 433);

		String[] columnName = { "名称", "类型", "大小", "修改日期" };

		String[][] rowData1 = new String[10][4];

		FileOpertion.findFileNature(f, rowData1, 0);
		int sign = 0;
		for (int i = 0; i < 10; i++) {

			if (rowData1[i][0] == null)
				break;
			sign++;

		}
		String[][] rowData = new String[sign][4];
		for (int i = 0; i < sign; i++) {
			rowData[i] = rowData1[i];
		}
		// String a[]={"123","456","798",path};

		// String b[]={"123","456","798","147"};
		// rowData[0] =a;
		// rowData[1] =b;

		table = new JTable(new DefaultTableModel(rowData, columnName));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 423, 433);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// JTable bitTable = new JTable(bitMap,head);
		// scrollPane1 = new JScrollPane(table);
		// scrollPane1.setBounds(423, 0, 423, 433);
		// scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
