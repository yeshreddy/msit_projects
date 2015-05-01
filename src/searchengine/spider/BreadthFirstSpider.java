/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.net.*;

import searchengine.dictionary.DictionaryInterface;
import searchengine.dictionary.MyHashDictionary;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageImg;
import searchengine.element.PageNum;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in breadth-first order.
 */
public class BreadthFirstSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u; 
	private int count,num,index;
	DictionaryInterface di=new  MyHashDictionary();
	LinkedQueue<URL> r=new LinkedQueue<URL>();
            LinkedQueue<URL> q=new LinkedQueue<URL>();
    String s;
	public BreadthFirstSpider (URL u, Indexer i) 
	{
		
		this.u = u;
		this.i = i;
		count=0;
		num=0;
		
		q.enqueue(u);
		r.enqueue(u);
		

	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 */
	public Indexer crawl (int limit) 
	{
		
	   while(count<limit)
	   {	    
	   try
	   {
		
		u=q.dequeue();
		
		count++;
		URLTextReader in = new URLTextReader(u);
		PageLexer<PageElementInterface> elts = new PageLexer<PageElementInterface>(in, u);
		while (elts.hasNext()) 
		{
			
			if(true)
			{	
				
			PageElementInterface elt = (PageElementInterface)elts.next();
			
			if (elt instanceof PageHref)
			 {
				 
				
					
				URL t=new URL(elt.toString());
				//isexist function return true if the given value is not present 
				if((r.isexist(t) && (!elt.toString().contains("#"))) || count==1 )
				{
					//ystem.out.println(u);
				    //System.out.println("sarath");
					q.enqueue(t);
					r.enqueue(t);
					num++;
					
					//System.out.println("link: "+" "+num+" "+elt);
				}
				
				
			 }
			else if(elt instanceof PageWord ) 
			{
				//System.out.println(u);
				//System.out.println("Word : "+elt +"\t"+u);
				//di.insert(elt.toString(), u);
				if(!(elt.toString().compareTo("  ")==0))
					//System.out.println(u+" "+elt.toString());
				i.addPage(u,elt.toString());
				
			}
			
		
			}
			else
				break;
			
			
		
			
		}
		//System.out.println(u);
	   }
	   catch(Exception e)
	   {
		   //System.out.println("Bad file or URL specification");
	   }
	   }
	   //System.out.println(count);
	   
	   return i;
	   
	}

	/** Crawl the web, up to the default number of web pages.
	 */
	public Indexer  crawl() 
	{
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: "+u.toString());
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;
	
	
	

}

class LinearNode<T>
{
   private LinearNode<T> next;
   private T element;
   private int pri;


   public LinearNode()
   {
      next = null;
      element = null;
   }


   public LinearNode (T elem)
   {
      next = null;
      element = elem;
   }


   public LinearNode<T> getNext()
   {
      return next;
   }


   public void setNext (LinearNode<T> node)
   {
      next = node;
   }


   public T getElement()
   {
      return element;
   }


   public void setElement (T elem)
   {
      element = elem;
   }
}

class LinkedQueue<T> implements QueueADT<T>
{
   private int count;
   private LinearNode<T> front, rear;


   public LinkedQueue()
   {
      count = 0;
      front = rear = null;
   }


   public void enqueue (T element)
   {
      LinearNode<T> node = new LinearNode<T>(element);

      if (isEmpty())
         front = node;
      else
         rear.setNext (node);

      rear = node;
      count++;
   }


   public T dequeue()
   {

      if (isEmpty())
        {

          return null;
        }



      T result = front.getElement();
      front = front.getNext();
      count--;

      if (isEmpty())
         rear = null;

      return result;

   }


   public T first()
   {
    if (isEmpty())
    {

         return null;
    }
    else
      return front.getElement();
   }


   public boolean isEmpty()
   {
    return count==0;
}


   public int size()
   {
    return count;
}


   public String toString()
   {
    String strin="{";
    LinearNode<T> temp = new LinearNode<T>();
    temp=front;
    while(temp!=null)
    {
      strin+=temp.getElement();
      temp=temp.getNext();
    }
    strin+="}";
    return strin;


  }
   
   public boolean isexist(T element)
   {
	   
	   if (isEmpty())
	    {

	         return true;
	    }

       LinearNode<T> temp = new LinearNode<T>();
       temp=front;

       while(temp!=null)
       {
         //T result = temp.getElement();
         //System.out.println(element.toString());
  	   	 //System.out.println(temp.getElement().toString());
  	   	 //System.out.println((element.toString()).equals(temp.getElement().toString()));
        // System.out.println("re to st "+result.toString());
         //System.out.println("ele to st "+element.toString());
         if((element.toString()).equals(temp.getElement().toString()))
         {
           
           return false;
         }

         temp=temp.getNext();
       }

     
     return true;
   }
}
interface QueueADT<T>
{
   //  Adds one element to the rear of the queue
   public void enqueue (T element);

   //  Removes and returns the element at the front of the queue
   public T dequeue();

   //  Returns without removing the element at the front of the queue
   public T first();

   //  Returns true if the queue contains no elements
   public boolean isEmpty();

   //  Returns the number of elements in the queue
   public int size();

   //  Returns a string representation of the queue
   public String toString();
}
