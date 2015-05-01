/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.net.*;
import java.util.ArrayList;

import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in Priority-first order.
 */
public class PriorityBasedSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	
	private Indexer i = null;
	private URL u; 
	private int count,num,index;
	ArrayList<node> l=new ArrayList<node>();
	LinkedQueue<URL> r=new LinkedQueue<URL>();
    LinkedQueue<URL> q=new LinkedQueue<URL>();
    String s;
    MinPQueue mq=new MinPQueue();
    public PriorityBasedSpider (URL u, Indexer i)
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
		int ecount=0;
		int bc=0;
		while(count<limit)
		   {	    
		   try
		   {
			
			u=q.dequeue();
			
			//System.out.println(u);
			/*String [] re=s.split("/");
			for(int i=0;i<re.length;i++)
			{
				bc++;
				//System.out.println(re[i]);
			}*/
			
			URLTextReader in = new URLTextReader(u);
			PageLexer<PageElementInterface> elts = new PageLexer<PageElementInterface>(in, u);
			while (elts.hasNext()) 
			{
				//System.out.println("link: "+u);
				
				if(count<limit)
				{	
				PageElementInterface elt = (PageElementInterface)elts.next();
				
				if (elt instanceof PageHref)
				 {
					 
					
					
					URL t=new URL(elt.toString());
					//isexist function return true if the given value is not present 
					if((r.isexist(t) && (!elt.toString().contains("#"))) || count==0 )
					{
						
					    //System.out.println("sarath");
						q.enqueue(t);
						r.enqueue(t);
						num++;
						count++;
						String s=elt.toString();
						bc=0;
						for(int i=0;i<s.length();i++)
						{
							if(s.charAt(i)=='/')
								bc++;
						}
						node n=new node();
						n.pri=(bc-2);
						n.value=elt.toString();
						if(n.pri!=-2)
						{
							//System.out.println(bc-2);
							mq.enqueue(n);
							ecount++;
						}
						
						//System.out.println("link: "+" "+num+" "+elt+" bcount "+(bc-2));
						
					}
					
					
				 }
				else if(elt instanceof PageWord) 
				{
					String s=u.toString();
					/*for(int i=0;i<s.length();i++)
					{
						if(s.charAt(i)=='/')
							bc++;
					}*/
					
					/*System.out.println("link "+u+" bcount "+(bc-2));*/
					
					//System.out.println("Word : "+elt +"\tlink"+u+" bcount"+(bc-2));
					
					
					//di.insert(elt.toString(), u);
					
				}
				
			
				}
				else
					break;
				
			}
		   }
		   catch(Exception e)
		   {
			   System.out.println("Bad file or URL specification");
		   }
		   }
		   System.out.println(count);
		   //System.out.println("econt "+ecount+" sizearr "+mq.size());
		   
		   //mq.disp();
		  
		   int c=mq.size();
		   
		   for(int i=1;i<=c;i++)
		   {
			   node temp=mq.dequeue();
			   System.out.println("pri "+temp.pri+" value "+temp.value);
			   
		   }
		   
		   
		   
		  
			  
		
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

class node
{
   int pri;
   String value;
   //ArrayList<Object> value  = new ArrayList<Object>();
}
class MinPQueue
{
  node arr[];
  int f,r;


  MinPQueue()
  {
    //Class c=instance.getClass();
    //arr=(E[])Array.newInstance(c,100);
    //arr=(E[])new Comparable[5];
    arr=new node[10000];
    for(int i=0;i<arr.length;i++)
      arr[i]=null;
    f=0;
    r=0;

  }

  public void enqueue(node n)
  {

    if(r==arr.length-1)
    {
      //E [] temp=(E[])new Comparable[arr.length*2];
      //int []temp=new int[arr.length*2];
      node temp[]=new node[arr.length*2];
      for(int i=0;i<arr.length;i++)
        temp[i]=arr[i];

      arr=temp;

    }
    if(f==0&&r==0)
    {
      //System.out.println("fisr");
      arr[++r]=n;

      f++;
     // System.out.println(f+" "+r);
      return;
    }
  //System.out.println (r);
//System.out.println (arr[1].pri);

    arr[++r]=n;
   // System.out.println (arr[1].pri+" "+arr[2].pri);
    check_ins_cond();
   // System.out.println(arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]+" "+arr[5]+" "+arr[6]);

  }

  public void check_ins_cond()
  {
    int t=r;
    int i=r/2;
    while(true)
    {
      //System.out.println ("t:"+t+"i"+i);
      //if(arr[i].compareTo(arr[t])>0)
      if(arr[i].pri>arr[t].pri)
      {
                //swapping takes place
        node temp=arr[t];
        arr[t]=arr[i];
        arr[i]=temp;
        if(i!=1)
        {
           t=i;
           i=i/2;
        }
        else
          break;

      }
      else
        break;
     }
  }

  public int size()
  {
    return r;
  }

  public node dequeue()
  {
    if(is_empty())
    {

      System.out.println("empty");
      return null;
    }

    node ret;
  ret=arr[f];
  if(r==1)
  {
   /*arr[f].value=0;
   arr[f].pri=0;*/
   arr[f]=null;
   r--;
   f=0;
   return  ret;
  }
  else
  arr[f]=arr[r];

  r--;
  check_del_cond();
  return ret;

  }


  public void check_del_cond()
  {


    int t=f;
    int i=2*t;

    while(true)
    {

      if(i>r)
        return;
      if(arr[i+1]==null)
      {
        //if(arr[t].compareTo(arr[i])>0)
        if(arr[t].pri>arr[i].pri)
        {
          node temp=arr[t];
          arr[t]=arr[i];
          arr[i]=temp;
          return;
        }
        return;

      }
      //if(arr[t].compareTo(arr[i])<0)
      if(arr[t].pri<arr[i].pri)
      {

        //if(arr[t].compareTo(arr[i+1])<0)
        if(arr[t].pri<arr[i+1].pri)
        {


          return;
        }

      }


      //if(arr[t].compareTo(arr[i])>0)
      if(arr[t].pri>arr[i].pri)
      {
          //if(arr[t].compareTo(arr[i+1])<0)
          if(arr[t].pri<arr[i+1].pri)
          {

          node temp=arr[t];
          arr[t]=arr[i];
          arr[i]=temp;
          t=i;
          i=2*t;
          continue;
          }

      }
      //if(arr[t].compareTo(arr[i+1])>0)
      if(arr[t].pri>arr[i+1].pri)
        {
           //if(arr[t].compareTo(arr[i])<0)
           if(arr[t].pri<arr[i].pri)
           {

            node temp=arr[t];
            arr[t]=arr[i+1];
            arr[i+1]=temp;
            t=i+1;
            i=2*t;
            continue;

           }


        }
       //if(arr[i].compareTo(arr[i+1])>0)
       if(arr[i].pri>arr[i+1].pri)
        {



          node temp=arr[t];
          arr[t]=arr[i+1];
          arr[i+1]=temp;
          t=i+1;
          i=2*t;
        }
        else
        {

            node temp=arr[t];
            arr[t]=arr[i];
            arr[i]=temp;
            t=i;
            i=2*t;
        }


      

    }
  }


  public node front()
  {
    return arr[f];
  }
  public void disp()
  {
    if(is_empty())
      System.out.println("\nempty");
    else
    {
      System.out.println("\nQueue elements");
      for(int i=1;i<=size();i++)
        System.out.println("pri "+arr[i].pri+"value "+arr[i].value);
    }
  }

  public boolean is_empty()
  {
    return r==0;
  }



}



