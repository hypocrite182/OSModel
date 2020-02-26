package czxt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SETTING {
	public static String block="PSA";//封锁队列调度算法
	public static String ready="PSA";//就绪队列调度算法
	public static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	public static String createtime(){
		Date now =new Date();
		return ft.format(now);
	}
}
