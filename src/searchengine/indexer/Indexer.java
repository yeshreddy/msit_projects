/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */ 

package searchengine.indexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import searchengine.dictionary.AVLDictionary;
import searchengine.dictionary.BSTDictionary;
import searchengine.dictionary.DictionaryInterface;
import searchengine.dictionary.HashDictionary;
import searchengine.dictionary.ListDictionary;
import searchengine.dictionary.MyHashDictionary;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageWord;


/**
 * Web-indexing objects.  This class implements the Indexer interface
 * using a list-based index structure.

A Hash Map based implementation of Indexing 

 */
public class Indexer implements IndexerInterface,Serializable
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	/** The constructor for ListWebIndex.
	 */

	// Index Structure 
	DictionaryInterface index;
	MyHashDictionary mh=new MyHashDictionary();
	// This is for calculating the term frequency
	HashMap<?,?> wordFrequency;

	public Indexer(String mode)
	{
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students

		if (mode.equals("hash")) 
			index = new HashDictionary();
		else if(mode.equals("list"))
			index = new ListDictionary();
		else if(mode.equals("myhash"))
			index = new MyHashDictionary();
		else if(mode.equals("bst"))
			index = new BSTDictionary();
		else if(mode.equals("avl"))
			index = new AVLDictionary();
	}

	/** Add the given web page to the index.
	 *
	 * @param url The web page to add to the index
	 * @param keywords The keywords that are in the web page
	 * @param links The hyperlinks that are in the web page
	 */
	public void addPage(URL url, String keywords)	
	{
	    ////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  gh
	    ///////////////////////////////////////////////////////////////////
		
		
		
		
		
		mh.insert(keywords, url);
		
		
		
	}

	/** Produce a printable representation of the index.
	 *
	 * @return a String representation of the index structure
	 */
	public String toString()
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		return "You dont need to implement it\n";
	}

	/** Retrieve all of the web pages that contain the given keyword.
	 *
	 * @param keyword The keyword to search on
	 * @return An iterator of the web pages that match.
	 */
	public ObjectIterator<?>  retrievePages(PageWord keyword)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		
		//System.out.println(index.getValue(keyword));
		//System.out.println("retriving starts"+keyword.toString());
		Vector<Object> vec = new Vector<Object>();
		ArrayList<Object> valu  = new ArrayList<Object>();
		
		String keywor=keyword.toString();
                
		valu=mh.getValue(keywor);
                
		
		if(valu==null)
                {  
                    System.out.println("null values");
                    return null;
                }
		for(int i=0;i<valu.size();i++)
		{
			
			vec.add(valu.get(i));
			//System.out.println(valu.get(i));
		}
		
		
		
		/*String k=keyword.toString();
		System.out.println("hai"+mh.getValue(k));
		
*/
		
		
		//return  null;
                // returnning objectiterator after converting from arraylist to vector
		return new ObjectIterator<Object>(vec);
		
		
		
	}

	/** Retrieve all of the web pages that contain any of the given keywords.
	 *	
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 * Calculating the Intersection of the pages here itself
	 **/
	public ObjectIterator<vecnod> retrievePages(ObjectIterator<?> keywords)
	{
		
		
		Vector<vecnod> rev=new Vector<vecnod>();
		Vector<String> te = (Vector<String>) keywords.returnVec();
		
                // 
		//te=(Vector<String>) keywords.returnVec();
		//System.out.println(te.size());	
		
		ObjectIterator<Object> itrr = null;
		
                for(int i=0;i<te.size();i++)
		{
			//vecnod temp=new vecnod();
			
			PageWord p=new PageWord(te.get(i));
                        //System.out.println("page word is "+p+"and it's size is "+p.toString().length());
			String s = te.get(i);
			
			itrr=(ObjectIterator<Object>)this.retrievePages(p);
			
                        if(itrr==null)	continue;
			
                        Vector<Object> tem = new Vector<Object>();
			
			tem=(Vector<Object>) itrr.returnVec();
			
			ArrayList<Integer> fre=mh.getfreq(s);
			//System.out.println("mh freq"+mh.getfreq(s));
			int j=0;
			while(j<fre.size())
			{
				vecnod temp=new vecnod();
				
				temp.url=tem.get(j);
				//System.out.println(p.toString());
				//System.out.println(tem.get(j));
				temp.freq=fre.get(j);
				rev.add(temp);
				j++;
				
				
			}
			
			
			//System.out.println(te.get(i));
		}
		
		return new ObjectIterator<vecnod>(new Vector<vecnod>(rev));
		//ObjectIterator<Vector> itr;
		
		//return null;
	}

	/** Save the index to a file.
	 *
	 * @param stream The stream to write the index
	 */
	public void save(FileOutputStream stream) throws IOException
	{
		
		MyHashDictionary obj = new MyHashDictionary();
		obj = (MyHashDictionary) mh;
		ObjectOutputStream os=new ObjectOutputStream(stream);
                //System.out.println("currently saving mh size "+obj.getKeys().length);
		os.writeObject(obj);
		os.close();
		
		/*PrintStream p = new PrintStream(stream);
        String keys[] = index.getKeys();
        int i = 0;
        while(i<keys.length)
        {
            p.println(keys[i]+"\t"+index.getValue(keys[i++]));
        }*/
	}

	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */
	public void restore(FileInputStream stream) throws IOException
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		try 
		{
			ObjectInputStream is=new ObjectInputStream(new FileInputStream("foo.txt"));
			
			
			try {
				mh=(MyHashDictionary) is.readObject();
				 //System.out.println("mh size "+mh.getKeys().length);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}

	/* Remove Page method not implemented right now
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}
	
	public void disp()
	{
		System.out.println("Displaying all the contents");

		String[] keys = mh.getKeys();
		
		int i=0;
		while(i<keys.length)
		{
			System.out.print(i+1);
			System.out.println(") " +keys[i] +", "+mh.getValue(keys[i]));
			i++;
		}
		
	}
	
	
};



