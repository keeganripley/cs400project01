import java.util.NoSuchElementException;

// Name: Keegan Ripley
// Email: kjripley@wisc.edu
// Team: EE
// TA: Keren
// Lecturer: Florian
// Notes to Grader: none :)
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>  {

	private int size; // Holds the number of key-value pairs in the hash table.
	private int capacity; // Holds the maximum number of elements the hash table can
							// contain.

	// (FROM SPEC) use a private array instance field within your HashTableMap class
	// to store key-value pairs.
	static private class ListNode {
		Object key; // Holds the key
		Object value; // Holds the value at a key
		ListNode next; // Points to the next ListNode in the list. Null = end of list.
	}

	private ListNode[] hashTable; // Initialize the hash table.

	// Initializes a hash table with a given capacity.
	public HashTableMap(int capacity) {
		// Initialize hash table
		hashTable = new ListNode[capacity];
		// Set parameter capacity to global variable capacity.
		this.capacity = capacity;

	}

	// Initializes a default hash table with capacity of 10.
	public HashTableMap() {
		// Initialize a hash table with capacity of 10.
		hashTable = new ListNode[10]; // Default capacity = 10
		// Set global variable capacity to 10.
		capacity = 10; // Is it better to set to hashTable.length?
	}

	// (FROM SPEC) store new values in your hash table at the index corresponding to
	// the ( absolute value ) of your key's hashCode() modulus the HashTableMap's
	// current capacity.
	// Perform a hash function on a key.
	int hash(KeyType key) {
		// Use absolute value to keep the key positive.
		return (Math.abs(key.hashCode())) % hashTable.length;
	}

	// (FROM SPEC) include a size method that returns the number of key-value pairs
	// stored in this collection.
	// Return the size of the hash table.
	public int size() {
		return size;
	}

	// Return the capacity of the hash table (used for testing).
	public int capacity() {
		return capacity;
	}

	// Insert a new key into the hash table at an open location.
	// If the hash table reaches 80% capacity, resize the hash table.
	// If key successfully inserted, return true. Otherwise return false.
	public boolean put(KeyType key, ValueType value) {
		// Initialize boolean val to true. This will later be returned.
		boolean val = true;
		// Assign key's location to an index.
		int index = hash(key);
		// Create list to edit the hash table at the index determined by hash function.
		ListNode list = hashTable[index];
		// Search list until list = null (aka reached end of list)
		while (list != null) {
			// Search the nodes in the list, to see if the key already exists.
			// If it is in the list, return false.
			if (list.key.equals(key)) {
				return false;
			}
			// Go to node after list to continue while loop.
			list = list.next;
		}
		// If key not found, add a key to the hash table, then return true.

		// Create a new list to hold the new key and value.
		ListNode newKey = new ListNode();
		// Assign the value and key of the parameter to the new key and value.
		newKey.key = key;
		newKey.value = value;
		// I'll be honest I don't know the point of this line except that my code breaks
		// without it.
		newKey.next = hashTable[index];
		// Set the open location while loop found to hold the values of the new key.
		hashTable[index] = newKey;
		// Add one to the size of the hash table.
		size++;
		// If size reaches 80% of capacity, resize the hash table.
		if (size >= capacity * 0.8) {
			resize();
		}

		// (FROM SPEC) The put method should only return true after successfully storing
		// a new key-value pair.
		// If the method has reached this point, val will equal true and will be
		// returned.
		return val;
	}

	// (FROM SPEC) grow by doubling and rehashing, whenever its load capacity
	// becomes greater than or equal to 80%. Define private helper methods to
	// organize this functionality.
	// Double the size of the hash table and rehash.
	private void resize() {
		// Create new table that is double the length of previous--this will hold the new
		// table contents.
		ListNode[] rehashedTable = new ListNode[hashTable.length * 2];
		// Update capacity to correctly reflect the new capacity of the hash table.
		capacity = hashTable.length * 2;
		// Iterate through each value of the old hash table that needs to be rehashed.
		for (int i = 0; i < hashTable.length; i++) {
			// Set a node to equal the index at i of the hash table.
			ListNode node = hashTable[i];
			// Set another node to equal the current node.
			ListNode nextNode = node; // Point to the position of the next node.
			// Search the list until you reach the end of it (where it will equal null).
			// For each element(key) in the hash table, rehash the key and move it to
						// the new rehashed list.
			while (nextNode != null) {
				// Move the node that is at index i on to the rehashed table.
				// Rehash the key.
				int hash = (Math.abs(nextNode.key.hashCode())) % capacity;
				// Set the remembered value held in next to that in the rehashed table at the
				// newly hashed index.
				rehashedTable[hash] = nextNode;
				// Move on to the next ListNode in the old hash table to continue the while
				// loop.
				nextNode = nextNode.next;
			}
		}
		// Set the hashTable to equal the rehashed table.
		hashTable = rehashedTable;
	}

	// Get the value associated with the specified key in the hash table.
	// If there is no key catch error.
	@SuppressWarnings("unchecked") // casted ValueType to Object
	public ValueType get(KeyType key) throws NoSuchElementException {
		// Assign key's location to an index.
		int index = hash(key);
		// Create a list to traverse hash table
		ListNode list = hashTable[index];
		// Initialize value to hold the value at specific key.
		ValueType value = null;

		// Traverse the list.
		// If key is found, return the value.
		while (list != null) {
			if (list.key.equals(key)) {
				value = (ValueType) list.value;
				return value;
			}
			list = list.next;
		}
		// If key is not found catch error and return a message.
		throw new NoSuchElementException("Key is not in the hash table.");
	}

	// Check the hash table to see if it contains paramaterized key. Primarily used
	// for testing.
	public boolean containsKey(KeyType key) {
		// Assign key's location to an index.
		int index = hash(key);
		// Create list to traverse the hash table at the appropriate index.
		ListNode list = hashTable[index];
		// Search the list until the end is reached (indicated by null).
		while (list != null) {
			// If key is found return true.
			if (list.key.equals(key)) {
				return true;
			}
			// Move to next index.
			list = list.next;
		}
		// If no key is found, return false.
		return false;
	}

	// (FROM SPEC) include a remove method that returns a reference to the value
	// associated with the key that is being removed. When the key being removed
	// does not exist, this method should instead return null.
	// Remove a key from hash table by first returning its value then removing
	// the key.
	@SuppressWarnings("unchecked") // casted ValueType to Object
	public ValueType remove(KeyType key) {
		// Initialize value to hold the value at the parameterized key.
		ValueType value = null;

		// Check to see if key is in the hash table (yes/no).
		// If no, tell user there is no key.
		int index = hash(key); // At what location should the key be?
		// If index doesn't exist, there is no key and user should be told so.
		if (hashTable[index] == null) {
			// Tell user there is no key at that value.
			System.out.println("There is no existing key.");
			// Value is still null so value is returned.
			return value;
		}

		// Check to see if the value to remove is at the first position on the list.

		ListNode curr = hashTable[index]; // Looks at the linked list at a certain index.
		ListNode prev = hashTable[index]; // Looks at the linked list at same index.
		// Set curr to equal the one in front of it.
		curr = curr.next;
		// If the value at the key wanted is at the first position, remove the first
		// position.
		if (prev.key.equals(key)) {
			// Set value to equal the key that has been found.
			value = (ValueType) prev.value;
			// Set the index of the hash table to one in front of it, skipping over the key
			// thereby removing it.
			hashTable[index] = curr;
			// Decrease the size by one.
			size--;
			// Return the value of the key that was removed.
			return value;
		}

		// If the value exists in the hash table but isn't at the first position...
		// Search the rest of the hash table.
		// Search the list. When curr = null, the end will have been reached.
		while (curr != null) {
			// If the key if found, set the value to equal the key.
			if (curr.key.equals(key)) {
				value = (ValueType) curr.value;
				// Set the index at the current position to that of the next to skip the link
				// over it and remove the key from the table.
				prev.next = curr.next;
				// Decrease size by one as a key has been removed.
				size--;
				return value;
			}
			// Set curr, the current value, to equal the next value.
			curr = curr.next;
			// Set prev, the value one spot before curr, to the next spot, making it equal
			// to the previous curr.
			prev = prev.next;
		}
		// Failsafe
		return null;
	}

	// (FROM SPEC) include a clear method that removes all key-value pairs from this
	// collection.
	// Clear all elements of the hash table.
	public void clear() {
		// Instantiate new hashTable that is empty but has same capacity.
		ListNode[] list = new ListNode[capacity];
		// Set the old hash table to equal the new empty hash table. Capacity is the
		// same.
		hashTable = list;
		// Set size to 0 as all keys have been removed.
		size = 0;
	}
	
}
