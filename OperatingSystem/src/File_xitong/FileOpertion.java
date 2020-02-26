package File_xitong;

import java.util.ArrayList;
 
import javax.swing.tree.DefaultMutableTreeNode;

public class FileOpertion {

public static File_file findFile(String path,File_file f)
{   
	 if(f==null) return null;
	 if(path.equals(""))return null;
	 File_file  file =null;
 
	 if(path.startsWith(f.getName())&&f.getName().endsWith(path))
		 return f;
	 else if(path.startsWith(f.getName()))
	 {
		 path = path.substring(f.getName().length()+1);
	 }
	 ArrayList<File_file> filelist  = f.getList();
	 for(int i =0; i< filelist.size();i++)
	 {  
		 file = filelist.get(i);
		 if(path.startsWith(file.getName()))
		 {
			 if(file.getName().endsWith(path))return file;
			// path = path.substring(file.getName().length()+1);
			 file= findFile(path,file);
			  break;
		 }
		if(i+1>=filelist.size())return null;//�Ҳ����ļ�
	 }
	 
	 return file;
}
public static boolean findFile(String path,File_file f,String name)//�����ļ���,���ĸ�Ŀ¼
{   
	 boolean a = true;
	 if(f==null) return false;
	 
	 File_file  file =null;
 
	 if(path.startsWith(f.getName())&&f.getName().endsWith(path))
	 {
 
		 
		 return false;
	 }
	 if(path.startsWith(f.getName()))
	 {
		 path = path.substring(f.getName().length()+1);
	 }
	 ArrayList<File_file> filelist  = f.getList();
	 for(int i =0; i< filelist.size();i++)
	 {  
		 file = filelist.get(i);
		 if(path.startsWith(file.getName()))
		 {
			 if(file.getName().endsWith(path))
			 {  
				 for(int j=0;j<filelist.size();j++)
				 {
					 if(filelist.get(j).getName().equals(name))
						 return false;
				 }
				 file.setName(name);
				 return true;
			 
			 }
			// path = path.substring(file.getName().length()+1);
			 a = findFile(path,file,name);
			 break;
		 }
	 
	 }
	 
	 return a;
}
public static void buildTree(File_file root,DefaultMutableTreeNode tree)//�ļ���
{  
	if(root==null)return;
	//DefaultMutableTreeNode r = new DefaultMutableTreeNode(root.getName());
	//tree.add(r);
	if(root.getList()==null)return;
	for(int i= 0;i<root.getList().size();i++)
	{
	DefaultMutableTreeNode r = new DefaultMutableTreeNode(root.getList().get(i).getName());
	tree.add(r);
	buildTree(root.getList().get(i),r);
	}
	
}
public static void findFile(String name,File_file f,ArrayList<File_file> filelist)//���Ҵ��������ļ�
{   
	 if(f==null) return ;
	  
	 if(f.getList()==null)return;
	 for(int i =0; i< f.getList().size();i++)
	 {  
		  if(f.getList().get(i).getName().indexOf(name)!=-1)
		  {
			  filelist.add(f.getList().get(i));
			  findFile(name,f.getList().get(i),filelist);
			  continue;
		  }
		  findFile(name,f.getList().get(i),filelist);
	 }
	 
  
}
public static boolean insertFile(String path ,File_file root,File_file file)//���ļ�ϵͳ����ָ��·���ļ������ڸ��ļ����������ļ�
{   
 
	 boolean fa = false;
	 if(path.startsWith(root.getName())&&root.getName().endsWith(path))
	 {
		 
		  root.setSize(root.getSize()+file.getSize());
		  for(int i=0;i<root.getList().size();i++)
		  {
			  if(root.getList().get(i).getName().equals(file.getName()))
				  return false;
		  }
		  root.getList().add(file);
		  return true;
	 }
	 if(path.startsWith(root.getName()))
	 {   
		 System.out.print(root.getSize());
		 root.setSize((root.getSize()+file.getSize()));
		 int  a = root.getSize();
		 System.out.print(a);
		 path = path.substring(root.getName().length()+1);		 
	 }
	 
	 
	 for(int i=0;i<root.getList().size();i++)
	 {
		 if(path.startsWith(root.getList().get(i).getName()))
		 {
			 if(root.getList().get(i).getName().endsWith(path))
			 {
				 
			      for(int j = 0;j<root.getList().get(i).getList().size();j++)
			      {
			    	  System.out.print(root.getList().get(i).getList().get(j).getName());
			    	  if(root.getList().get(i).getList().get(j).getName().equals(file.getName()))
			    	  {  
			    		  
			    		  return false;
			    	  }
			      }
				  root.getList().get(i).getList().add(file);
				  root.getList().get(i).setSize(root.getList().get(i).getSize()+file.getSize());
				  return true;
			 }
			// path = path.substring(root.getList().get(i).getName().length()+1);
			 fa = insertFile(path,root.getList().get(i),file);
			 break;
		 }
	 }
	return fa;
}
public static boolean deleteFile(String path,File_file f,int size)//ɾ����Ŀ¼�µ�ָ��·�����ļ�
{   
	 boolean flag = false;
	 if(f==null)return false;
	 if(path.startsWith(f.getName()))
	 {
		 if(f.getName().endsWith(path))
		 {
			 f.deletFile(f);
			 
			 for(int i = 0;i<f.getList().size();i++)
				 f.getList().remove(i);
			 f.getList().clear();
			 f = null;
			 return true;
		 }
		 f.setSize(f.getSize()-size);
		 path = path.substring(f.getName().length()+1);
	 }
	 ArrayList<File_file> filelist  = f.getList();
	 File_file  file =null;
	 
	 for(int i =0; i< filelist.size();i++)
	 {  
		 file = filelist.get(i);
		 if(path.startsWith(file.getName()))
		 {
			 if(file.getName().endsWith(path))
			 {    
				// filelist.get(i).setSize(filelist.get(i).getSize()-size);
				  file.deletFile(file);//�ռ�ɾ��
				  filelist.remove(i);//����ɾ��
				  
				  return true;
			 }
			 path = path.substring(file.getName().length()+1);
			 filelist.get(i).setSize(filelist.get(i).getSize()-size);
			 flag =  deleteFile(path,file,size);
			 
			 break;
			 
			 
		 }
		 
	 }
	 return flag;
}
public static void findFileofType(File_file root,String type,ArrayList<File_file> filelist)//�����ļ�ϵͳ��ĳ�����͵������ļ�
{
	   
	  if(root.getName().endsWith(type))
		  filelist.add(root);
	  if(root.getList().size()>0)
		  for(int i = 0;i<root.getList().size();i++)
			  findFileofType(root.getList().get(i),type,filelist);
	  
}
public static void findFileNature(File_file f,String a[][],int pos)//�����ļ����ļ��¼�������
{
	if(f==null)return;
	a[pos][0] =f.getName();
	a[pos][1] =f.getType();
	a[pos][2] = f.getSize()+"";
	a[pos][3] = f.getNewtime();
	pos++;
	for(int i=0;i<f.getList().size();i++)
	{		
	//findFileNature(f.getList().get(i),a,pos);
		a[pos][0] =f.getList().get(i).getName();
		a[pos][1] =f.getList().get(i).getType();
		a[pos][2] = f.getList().get(i).getSize()+"";
		a[pos][3] = f.getList().get(i).getNewtime();
	pos++;
	}
	return;
//	findFileg
}
public static void findFileNatureall(File_file f,String a[][],int pos)//��ѯ�ļ��Լ��ļ������м����ļ�
{
	if(f==null)return;
	a[pos][0] =f.getName();
	a[pos][1] =f.getType();
	a[pos][2] = f.getSize()+"";
	a[pos][3] = f.getNewtime();
	pos++;
	for(int i=0;i<f.getList().size();i++)
	{		
	findFileNature(f.getList().get(i),a,pos);
	pos++;
	}
	return;
//	findFileg
}
}
