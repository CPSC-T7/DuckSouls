package items;

import genericInterfaces.Drawable;

public interface Item extends Drawable{
	
	public abstract String getName();
	public abstract boolean tryToSpawn();
	
}
