package czxt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SETTING {
	public static String block="PSA";//�������е����㷨
	public static String ready="PSA";//�������е����㷨
	public static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	public static String createtime(){
		Date now =new Date();
		return ft.format(now);
	}
}
