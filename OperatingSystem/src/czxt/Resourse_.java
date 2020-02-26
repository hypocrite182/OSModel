package czxt;

import java.util.HashMap;

public class Resourse_ extends HashMap<String, Integer> implements Comparable<Resourse_>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Resourse_(Resourse_ o) {
		for(Entry<String, Integer> e:o.entrySet()){
			put(e.getKey(), e.getValue());
		}
	}
	public Resourse_() {
		// TODO
	}
	@Override
	public int compareTo(Resourse_ o) {
		// TODO 
		for(Entry<String, Integer> e:o.entrySet()){
			int value1=getOrDefault(e.getKey(),0);
			int value2=e.getValue();
			if(value1<value2) return -1;
		}
		return 1;
	}
	public void add(Resourse_ o){
		for(Entry<String, Integer> e:o.entrySet()){
			put(e.getKey(), getOrDefault(e.getKey(), 0)+e.getValue());
		}
	}
	public void sub(Resourse_ o){
		for(Entry<String, Integer> e:o.entrySet()){
			put(e.getKey(), getOrDefault(e.getKey(), 0)-e.getValue());
		}
	}
	public void add(String name,int count){
		put(name, getOrDefault(name, 0)+count);
	}
}
