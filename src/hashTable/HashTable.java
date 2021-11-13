package hashTable;


public class HashTable {

    private LinkedListHash[] p;
    private Integer size;

    public HashTable(Integer size) {
        p = new LinkedListHash[60];
        this.size = size;
    }

    public Integer hash(String s) {
        Integer h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h*31 + s.charAt(i)) % this.size;
        }
        return h;
    }

    public void insert(String key, Integer value) {
        if (p[hash(key)] == null) {
            p[hash(key)] = new LinkedListHash();
        }
        p[hash(key)].insert(key,value); // TODO insert string not key
    }

    public Integer findKey(String key) {
        if (p[hash(key)] instanceof LinkedListHash) {
            return p[hash(key)].get(key);
        }
        return null;
    }


    public static void main(String[] args) {
        HashTable h = new HashTable(80);
        h.insert("pip",90);
        h.insert("werwefwc",90);
//        h.insert("pdsad",90);
//        h.insert("xcvxz",90);
//        h.insert("wefew-w",90);
//        h.insert("wefws",90);


        System.out.println(h.findKey("pip"));
    }
}
