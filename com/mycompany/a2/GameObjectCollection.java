package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Iterator;

//class will implement the Icollection class, to use the iterator pattern
public class GameObjectCollection implements ICollection
{
	
	/*private attributes
	 *ArrayList will be used. iterator pattern allows for the data structure to be changed 
	 *and will be able to iterated/added to. 
	 */
	private ArrayList<GameObject> gameObjectCollection;
	
	//constructor
	public GameObjectCollection()
	{
		gameObjectCollection = new ArrayList<GameObject>();
	}
	

	/*method to add object to our game collection
	 * Check if its  a game object first
	 */
	@Override
	public void add(Object o) 
	{
		if (o instanceof GameObject)
		{
			gameObjectCollection.add((GameObject) o);
		}
		else
		{
			System.out.println("Error. Object is not a Game Object");
		}
		
	}
	
	
	//method to get the iterator from our private class 
	@Override
	public IIterator getIterator() 
	{
		return new GameObjectCollectionIterator();
		
	}
	
	private class GameObjectCollectionIterator implements IIterator
	{
		//private attributes
		//keeps track of current element
		private int currElementIndex;
		
		public GameObjectCollectionIterator()
		{
			currElementIndex = -1;
		}

		//method to determine if there is next available object
		@Override
		public boolean hasNext() 
		{
			if (gameObjectCollection.size() <=0)
				return false;
			if (currElementIndex == gameObjectCollection.size()-1)
				return false;
			return true;
		}

		//method to get next available object
		@Override
		public GameObject getNext() 
		{
			currElementIndex++;
			return (gameObjectCollection.get(currElementIndex));
		}
		
	}
}
