package com.mycompany.a2;

import java.util.Iterator;

//interface will be used for the iterator pattern
public interface ICollection 
{
	//function for adding object to collection
	public void add(Object o);
	
	//function to obtaining an iterator over the collection. 
	public IIterator getIterator();
	
	
}
