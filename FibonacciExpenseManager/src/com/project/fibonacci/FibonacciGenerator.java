package com.project.fibonacci;

import java.util.ArrayList;
import java.util.List;

public class FibonacciGenerator {
    public List<Integer> generate(int terms) {
        List<Integer> fibonacciSeries = new ArrayList<>();
        if (terms < 1) return fibonacciSeries;

        int a = 0, b = 1;
        fibonacciSeries.add(a);

        for (int i = 1; i < terms; i++) {
            fibonacciSeries.add(b);
            int next = a + b;
            a = b;
            b = next;
        }
        return fibonacciSeries;
    }
}
