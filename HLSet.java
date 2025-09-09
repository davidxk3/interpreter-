import java.util.*;
/**
 * Implements HLSet objects
 */

public class HLSet extends HLObject{

//Instance Variables

  private TreeSet value = null;
  private int len = 0;
  private Iterator iter;
  
// Class variables

  public static HLSet empty = new HLSet();

// Constructors
  
  public HLSet()
    {
    	value = new TreeSet();
	iter = value.iterator();
    }

  public HLSet(TreeSet lst)
    {
    	value = new TreeSet(lst);
    	len = value.size();
	iter = value.iterator();
    }

  public HLSet(Collection lst)
    {
	value = new TreeSet(lst);
    	len = value.size();
	iter = value.iterator();
    }
    
    // Assumption that set contains values from firstelem to last elem inclusive 
    public HLSet(HLNumber firstelem, HLNumber lastelem)
    {
	// Declare value to be a TreeSet
	value = new TreeSet();
	
	// Iterate through firstelem ... lastelem
	for (int i = firstelem.value; i <= lastelem.value; i++) {
		value.add(i);
	}

	// Update length and iterator 
	len = value.size();
	iter = value.iterator();
    }
  
// Overridden  HLObject Methods

  public HLObject deepclone()
    {
	return new HLSet(new TreeSet<>(value));
    }

// 6Ba -> works with regular sets, 6Ab -> also works with sets that are initialized with intervals 
  public String toString()
    {
	// Use iterator to go through every value for NORMAL INITIALIZED SETS
	// Can't use TreeSet's toString() because it uses square brackets instead of curly brackets
	String set = "{";
	while (iter.hasNext()) {
		// Get next value (Object typecasted to int) and add to set string (output) 
		int value = ((int) iter.next());
		set += value;
		
		// If it has next value, include comma in string 
		if (iter.hasNext()) {
			set += ", ";
		}
	}
	
	set += "}";
	return set; 
    }

  public Boolean isSame (HLObject other) 
    {
    	return this.value.equals(((HLSet)other).getValue());
    }

  public Boolean isLessThan (HLObject other) 
    {
	return ((HLSet)other).getValue().containsAll(this.value) && !this.isSame(other);
    }
    
// Negation applies unary operator +
  public HLSet negate()
    {
    	// iterate through each element and negate it 
	HLSet result = new HLSet();
	while (iter.hasNext()) {
		result.addToSet(new HLNumber((int) iter.next()).negate());
	}
	return result;
    }


// Addition is set union
  public HLSet add(HLObject operand)
    {
	
	HLSet result = (HLSet) operand.deepclone();
	
	while (iter.hasNext()) {
		result.addToSet(new HLNumber((int) iter.next()));
	}

	return result;
    }	

// Subtraction is set difference 
  public HLSet sub(HLObject operand)
    {
    	HLSet set1 = new HLSet();
	TreeSet set2 = ((HLSet) operand).getValue();

	// iterate through values in original set
	while (iter.hasNext()) {
		int val = (int) iter.next();
		// if set 2 doesn't contain this value, add it to set 1 (output)
		if (!(set2.contains(val))) {
			//System.out.println(val + " is not in " + set2.toString());
			//System.out.println(val + " was originally in " + this.value.toString());
			set1.addToSet(new HLNumber((int) val));
		}
	}
	// return 
	return set1;
    }

// Multiplication is set intersection
  public HLSet mul(HLObject operand)
    {
    	HLSet set1 = new HLSet();
	TreeSet set2 = ((HLSet) operand).getValue();

	while (iter.hasNext()) {
		int val = (int) iter.next();
		if (set2.contains(val)) {
			set1.addToSet(new HLNumber((int) val));
		}
	}
	return set1;
    }

// Invalid operation for HLSet
  public HLObject mod(HLObject operand)
    {
    	return null;
    }

// Invalid operation for HLSet
  public HLObject div(HLObject operand)
    {
    	return null;
    }
// Returns the length of the set 
  public HLNumber card()
    {
    	return new HLNumber(len);
    }

// Other Methods  
// Used to check if HLNumber =in HLSet
  public boolean contains(HLObject number) {
     	return this.value.contains(((HLNumber) number).value);
  }

// Helper Methods  
// Add new HLNumber to set (value)
  public void addToSet(HLNumber operand)
  {
	// Uses TreeSet's inherited add method which by default removes duplicates and sorts values by default 
	this.value.add(operand.value);
	// update len and iterator as a side effect
	len += 1;
	iter = value.iterator();
  }


  // Get values of a set
  public TreeSet getValue() {
	return this.value;
  } 
}
