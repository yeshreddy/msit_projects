package searchengine.dictionary;

import java.io.Serializable;
import java.util.ArrayList;


public class MyHashDictionary implements DictionaryInterface, Serializable {

	ListNode temporary=new ListNode();
	ListNode htable[];
	int hashval,size;
	
	
	public MyHashDictionary()
	{
		
		size=0;
		htable=new ListNode[10000];
		for(int i=0;i<10000;i++)
		{
			htable[i]=null;
		}
	    
	}
	
	@Override
	public String[] getKeys() {
		try
		{
		
		//K keys;
		String [] retarr;
		int i=0;
		//ListNode<K,V> temp=new ListNode<K,V>();
		
		for(i=0;i<10000;i++)
		{
			if(htable[i]!=null)
				break;
		}
			
		retarr=new String[size];
		int j=0;
		for( i=0;i<10000;i++)
		{
			if(htable[i]!=null)
			{
				ListNode temp=new ListNode();
				temp=htable[i];
				while(temp!=null)
				{
					retarr[j]=temp.key;
					j++;
					temp=temp.next;
				}
			}
			
		}
		return retarr;
		}
		catch(Exception e){System.out.println("empty");}
		return null;
	}

	@Override
	public ArrayList<Object> getValue(String str) {
		hashval=hashfunc(str);
		ListNode temp;
		temp=htable[hashval];
		while(temp!=null)
		{
		     if(temp.key.compareTo(str)==0)
		     {
		    	 //System.out.println("frequency"+temp.freq);
		    	 return temp.value;
		     }
		     temp=temp.next;
		
		}
		
		return null;
	}
	public ArrayList<Integer> getfreq(String str) {
		hashval=hashfunc(str);
		ListNode temp;
		temp=htable[hashval];
		while(temp!=null)
		{
		     if(temp.key.compareTo(str)==0)
		     {
		    	 //System.out.println("frequency"+temp.freq);
		    	 return temp.freq;
		     }
		     temp=temp.next;
		
		}
		
		return null;
	}
	
	@Override
	public void insert(String key, Object value) 
	{
		ListNode temp=new ListNode();
		ListNode  t=new ListNode();
		temp.key=key;
		temp.value.add(value);
		temp.freq.add(1);
		hashval=hashfunc(key);
		//System.out.println("hash code  "+hashfunc(key)+ " key "+key+" value"+value);
		if(htable[hashval]==null)
		{
			htable[hashval]=temp;
			size++;
		}
		else
		{
			
			t=htable[hashval];
			
			if(t.next==null)
			{
					if(t.key.equals(key))
					{
						//System.out.println("keys are equal"+" "+t.value.contains(value));
						if(!t.value.contains(value)) 
						{
							t.value.add(value);
							t.freq.add(1);
						}
						else 
						{
							int ind = t.value.indexOf(value);
							int freq = t.freq.get(ind);
							t.freq.set(ind,(freq+1));
						}
						return;
					}
					t.next=temp;
					size++;
					return;
			}
			if(t.key.equals(key))
			{
				//System.out.println("keys are equal"+" "+t.value.contains(value));
				if(!t.value.contains(value)) 
				{
					t.value.add(value);
					t.freq.add(1);
				}
				else 
				{
					int ind = t.value.indexOf(value);
					int freq = t.freq.get(ind);
					t.freq.set(ind,(freq+1));
				}
				return;
			}
			
			while(t.next!=null)
			{
				if(t.next.key.equals(key))
				{
					//System.out.println("keys are equal"+" "+t.value.contains(value));
					if(!t.next.value.contains(value)) 
					{
						t.next.value.add(value);
						t.next.freq.add(1);
						
					}
					else 
					{
						int ind = t.next.value.indexOf(value);
						int freq = t.next.freq.get(ind);
						t.next.freq.set(ind,(freq+1));
					}
					return;
					
				}
				t=t.next;
			}
			t.next=temp;
			size++;
		}
	}

	
	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub

	}
	public int hashfunc(String k)
	{
		Object obj = k;
        String str = (String)obj;
        int hash = 0;
        for(int i=0;i<str.length();i++)
            hash = hash*31 + str.charAt(i);
            if(hash<0)
            hash*=-1;
            //System.out.println("hash code"+ (hash%10000)+ "key "+k);
            return hash%10000;
	}
}
@SuppressWarnings("serial")
class ListNode implements Serializable
{
	String key;
	ArrayList<Object> value  = new ArrayList<Object>();
	ArrayList<Integer> freq  = new ArrayList<Integer>();
	ListNode next;
	ListNode() {}
}

