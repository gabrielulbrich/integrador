package controllers;

import java.util.List;

import models.Paciente_model;

// Java program for implementation of Heap Sort 
public class HeapSort 
{ 
    public void sort(List<Paciente_model> lista) 
    { 
        int n = lista.size(); 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(lista, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>=0; i--) 
        { 
            // Move current root to end 
            Paciente_model temp = lista.get(0); 
            lista.set(0, lista.get(i));
            lista.set(i, temp);
  
            // call max heapify on the reduced heap 
            heapify(lista, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(List<Paciente_model> lista, int n, int i) 
    { 
        int smallest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && Integer.parseInt(lista.get(l).getPrioridade()) < Integer.parseInt(lista.get(smallest).getPrioridade())) 
        	smallest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && Integer.parseInt(lista.get(r).getPrioridade()) < Integer.parseInt(lista.get(smallest).getPrioridade())) 
        	smallest = r; 
  
        // If largest is not root 
        if (smallest != i) 
        { 
            Paciente_model swap = lista.get(i);
            lista.set(i, lista.get(smallest));
            lista.set(smallest, swap);
  
            // Recursively heapify the affected sub-tree 
            heapify(lista, n, smallest); 
        } 
    } 
  
    /* A utility function to print array of size n */
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 
  

} 