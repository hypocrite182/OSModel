package czxt;

import java.util.Comparator;

/*��������*/
public class Process_Comparetor {
	
	/**
	 *���ȼ�����
	 **/
	public static String[] key=new String[]{"���ȼ�����","�����ȷ���","����ҵ����"};
	public static String[] value=new String[]{"PSA","FCFS","SJF"};
	public static Comparator<Process_> PSA=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			return o1.getPriority()-o2.getPriority();
		}
	};
	/**
	 *�����ȷ���
	 **/
	public static Comparator<Process_> FCFS=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			return o1.getProcess_id()-o2.getProcess_id();
		}
	};
	/**
	 *����ҵ����
	 **/
	public static Comparator<Process_> SJF=new Comparator<Process_>() {
		@Override
		public int compare(Process_ o1, Process_ o2) {
			return o1.getCost()-o2.getCost();
		}
	};
	
}
