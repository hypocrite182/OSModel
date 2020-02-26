package czxt;

import java.util.ArrayList;
import java.util.LinkedList;



public class Banker_ {

	public static boolean judge(Process_ block,Resourse_ request,LinkedList<Process_> ready_list){
		Resourse_ work=new Resourse_(Device_List.resourse);
		if(block.getNeed().compareTo(request)==-1) return false;
		work.sub(request);
		ArrayList<Process_Resourse> ap=new ArrayList<>();
		Process_Resourse p=new Process_Resourse(block.getNeed(), block.getAllocation());
		p.allocation.add(request);
		p.need.sub(request);
		ap.add(p);
		for(Process_ e:ready_list) ap.add(new Process_Resourse(e.getNeed(),e.getAllocation()));
		boolean flag=true;
		while(flag){
			flag=false;
			for(int i=ap.size()-1;i>=0;i--){
				Process_Resourse e=ap.get(i);
				if(work.compareTo(e.need)>0){
					flag=true;
					work.add(e.allocation);
					ap.remove(i);
				}
			}
		}
		return ap.size()==0;
	}
}
