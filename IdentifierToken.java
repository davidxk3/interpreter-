import java.util.*;

class HLSymbolEntry {
	int key;
	ArrayDeque identifierStack;

	public HLSymbolEntry(int key, ArrayDeque identifierStack) {
		this.key = key;
		this.identifierStack = identifierStack;
	}

	public int getKey() { return key; }

	public void addValue(Object value) {
		identifierStack.addFirst(value);
	}

	public Object getValue() {
		return identifierStack.peek();
	}

}

class HLSymbTab {
	// Identifier name table for that object 
	static ArrayList<HLSymbolEntry> HLSymbolTable = new ArrayList<HLSymbolEntry>();

	// Add to identifier name table
	public static void newIdName(int key) {
		// add corresponding identifier stack to keep track of values 
		ArrayDeque identifierStack = new ArrayDeque(); 
		HLSymbolTable.add(new HLSymbolEntry(key, identifierStack));
	}

	public static void newValue(int key, Object val) {
		for (HLSymbolEntry ident : HLSymbolTable) {
			if (ident.getKey() == key) {
				ident.addValue(val);
				break;
			}
		}

	}

	public static Object retrieveValue(int key) {
		for (HLSymbolEntry ident : HLSymbolTable) {
			if (ident.getKey() == key) {
				System.out.println(IdentifierToken.getName(ident.getKey()) + ": " + ident.getValue() + "(" + key + ")");
				return ident.getValue();
			}
		}
		// if find nothing 
		System.out.println("Value: " + "NULL");
		return null;
	}
	
	public static void printOut() {
		System.out.println("Called.");
		System.out.println(HLSymbolTable);
		for (HLSymbolEntry ident : HLSymbolTable) {
			System.out.println("Element: " + ident.getValue());
		}
	}
}


/**
 * Describes IDENTIFER tokens
 * The name table is a static ArrayList<String>
 */
public class IdentifierToken extends Token {
/**
 * nameTable contains all the names of all identifiers in HL
 */

static ArrayList<String> nameTable = new ArrayList<String>();


/**

 * Returns the name of the identifier with the specified key
 * @param key int key of identifier
 * @return the name of the identifier with the specified key
 */  
  public static String getName(int key)
    {
	return nameTable.get(key);
    }

/**
 * Returns the name of the identifier with the specified key
 * @param key Integer key of identifier
 * @return the name of the identifier with the specified key
 */      
  public static String getName(Integer key)
    {
	return nameTable.get(key.intValue());
    }

/**
 * Returns the number of names of identifiers in HL
 * @return the number of names of identifiers in HL
 */      
  public static int totalIdentifiers()
    {
	return nameTable.size();
    }

/**
 * A unique key is stored for each identifier name to be used later in symbol tables
 */ 
  public int key;
/**
 * The key is also stored as an Integer to be stored in ASTIdentifier nodes
 */ 
  public Integer Key;

/**
 * Creates a new IdentifierToken with the specified name.
 * If not already there, name is added to nameTable.
 * this will keep a key into nameTable for its name.
 * For C and B learning objectives, this method should trigger a static call to
 * something like HLSymbTab.newIdName(key)
 * @param name name of the identifier (just scanned)
 * @return a new IdentifierToken with the specified name
 */      
  public IdentifierToken(int type, String name)
    {
	this.kind = type;
	this.image = name;
	
	// Check if name is in ArrayList (which allows duplicates), if it isn't add it to nameTable 
  	if (!nameTable.contains(name)) {
		// add to global name table 
		nameTable.add(name);

		// key is length of name table since most recently added symbol entry 	
		key = totalIdentifiers();
		Key = Integer.valueOf(key);

		// default types for IDNUM, IDSET, and other (which is NULL)
		if (type == HLConstants.IDNUM) {
			System.out.println(name + ": " + "IDNUM" + ", KEY: " + this.key);
			HLSymbTab.newValue(key, new HLNumber(0));
		} else if (type == HLConstants.IDSET) {
			System.out.println(name + ": " + "IDSET" + ", KEY: " + this.key);
			HLSymbTab.newValue(key, new HLSet());
		} else {
			HLSymbTab.newValue(key, null);
		}

		// create identifier's name table
		HLSymbTab.newIdName(key);

	}
    }

/**
 * Returns String name of identifier
 * @return String name of identifier
 */     
  public String toString()
    {
    return image;

    }
  
/**
 * Retrieves key of identifier to be stored in ASTIdentifer nodes
 * @return Integer key of identifier
 */     
  public Integer getValue()
    {
	  return Key;
    }

/**
 * Retrieves key of identifier
 * @return int key of identifier
 */  
  public int getKey()
    {
	return key;
    }
}
