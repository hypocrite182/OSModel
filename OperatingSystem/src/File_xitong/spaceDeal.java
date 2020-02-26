package File_xitong;

public class spaceDeal {
	 public static Integer bit[][];
	 public static int row;
	 public static int col;
	 public static int usedcount;
	 public static int pt[];
	 @SuppressWarnings("static-access")
	public spaceDeal(int row,int col)
	 {
		 bit = new Integer[row][col];
		 pt = new int[row*col];
		 for(int i = 0;i<row;i++)
		   for(int j = 0;j<col;j++)
		   {
			   bit[i][j] = 0;
		   }
		 this.row = row;
		 this.col = col;
	 }
    public static int  getSpace(int size)
    {   
    	 int sign = 0;
    	 int  position = -1;
    	 int preposition = -1;
    	 if((row*col-usedcount)<size)
    		return -1;
    	 for(int i=0;i<row;i++)
    		 for(int j = 0;j<col;j++)
    		 {   
    			 if(bit[i][j]==0)
    			 {  
    				 
    				 if(sign==0)
    				 {
    					position = col*i+j; 
    					preposition = col*i+j;
    					bit[i][j] = 1;
       				    size--;
       				    if(size==0)
       				    {    
       				    	pt[col*i+j] = -1;
       				    	return position;
       				    }
       				    sign++;
       				    continue;
    				 }
    				 pt[preposition] = col*i+j;
    				 preposition  = col*i+j;
    				 bit[i][j] = 1;
    				 size--;
    				 if(size==0)
    				 {   
    					 pt[col*i+j]=-1;
    					 return position;
    				 }
    				 sign++;
    			 }
    			  
    		 }
		return -1;
    	 
    }
    public static boolean deletSpace(File_file f)
    {   
    	int position = f.getPointfirst();
    	while(position!=-1)
    	{
    	   bit[position/col][position%col] = 0;
    	   position = pt[position];
    	}
    	 
        if(f.getList().size()>0)
        for(int i=0;i<f.getList().size();i++)
            deletSpace(f.getList().get(i));
    	return true;
    }
}
