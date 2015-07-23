/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 12-10-2009
 */ 


package searchengine.search;


import java.util.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import searchengine.dictionary.ObjectIterator;
import searchengine.indexer.Indexer;
import searchengine.indexer.vecnod;



/**
 * The user interface for the index structure.
 *
 * This class provides a main program that allows users to search a web
 * site for keywords.  It essentially uses the index structure generated
 * by WebIndex or ListWebIndex, depending on parameters, to do this.
 *
 * To run this, type the following:
 *
 *    % java SearchDriver indexfile list|custom keyword1 [keyword2] [keyword3] ...
 *
 * where indexfile is a file containing a saved index and list or custom indicates index structure.
 *
 */
public class SearchDriver
{
    @SuppressWarnings({ "unchecked", "unchecked" })
	public static void main(String [] args)
        {
             Vector<String> v=new Vector<String>();
        DataInputStream inn;
        PrintStream out;
        Indexer w = new Indexer("myhash");
        try{
		    FileInputStream indexSource=new FileInputStream("foo.txt");
		    w.restore(indexSource);
                    //w.disp();
	    }
	catch(IOException e){
            System.out.println(e.toString());
	 }
     try
     {
    	 ServerSocket serv=new ServerSocket(4402);
         System.out.println(serv.getInetAddress());
	    System.out.println("server started");
    	while(true)
    	{
    		 
    	Socket client=serv.accept();
         System.out.println("Just connected to "
                  + client.getRemoteSocketAddress());
          
    	inn = new DataInputStream(client.getInputStream());
    	
    	System.out.println("client IP : "+client.getInetAddress());
		String ob=inn.readLine();
                System.out.println("ob is "+ob);
		String[] h=ob.split(" ");
                for(String i:h)
                {
                    v.addElement(i.toLowerCase());
                }
		
    	
		
		// Take care to use the right usage of the Index structure
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students
		
		//System.out.println(perform);    
		System.out.println("search request for : "+v.toString());
		
		
		         //w.disp();
		
		ObjectIterator<vecnod> i= w.retrievePages(new ObjectIterator<String>(v));
              
		Vector<vecnod> no_dup_vec=new Vector<vecnod>();
		Vector<vecnod> dup_vec=new Vector<vecnod>();
		
		int argcount=v.size();
               
		v.clear();
                
		if(i==null)
		{
                    System.out.println("Search complete.  0  hits found.");
                    return;
                }
                vecnod temp=new vecnod();
                System.out.println("Search results:");
                
		while(i.hasNext())
		{
                          
		  temp=i.next();
                  String s=temp.url.toString();
                   
		   
		  int bc=1;
		  for(int j=0;j<s.length();j++)
		  {
		     if(s.charAt(j)=='/')
		     bc++;
	          }
		  temp.freq=temp.freq*100*1/(bc-2);// ressigning rank
		  System.out.println("URL is: "+s+" Rank is: "+temp.freq);	
                }
        }
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
 }
 public String Play( String input)
 {
     Vector<String> v=new Vector<String>();
        
        String output="";
        Indexer w = new Indexer("myhash");
        try{
		    FileInputStream indexSource=new FileInputStream("foo.txt");
		    w.restore(indexSource);
                    //w.disp();
	    }
	catch(IOException e){
            System.out.println(e.toString());
	 }
     try
     {
    	
             
    	
    		 
    	
    	
    	
		String ob=input;
                System.out.println("ob is "+ob);
		String[] h=ob.split(" ");
                for(String i:h)
                {
                    v.addElement(i.toLowerCase());
                }
		
    	
		
		// Take care to use the right usage of the Index structure
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students
		
		//System.out.println(perform);    
		System.out.println("search request for : "+v.toString());
		
		
		         //w.disp();
		
		ObjectIterator<vecnod> i= w.retrievePages(new ObjectIterator<String>(v));
              
		Vector<vecnod> no_dup_vec=new Vector<vecnod>();
		Vector<vecnod> dup_vec=new Vector<vecnod>();
		
		int argcount=v.size();
               
		v.clear();
                
		if(i==null)
		{
                    System.out.println("Search complete.  0  hits found.");
                    return "";
                }
                vecnod temp=new vecnod();
                System.out.println("Search results:");
               
		while(i.hasNext())
		{
                          
		  temp=i.next();
                  String s=temp.url.toString();
                   
		   
		  int bc=1;
		  for(int j=0;j<s.length();j++)
		  {
		     if(s.charAt(j)=='/')
		     bc++;
	          }
		  temp.freq=temp.freq*100*1/(bc-2);// ressigning rank
		  //System.out.println("URL is: "+s+" Rank is: "+temp.freq);
                  output=output+s+"\n";
                }
        
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     return output;
 }
}
			
			
			
        		
     
    
                
