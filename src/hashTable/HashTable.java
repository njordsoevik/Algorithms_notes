package hashTable;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

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
      h = (h * 31 + s.charAt(i)) % this.size;
    }
    return h;
  }

  public void insert(String key, Integer value) {
    if (p[hash(key)] == null) {
      p[hash(key)] = new LinkedListHash();
    }
    p[hash(key)].insert(key, value);
  }

  public void increaseKey(String key) {
    insert(key, 1);
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

  public String listAllKeys() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < this.size; i++) {
      b.append("Hash Key ").append(i).append(":");
      if (p[i] != null) {
        b.append(p[i].printList());
      }
      b.append("\n");
    }
    return b.toString();
  }

  public static void main(String[] args) {
    HashTable h = new HashTable(40);
    String e;
    Scanner s;
    try {
      s = new Scanner(new File("C:\\Users\\njord\\IdeaProjects\\Algorithms_notes\\src\\HashTable\\alice_in_wonderland.txt"));
    } catch (FileNotFoundException fnf) {
        throw new IllegalArgumentException("File not found");
    }
      while (s.hasNext()) {
        e = s.next();
        h.increaseKey(e);
      }
      s.close();

      h.listAllKeys();
      PrintWriter out;
      try {
        out = new PrintWriter("C:\\Users\\njord\\IdeaProjects\\Algorithms_notes\\src\\HashTable\\outputCounts.txt");
      } catch (FileNotFoundException fnf) {
        throw new IllegalArgumentException("File not found");
      }
    System.out.println(h.listAllKeys());
      out.println(h.listAllKeys());
      out.close();

  }
}
