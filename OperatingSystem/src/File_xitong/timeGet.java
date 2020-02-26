package File_xitong;

import java.text.SimpleDateFormat;
import java.util.Date;

public class timeGet {
	public static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	public static String createtime(){
		Date now =new Date();
		return ft.format(now);
	}
}
