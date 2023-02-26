package org.example;

public class Largest {
  public static int largest(int[] list) {
    int max = 0;

    for (int i = 0; i < list.length; i++) {
      if (list[i] > max) {
        max = list[i];
      }
    }
    return max;
  }
}
