
/**
 * Implements HLNumber
 */

public class HLNumber extends HLObject  implements Comparable<HLNumber> {

//Instance Variables
// I have found it simpler to wrap HLNumber around a primitive int value
// but it could also be wrapped around an Integer value.

  int value;
  
// Class variables

  public static HLNumber zero = new HLNumber(0);

// Constructors
  
  public HLNumber(int value)
    {
	this.value = value;
    }                
  
  public HLNumber(Integer num)
    {
	this.value = num;
    }

//Overridden compareTo

  public int compareTo(HLNumber to)
    {
	if (this.isLessThan(to)) {
		return -1;
	} else if (this.isSame(to)) {
		return 0;
	} else {
		return 1;
	}
    }                
  
// Overridden  HLObject Methods

  // Make clone its own HLNumber
  public HLObject deepclone()
    {
   	 return new HLNumber(this.value); 
    }

  public String toString()
    {
   	 return String.valueOf(this.value);
    }

  public Boolean isSame (HLObject other) 
    {
	// type cast first to HLNumber 
	HLNumber otherNum = (HLNumber) other; 
    	return this.value == otherNum.value;
    }

  public Boolean isLessThan (HLObject other) 
    {
	// type cast first to HLNumber
	HLNumber otherNum = (HLNumber) other;
    	return this.value < otherNum.value;
    }
    
  public HLNumber negate()
    {
	int result = this.value * -1; 
    	return new HLNumber(result);
    }

  public HLNumber add(HLObject operand)
    {
	// type cast first to HLNumber
	HLNumber otherNum = (HLNumber) operand;
	// return new HLNumber containing result 
	return new HLNumber(this.value + otherNum.value);
    }

  public HLNumber sub(HLObject operand)
    {
	HLNumber otherNum = (HLNumber) operand;
	return new HLNumber(this.value - otherNum.value);
    }

  public HLNumber mul(HLObject operand)
    {	
	HLNumber otherNum = (HLNumber) operand;
	return new HLNumber(this.value * otherNum.value);
    }

  public HLNumber mod(HLObject operand)
    {
	HLNumber otherNum = (HLNumber) operand;
        return new HLNumber(this.value % otherNum.value);
    }

  public HLNumber div(HLObject operand)
    {
	HLNumber otherNum = (HLNumber) operand;
        return new HLNumber(this.value / otherNum.value);
	
    }

  public HLNumber card()
    {
    	if (this.value >= 0) {
		return this;
	} else {
		return this.negate();
	}	
    }

// Other Methods  


// Helper Methods  
   
  
}
