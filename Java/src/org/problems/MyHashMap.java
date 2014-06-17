public class MyHashMap<K,V> {
	
	protected Entry table[];
	private static final int DEFAULT_INIT_CAPACITY = 16;
	private static final float LOAD_FACTOR = 0.75f;
	public static int threshold;
	public static int size;
	
	public MyHashMap() {
		threshold = (int) (DEFAULT_INIT_CAPACITY * LOAD_FACTOR);
		table = new Entry[DEFAULT_INIT_CAPACITY];
		size = 0;
	}
	
	protected int size() {
		return size;
	}
	
	protected int indexFor(int hashcode, int length) {
		int index = hashcode&(length-1);
		return index;
	}
	
	protected Entry<K,V> getEntry(K key) {
		int index = indexFor(key.hashCode(),table.length);
		Entry<K,V> entry = table[index];
		while(entry != null){
			if(entry.key.equals(key)){
				return entry;
			}
			entry = entry.next;
		}
		return null;
	}
	
	public V get(K key) {
		Entry<K,V> entry = getEntry(key);
		if(entry == null){
			return null;
		}
		return entry.value;	
	}
	
	public void put(K key, V value) {
		int index = indexFor(key.hashCode(),table.length);
		Entry<K,V> entry = table[index];
		if(containsKey(key)){

			//update
			while(entry != null){
				if(entry.key.equals(key)){
					entry.value = value;
					entry.registerAccess(this);
					break;
				}
				entry = entry.next;
			}
		}else{

			createEntry(key,value,index);

		}

		if(size >= threshold){
			resize(table.length*2);
		}


	}
	protected void createEntry(K key, V value, int index) {
			Entry<K,V> entry = table[index];
			table[index] = new Entry<K,V>(key,value,entry);
			//System.out.println("Create Entry> " + table[index] + " ---> ext --> "+ entry);
			size++;
	}
	public boolean containsKey(K key) {
		int index = indexFor(key.hashCode(),table.length);
		Entry<K,V> entry = table[index];
		if(entry == null){
			return false;
		}else{
			while(entry != null){
				if(entry.key.equals(key)){
					return true;
				}
				entry = entry.next;

			}
		}

		return false;
	}
	private void resize(int newCapacity) {
		Entry newTable[] = new Entry[newCapacity];
		transfer(newTable);
		table = newTable;
		threshold = (int)(newCapacity*LOAD_FACTOR);
	} 
	protected void transfer(Entry newTable[]) {
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				Entry<K,V> current = table[i];
				while(current != null) {
					Entry<K,V> entry = current;
					current = current.next;

					int index = indexFor(entry.key.hashCode(),newTable.length);
					entry.next = newTable[index];
					newTable[index] = entry; 

				}
			}
		}
	}
	public void removeKey(K key) {

		if(containsKey(key)) {
		        System.out.println("Remo??" + key);
			int index = indexFor(key.hashCode(),table.length);
			Entry<K,V> entry = table[index];
			if(entry.key.equals(key)) {

				table[index] = entry.next;
				entry.removeme(this);
			} else {
				while(entry.next.key.equals(key) == false) {
					entry = entry.next;
				}
				Entry<K,V> target = entry.next;
				entry.next = entry.next.next;

				target.removeme(this);
			}
			size--;
		} else {
			System.out.println("The following key what not found, so this could not be removed> " + key);
		}
	}
	public void printTable() {
	System.out.println("HashMap size = " + size);
		for(int i = 0; i < table.length; i++){
			Entry<K,V> entry = table[i];
			if(entry != null){
				System.out.print("["+i+"]");
				while(entry != null){
					System.out.print(entry + " ");
					entry = entry.next;
				}
				System.out.println();		
			}
		}
	}

	static class Entry<K,V> {
		public K key;
		public V value;
		public Entry<K,V> next;
		public Entry(K k, V v, Entry<K,V> next){
			key = k;
			value = v;
			this.next = next;
		}
		// this 2 methods are use to regiter edit access for future uses, like implementing a linked hash map
		protected void registerAccess(MyHashMap<K,V> hashmap) {
		}
		
		protected void removeme(MyHashMap<K,V> hashmap) {
		
		}
		
		public String toString() {
			return "[key = "+key+", Value = "+value+", hashcode="+key.hashCode()+"]";
		}
	}
	public static void main(String args[]) {
		MyHashMap<String,String> hashmap= new MyHashMap<String,String>();
		hashmap.put("uno","one");
		hashmap.put("dos","two");
		hashmap.put("tres","three");
		hashmap.put("cuatro","four");
		hashmap.put("cinco","five");		
		hashmap.put("seis","six");
		hashmap.put("siete","seven");
		hashmap.put("ocho","eight");
		hashmap.put("nueve","nine");
		hashmap.put("diez","ten");
		hashmap.put("once","eleven");
		hashmap.put("doce","twelve");
		hashmap.put("trece","thirteen");
		hashmap.put("catroce","fortheen");
		hashmap.put("quince","fivetheen");
		hashmap.put("dieciseis","sixteen");
		hashmap.put("axeliux","Axeliux");
		hashmap.printTable();


		System.out.println(hashmap.get("uno"));
		System.out.println(hashmap.get("dos"));
		System.out.println(hashmap.get("tres"));
		System.out.println(hashmap.get("cuatro"));
		System.out.println(hashmap.get("cinco"));
		System.out.println(hashmap.get("seis"));
		System.out.println(hashmap.get("siete"));
		System.out.println(hashmap.get("ocho"));
		System.out.println(hashmap.get("nueve"));
		System.out.println(hashmap.get("diez"));
		System.out.println(hashmap.get("doce"));
		System.out.println(hashmap.get("trece"));
		System.out.println(hashmap.get("catroce"));
		System.out.println(hashmap.get("quince"));
		System.out.println(hashmap.get("dieciseis"));
		System.out.println(hashmap.get("axeliux"));

	}
}
