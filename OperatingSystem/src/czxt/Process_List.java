package czxt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Vector;

import org.omg.CORBA.OBJ_ADAPTER;

public class Process_List {
	public static LinkedList<Process_> ready_list = new LinkedList<>();
	public static LinkedList<Process_> block_list = new LinkedList<>();
	public static ArrayList<Process_> run_list = new ArrayList<>();// 运行队列
	public static int auto_process_id;// 进程号自增主键
	private static int degree = 10;// 允许调入内存的作业数
	private static int batch = 1;// 允许同时处理的作业数
	public static int step;// 运行时间
	public static Vector<Object> name = new Vector<>();

	// 设置进程列表的最大处理作业数和允许调入内存的作业数
	public Process_List() {

		auto_process_id = 1;
		step = 0;
	}

	public static int run() {
		int id=-1;
		try {
			Collections.sort(block_list,
					(Comparator<Process_>) Process_Comparetor.class
							.getField(SETTING.block).get(null));
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e1) {
			System.out.println("作业调度算法问题");
		}
		int s = block_list.size();
		/* 若阻塞队列不为空且就绪队列未满，从阻塞队列末尾移动到就绪队列 */
		for (int i = 0; i < s && ready_list.size() < degree; i++) {
			Process_ p = block_list.getFirst();
			block_list.removeFirst();
			Resourse_ request = p.haveRequest();
			// 判断此进程是否需要再分配资源
			if (request != null) {
				/* 若满足银行家算法， */
				// 分配资源判断
				if (Banker_.judge(p, request, ready_list)) {
					p.getAllocation().add(request);
					p.getNeed().sub(request);
					Device_List.resourse.sub(request);
					p.getRequest().removeFirst();
					p.ready();
				} else
					block_list.add(p);// 不满足资源分配条件，加入阻塞队列
			} else {
				p.ready();// 进程加入就绪队列
			}
		}
		try {
			Collections.sort(ready_list,
					(Comparator<Process_>) Process_Comparetor.class
							.getField(SETTING.ready).get(null));
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e1) {
			// TODO
			System.out.println("进程调度算法问题");
		}
		for (int i = 0; i < batch && i < ready_list.size(); i++) {
			Process_ p = ready_list.get(i);
			p.run();
			run_list.add(p);// 将该进程加入运行队列
		}
		for (Process_ e : ready_list) {
			e.setWait();
		}
		for (Process_ e : block_list) {
			e.setWait();
		}
		step++;
		for (int i = run_list.size() - 1; i >= 0; i--) {
			Process_ e = run_list.get(i);
			/* 销毁进程 */
			if (e.getCost() == e.getPc()) {
				id=remove(e.getProcess_id());
				continue;
			}
			if (e.haveRequest() == null)
				e.ready();
			else
				e.block();
		}
		run_list.clear();
		return id;
	}

	public static int add(int pri, String name, int cost,
			LinkedList<Request_> request, Object o) {
		Resourse_ need = new Resourse_();
		for (Request_ e : request) {
			need.add(e.getRequest());
		}
		int id=auto_process_id;
		Process_ p = new Process_(auto_process_id, pri, name, cost, request,
				need);
		block_list.add(p);
		auto_process_id++;
		return id;
	}

	public static int remove(int process_id) {
		for (Process_ e : ready_list) {
			if (e.getProcess_id() == process_id) {
				/* 归还资源 */
				Device_List.resourse.add(e.getAllocation());
				int id=e.getProcess_id();
				ready_list.remove(e);
				return id;
			}
		}
		return -1;
	}

	public static void removeAll() {
		for (Process_ e : ready_list) {
			/* 归还资源 */
			Device_List.resourse.add(e.getAllocation());
			ready_list.remove(e);
			return;
		}
	}

	public static Process_ get(int process_id) {
		for (Process_ e : ready_list)
			if (e.getProcess_id() == process_id)
				return e;
		for (Process_ e : block_list)
			if (e.getProcess_id() == process_id)
				return e;
		return null;
	}

	public static Object[][] gettable() {
		Vector<Vector<Object>> vector1 = new Vector<>();
		Object[][] vector = new Object[100][100];
		for (Process_ e : block_list)
			vector1.add(e.getRow());
		for (Process_ e : ready_list)
			vector1.add(e.getRow());
		for (Process_ e : run_list)
			vector1.add(e.getRow());
		for (int i = 0; i < vector1.size(); i++) {
			vector[i] = vector1.get(i).toArray();
		}
		return vector;
	}
}
