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


import java.io.*;
import java.util.Scanner;
import java.net.URL;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;


/** A user interface for web-crawling objects.
 * Usage : java CrawlerDriver url saveFile [list|custom] [limit]
 */
public class CrawlerDriver 
{
	/** A main entry point, to be used by web administrators, for
	creating web indexes.
	 */
	@SuppressWarnings("unchecked")
	public static void main (String[] args) {
		try {
			URL u;
			FileOutputStream isaveFile;

			BreadthFirstSpider web = null;
			//PriorityBasedSpider web=null;
                       Scanner sc=new Scanner(System.in); 
			if (true) {
				// Create a web crawler
                             System.out.println("Enter the url, where you want to start indexing");
				u = new URL(sc.nextLine());
				String indexMode ="myhash" ;
				indexMode = indexMode.toLowerCase();
				
				// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
				// list - Dictionary Structure based on Linked List 
				// myhash - Dictionary Structure based on a Hashtable implemented by the students
				// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
				// avl - Dictionary Structure based on AVL Tree implemented by the students

				if (indexMode.equals("list") || indexMode.equals("hash") || indexMode.equals("myhash")
						|| indexMode.equals("bst") || indexMode.equals("avl")) 
					web = new BreadthFirstSpider(u, new Indexer(indexMode));
					//web = new PriorityBasedSpider(u, new Indexer(indexMode));

				else {
					System.out.println("Invalid index mode - use either \"list\" or \"hash\"");
					System.exit(1);
				}

				// Open the index save file
				isaveFile = new FileOutputStream("foo.txt");

			}
			else 
			{
				System.out.println("Usage: CrawlerDriver url index-saveFile [hash | list] [crawl limit]");
				return;
			}

			// Check for a page limit
			
				web.crawlLimitDefault = Integer.parseInt("10");

			// Crawl the web site
			Indexer index = web.crawl();
		                  //System.out.println("index"+index.);
			//index.disp();
			
			
			ObjectIterator<Object> itr;
			PageWord p=new PageWord("msitprogram");
			System.out.println("search results");
			index.save(isaveFile);
			index.disp();
			itr=(ObjectIterator<Object>) index.retrievePages(p);
			Vector<Object> te = new Vector<Object>();
			//te=itr.returnVec();
			
			//for(int i=0;i<te.size();i++)
			//{
				
				
			//	System.out.println(te.get(i));
			//}
				
			// Save the index to the specified file
			
			

		} catch (IOException e) {
			System.out.println("Bad file or URL specification");
		} catch (NumberFormatException e) {
			System.out.println("Bad page limit.");
		}
	}
}

