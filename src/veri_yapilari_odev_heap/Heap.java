//02170201006
//Doğukan Gültürk

package veri_yapilari_odev_heap;

import java.util.Scanner;

public class Heap {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Sayıları girin: ");
    String input = sc.nextLine();

    String[] numbers = input.split(",");
    int[] heap = new int[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      heap[i] = Integer.parseInt(numbers[i]);
    }

    // min heap kontrol kısmı
    boolean isMinHeap = true;
    for (int i = 0; i < heap.length / 3; i++) {
      if (heap[i] > heap[3 * i + 1] || heap[i] > heap[3 * i + 2] || heap[i] > heap[3 * i + 3]) {
        isMinHeap = false;
        break;
      }
    }
    System.out.println(isMinHeap ? "Minimum heap'tir." : "Değildir.");

    // uclu mın heap donusturme
    for (int i = heap.length / 3 - 1; i >= 0; i--) {
      heapify(heap, i);
    }

    // heap ı ekrana yazdırma
    System.out.print("Heap: ");
    for (int i : heap) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  public static void heapify(int[] heap, int i) {
    int smallest = i;
    int left = 3 * i + 1;
    int middle = 3 * i + 2;
    int right = 3 * i + 3;

    // sol kontrolu
    if (left < heap.length && heap[left] < heap[smallest]) {
      smallest = left;
    }
    // orta 
    if (middle < heap.length && heap[middle] < heap[smallest]) {
      smallest = middle;
    }
    // sag 
    if (right < heap.length && heap[right] < heap[smallest]) {
      smallest = right;
    }

    // en kucuk deger yer degıstırme
    if (smallest != i) {
      int temp = heap[i];
      heap[i] = heap[smallest];
      heap[smallest] = temp;
      // ıslemı en kucuge uyghula
      heapify(heap, smallest);
    }
  }
  }