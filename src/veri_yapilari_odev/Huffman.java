package veri_yapilari_odev;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
  static class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq, Node left, Node right) {
      this.ch = ch;
      this.freq = freq;
      this.left = left;
      this.right = right;
    }

    boolean isLeaf() {
      return left == null && right == null;
    }

    public int compareTo(Node that) {
      return this.freq - that.freq;
    }
  }


  private static String input;

  private static Node huffmanTree;


  private static Map<Character, String> codes = new HashMap<>();

  private static void buildCode(Node node, String s) {
    if (!node.isLeaf()) {

      buildCode(node.left, s + '0');


      buildCode(node.right, s + '1');
    }
    else {

      codes.put(node.ch, s);
    }
  }

  private static Map<Character, Integer> buildFrequencyTable(String input) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char ch : input.toCharArray()) {
      if (freq.containsKey(ch)) {
        freq.put(ch, freq.get(ch) + 1);
      }
      else {
        freq.put(ch, 1);
      }
    }
    return freq;
  }

  private static Node buildTree(Map<Character, Integer> freq) {
    PriorityQueue<Node> queue = new PriorityQueue<>();

    for (char ch : freq.keySet()) {
      queue.add(new Node(ch, freq.get(ch), null, null));
    }

    while (queue.size() > 1) {

      Node left = queue.poll();
      Node right = queue.poll();

      Node parent = new Node('\0', left.freq + right.freq, left, right);


      queue.add(parent);
    }


    return queue.poll();
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    input = sc.nextLine();

    Map<Character, Integer> freq = buildFrequencyTable(input);

    huffmanTree = buildTree(freq);

    buildCode(huffmanTree, "");

    for (char ch : codes.keySet()) {
      System.out.println(ch + ": " + codes.get(ch));
    }

    StringBuilder sb = new StringBuilder();
    for (char ch : input.toCharArray()) {
      sb.append(codes.get(ch));
    }
    System.out.println("Encod edilmiş girdi: " + sb.toString());
  }
}
