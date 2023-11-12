package com.mycompany.a2;

/*
 * IIterator will be part of the iterator design pattern
 * Purpose is to iterate through the objects, whatever data structure is used
 */
public interface IIterator 
{
	public boolean hasNext();
	
	public GameObject getNext();
	
}
