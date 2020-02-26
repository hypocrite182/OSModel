package File_xitong;

import java.util.ArrayList;

 

public class File_file {
  private String name ;
  private String type;
  private int size;
  private int pointfirst;//ֵΪ-1ʱ�ļ�Ϊ����ʧ�ܵ��ļ�
  private ArrayList<File_file> filelist;
  private String newtime;
  public File_file(String name,String type,int size)
  {   
	  filelist = new ArrayList<File_file>();
	  this.setName(name);
	  this.setType(type);
	  this.setSize(size);
	  this.setNewtime(timeGet.createtime());
	  pointfirst = spaceDeal.getSpace(size);
  }
  public File_file()
  {
	  
  }
  public void  FileOutput()
  {
	  System.out.println(this.name+" "+this.newtime+"  "+this.size+"  "+this.type);
  }
  public void setList(ArrayList<File_file> filelist)
  {
	  this.filelist = filelist;
  }
  public ArrayList<File_file> getList()
  {
	  return filelist;
  }
  public   void deletFile(File_file f)//ɾ���ռ䣬�ļ���Ϊ��Ч�����Ƕ�����
  {   
	  if(f.pointfirst==-1)
	  {
		  System.out.println("�ļ�������");
		  return ;
	  }
	  spaceDeal.deletSpace(f);
	  f.pointfirst = -1;
	  
	 
  }
  public void SetFile(String name,String type){//�޸��ļ�
		this.name=name;
		this.type=type;
	}
  public void addFile(File_file f)
  {   
	  if(f.pointfirst==-1)
	  {
		  System.out.println("�ļ�������");
		  return;
	  }
	  for(int i=0;i<this.filelist.size();i++)
	  {
		  if(f.getName().equals(this.filelist.get(i).getName()))
		  {
			  if(f.getNewtime().equals(this.filelist.get(i).getNewtime()))
			  {
				  System.out.print("��ͬ�ļ��Ƿ��滻");
				  //todo
				  break;
			  }
			  System.out.println("�ļ�����");
		  }
	  }
	  this.filelist.add(f);
  }
  
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the size
 */
public int getSize() {
	return size;
}
/**
 * @param size the size to set
 */
public void setSize(int size) {
	this.size = size;
}
/**
 * @return the pointfirst
 */
public int getPointfirst() {
	return pointfirst;
}
/**
 * @param pointfirst the pointfirst to set
 */
public void setPointfirst(int pointfirst) {
	this.pointfirst = pointfirst;
}
/**
 * @return the newtime
 */
public String getNewtime() {
	return newtime;
}
/**
 * @param newtime the newtime to set
 */
public void setNewtime(String newtime) {
	this.newtime = newtime;
}
 
}
