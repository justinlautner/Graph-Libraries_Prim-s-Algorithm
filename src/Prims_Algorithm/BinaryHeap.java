package Prims_Algorithm;

import MainPackage.WeightedEdge;

public class BinaryHeap {

    //private static int totalCapacity;
    private static int currentSize;
    private static int i, j;
    private static int[] array;

    /*public BinaryHeap(int totalCapacity){

        BinaryHeap.totalCapacity = totalCapacity;

    }*/

    private int parent(int pos){ return pos / 2; }

    private static void swap(int i, int j){
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int rightChild(int pos){ return (2 * pos) + 1; }

    private int leftChild(int pos){ return (2* pos); }

    private static void Heapify_Up(int i){

        if (i > 1){
            j = i / 2;
            if (array[i] < array[j]){
                swap(i, j);
                Heapify_Up(j);
            }
        }

        /*Heapify-up (H, i) :
        If i> 1 then
        let ] = parent(i) = Lil2J
        If key[H[i]]<key[H[j]] then
        swap the array entries H[i] mad H[j]
        Heapify-up (H, j )
        Endif
                Endif*/

    }

    private static void Heapify_Down(int i){

        int n = array.length;
        if ((2 * i) > n){
            return;
        }
        else if ((2 * i) < n){
            int left = 2 *i, right = 2 * i + 1;
            //Let j be the index that minimizes key [H [left] ] and key [H [right] ]
        }
        else if ((2 * i) == n){
            j = 2 * i;
        }
        if (array[j] < array[i]){
            swap(j, i);
            Heapify_Down(j);
        }

        /*Let n= length(H)
        If 2i>n then
        Terminate with H unchanged
        Else if 2f<n then
        Let left=2f, and right=2f+l
        Let ] be the index that minimizes key [H [left] ] and key [H [right] ]
        Else if 2f=n then
        Let ] = 2f
        Endif
        If key[H[]]] < key[H[i]] then
        swap the array entries H[i] and H[]]
        Heapify-down (H, ])
        Endif*/

    }

    private static void StartHeap(int N){

        array = new int[N];

    }

    private void Insert(int v){

        if (currentSize == array.length){
            return;
        }
        array[currentSize++] = v;
        int index = currentSize;

        while (array[index] < array[index / 2]){
            swap(index, parent(index));
            index = parent(index);
        }
        
    }

    private static int FindMin(){

        return array[1];

    }

    private static void Delete(int i){



    }

    private static int ExtractMin(){

        if (currentSize == 0){
            System.out.println("Queue is empty. ");
            return -1;
        }
        int min = array[1];
        array[1] = array[currentSize];
        currentSize--;
        Heapify_Up(array[1]);

        return min;

    }

    private static void Delete(v){



    }

    private static void ChangeKey(v, newValue){

    }

}
