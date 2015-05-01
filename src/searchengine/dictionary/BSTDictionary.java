package searchengine.dictionary;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BSTDictionary  implements DictionaryInterface 
{
	int size;
	String[] keys;
	int c;
	private BSTNODE root;//=new BSTNODE<K,V>();
	public BSTDictionary()
	{
		size=0;
		root=null;
	}
	@SuppressWarnings("unchecked")
	public String[] getKeys() 
	{

		if(root==null)
		return null;
		
		Class i=root.key.getClass();
		
		//System.out.println("Array size created "+ size);
		keys=(String[])Array.newInstance(i, size);
		c=0;
		inOrder(root);
		return keys;
		
	}

	@Override
	public ArrayList<Object> getValue(String str) 
	{
		BSTNODE t=root;
		if(t==null)
		{
			return null;
		}
		while(t!=null)
		{
			if(str.compareTo(t.key)==0)
				return t.value;
			if(str.compareTo(t.key)<0)
				t=t.left;
			else if(str.compareTo(t.key)>0)
				t=t.right;
				
		}
		return null;
		
	}

	@Override
	public void insert(String key, Object value) 
	{
		BSTNODE temp=new BSTNODE();
		temp.key=key;
		temp.value.add(value);
		temp.freq.add(1);
		
		if(root==null)
		{
			root=temp;
			root.parent=null;
		
			size++;
		}
		else
		{
			BSTNODE curr=root;
			/*if(key.compareTo(curr.key)==0)
			{
				curr.value=value;
				//size++;
				return;
			}*/
			while(true)
			{
				    if(key.compareTo(curr.key)==0)
				    {
				    	if(!curr.value.contains(value))
				    	{
				    		curr.value.add(value);
				    		curr.freq.add(1);
				    	}
				    	else
				    	{
				    		int ind=curr.value.indexOf(value);
				    		int freq=curr.freq.get(ind);
				    		curr.freq.set(ind,(freq+1));
				    		
				    	}
				    	return;
				    }
				    
		           	if(key.compareTo(curr.key)<0)
		           	{
		           		////System.out.println("passedkey  "+key+"  "+"currkey"+curr.key);
		           	//	System.out.println("in left"+curr.key.compareTo(key));
		           		if(curr.left==null)
		           		{
		           			temp.parent=curr;
		           			curr.left=temp;
		           			size++;
		           			return;
		           		}
		           		curr=curr.left;
		           	}
		           	else if(key.compareTo(curr.key)>0)
		           	{
		           		//System.out.println("in right"+curr.key.compareTo(key));
		           		if(curr.right==null)
		           		{
		           			
		           			temp.parent=curr;
		           			curr.right=temp;
		           			size++;
		           			return;
		           		}
		           		else
		           			curr=curr.right;
		           	}
			}
		}
		
						
		
	}

	@Override
	public void remove(String key) 
	{
		/*int keyfound=0;
		String side="";
		if(root==null)
		{
			System.out.println("empty");
			return;
		}
		
		BSTNODE<K,V> curr=root;
		if(curr.left==null && curr.right==null)
		{
			System.out.println("root");
			if(curr.key.equals(key))
			{	
			  root=null;
			  size--;
			  //System.out.println("root deleted");
			  return;
			}
			else
				return;
		}
		while(curr!=null)
		{
			if(key.compareTo(curr.key)==0)
			{
				keyfound=1;
				//System.out.println("keyfound"+curr.key);
				break;
			}
			if(key.compareTo(curr.key)<0)
			{
				side="left";
				curr=curr.left;
			}
			else if(key.compareTo(curr.key)>0)
			{
				side="right";
				curr=curr.right;
				
			}
		}
		if(keyfound==0)
			return;
		
		
		// the found node is leaf
		if(curr.left==null&&curr.right==null)
		{
			//System.out.println("leaf node");
			if(side.equals("left"))
			{	
				//System.out.println("left leaf node deleted");
				curr.parent.left=null;
				size--;
				return;
			}
			else if(side.equals("right"))
			{
				//System.out.println("right leaf node delelted");
				curr.parent.right=null;
				size--;
				return;
			}
			
				
			
		}
		if(curr.left==null &&curr.right!=null||curr.left!=null&&curr.right==null)
		{
			if(side.equals("left"))
			{
				curr.parent.left=curr.left;
				curr.left.parent=curr.parent;
				size--;
				return;
			}
			else if(side.equals("right"))
			{
				curr.parent.right=curr.right;
				curr.right.parent=curr.parent;
				size--;
				return;
			}
			else
			{
				if(curr.left!=null&&curr.parent==null)
				{
					root=curr.left;
					size--;
					return;
				}
				else 
				{
					root=curr.right;
					size--;
					return;
				}
					
			}
		}
		
		if(curr.left!=null&&curr.right!=null)
		{
			int flag=0;
			BSTNODE<K,V> succ=curr;
			succ=curr.right;
			if(succ.left!=null)
			{
				while(succ.left!=null)
				{
					succ=succ.left;
					flag=1;
				}
			}
			
			
			curr.key=succ.key;
			curr.value=succ.value;
			if(flag==1)
			succ.parent.left=null;
			else
			succ.parent.right=null;
			size--;
			return;
		}*/
	}
	public void inOrder(BSTNODE rt)
	{
		
		
		   if(rt != null)      
		    { 
		      inOrder(rt.left);          		
		        keys[c]=rt.key;
		       c++;
		      inOrder(rt.right);          
		     } 
		 

	}

}


class BSTNODE 
{
	String key;
	ArrayList<Object> value  = new ArrayList<Object>();
	ArrayList<Integer> freq  = new ArrayList<Integer>();
	BSTNODE left,right,parent;
	
	

}

