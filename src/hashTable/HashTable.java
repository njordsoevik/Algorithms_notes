package hashTable;


public class HashTable {

    private LinkedListHash[] p;
    private Integer size;

    public HashTable(Integer size) {
        p = new LinkedListHash[size];
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
        p[hash(key)].insert(key,value);
    }

    public void increaseKey(String key) {
        p[hash(key)].insert(key,1);
    }

    public Integer findKey(String key) {
        if (p[hash(key)] instanceof LinkedListHash) {
            return p[hash(key)].get(key);
        }
        return null;
    }

    public void delete(String key) {
        if (p[hash(key)] instanceof LinkedListHash) {
            p[hash(key)].delete(key);
        }
    }

    public void printTable() {
        for (int i = 0; i<this.size;i++) {
            System.out.print("Hash Key "+i+":");
            if (p[i] != null) {
                p[i].printList();
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        HashTable h = new HashTable(4);
        h.insert("pip",90);
        h.insert("werwefwc",190);
        h.insert("pdsad",903);
        h.insert("xcvxz",9230);
        h.insert("wefew-w",120);
        h.insert("wef!ws",50);

        h.increaseKey("pip");
        h.increaseKey("wed");

        h.delete("pdsad");
        h.findKey("pip");
        h.printTable();
    }
}
