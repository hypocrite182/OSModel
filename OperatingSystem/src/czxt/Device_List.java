package czxt;

import java.util.Vector;
import java.util.Map.Entry;


public class Device_List {
	public static Resourse_ resourse=new Resourse_();
	public static Resourse_ total=new Resourse_();
	public static String Device_name[]=new String[]{"设备名","总数量","剩余数量","分配数量"};
	public static Vector<String> Dname=null;
	public Device_List() {
		resourse=new Resourse_();
		total=new Resourse_();
		Dname=new Vector<String>();
		Dname.add("设备名");
        Dname.add("Need");
        Dname.add("Allocation");
	}
	public static boolean add(String name,int count){
		if(count+resourse.getOrDefault(name,0)>=0) {
			resourse.add(name,count);
			total.add(name,count);
			return true;
		}
		return false;
	}
	public static int get(String name){
		return resourse.get(name);
	}
	public static Object[][] gettable(){
		Object tab[][]=new Object[total.size()][];
		int i=0;
		for(Entry<String, Integer> e:total.entrySet()){
			String key=e.getKey();
			int t=e.getValue();
			int t2=resourse.get(key);
			tab[i++]=new Object[]{key,t,t2,t-t2};
		}
		return tab;
	}
}
