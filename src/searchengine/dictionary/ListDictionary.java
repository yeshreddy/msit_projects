package searchengine.dictionary;
import java.lang.reflect.*;
import java.util.ArrayList;
public class ListDictionary implements DictionaryInterface 
{
	
	
	ListNode head, tail;
	//private K[] arr;
	int size;
	
	
	public ListDictionary()
	{
		head=null;
		tail=null;
		size=0;
	}

	@Override
	public String[] getKeys() 
	{
		if(size!=0)
		{
		
		Class c = head.key.getClass();
		String[] array = (String[])Array.newInstance(c, size);
		ListNode temp1=new ListNode();
		temp1=head;
		int i=0;
		while(temp1!=null)
		{
			 array[i]=temp1.key;
			temp1=temp1.next;
			i++;
		}
		return array;
		}
		else
			return null;
			
		
		
	}

	@Override
	public ArrayList<Object> getValue(String str) 
	{
		ListNode temp1=new ListNode();
		temp1=head;
		while(temp1!=null)
		{
			if(temp1.key.equals(str))
			return temp1.value;
			temp1=temp1.next;
		}
		return null;
	}

	@Override
	public void insert(String key, Object value) 
	{
		
		
		ListNode t=new ListNode();
		t.key=key;
		t.value.add(value);
		t.freq.add(1);
		ListNode temp1=new ListNode();
		temp1=head;
		if(head==null)
		{
			head=t;
			tail=head;
			size++;
		}
		else
		{
			while(temp1!=null)
			{
				if(temp1.key.equals(key))
				{
					if(!temp1.value.contains(value))
					{
						temp1.value.add(value);
					
						temp1.freq.add(1);
					}
					else
					{
						int ind=temp1.value.indexOf(value);
						int freq=temp1.freq.get(ind);
						temp1.freq.set(ind, (freq+1));
					}
					return;
				}
				temp1=temp1.next;
				
			}
			tail.next=t;
			tail=t;
			size++;
		}
		
		
		
		
		
		
	}

	@Override
	public void remove(String key) 
	{
		
		if(size==0)
		{
			System.out.println("no elements to delete(list empty)");
			return;
		}
		
		ListNode tmp = head;
		
		
		if(tmp.key.equals(key))
		{
			head = head.next;
			size--;
			return;
			
		}
		while(tmp.next != null)
		{
			
			if(tmp.next.key.equals(key))
			{
				if(tmp.next == tail)
					tail = tmp;
				tmp.next = tmp.next.next;
				size--;
			}			
			tmp = tmp.next;
			if(tmp == null )
			{
				
				return;
			}
			}
		
	  }
		
	
	
	

}
