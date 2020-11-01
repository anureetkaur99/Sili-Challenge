package com.mycompany.a3;
import java.util.Vector;

public class GameObjectCollection implements ICollection{
	private Vector<GameObject> objectList;

	public GameObjectCollection() {
		objectList = new Vector<GameObject>();
	}

	public void add(GameObject newObject) {
		objectList.add(newObject);
		
	}

	public IIterator getIterator() {
		return new CollectionIterator();
	}
	
	public int sizeIterator() {
		return objectList.size();
	}
	
	private class CollectionIterator implements IIterator {
		private int currElementIndex;
		
		public CollectionIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if(objectList.size() <= 0 || currElementIndex == objectList.size() - 1) 
				return false;
			return true;
		}
		
		public GameObject getNext() {
			currElementIndex++;
			return objectList.elementAt(currElementIndex);
		}
	}

}
