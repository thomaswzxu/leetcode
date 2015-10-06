package design;

import java.util.Iterator;

/**
 * http://www.careercup.com/question?id=5723105072775168
 * @author Thomas Xu
 *
 */
public class TrainSystem{
	
}

class Train {
	
	public Iterator<Stop> getAllStops(){return null;}
	
	public boolean doesStop(Stop stop){return false;}
	
	public Stop getFistStop(){return null;}
	
	public Stop getLastStop(){return null;}
}

class StopManager {
	public static Stop[] getAllStops(){ return null; }
	public static Stop findStop (String id) { return null;}
}

class Stop {
	
	public String getId(){return null;}
	
	public String getName(){return null;}
}
