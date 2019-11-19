package MainPackage;


import java.util.HashMap;
import java.util.Map;

public class BinaryHeap {

    private static int currentSize;
    private static HeapNode[] heap;
    //private static int[] Position;
    private static Map<Integer, Integer> Position;

    private static void swap(int i, int j){
        HeapNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private static void Heapify_Up(int i){

        if (i > 1){
            int j = i / 2;
            if (heap[i].key < heap[j].key){
                swap(i, j);
                Heapify_Up(j);
            }
        }

    }

    private static void Heapify_Down(int i){

        int n = currentSize;
        int j = 0;
        if ((2 * i) > n){
            return;
        }
        else if ((2 * i) < n){
            //int left = 2 * i, right = 2 * i + 1;
            j = 2 * i + 1;
        }
        else if ((2 * i) == n){
            j = 2 * i;
        }
        if (heap[j].key < heap[i].key){
            swap(i, j);
            Heapify_Down(j);
        }

    }

    static void StartHeap(int N){

        heap = new HeapNode[N + 1];
        heap[0] = new HeapNode(-1, Integer.MIN_VALUE);
        currentSize = 0;

        Position = new HashMap<>();

    }

    static void Insert(HeapNode v){

        currentSize++;
        int index = currentSize;
        heap[currentSize] = v;

        //Position[v.vertex] = index;
        Position.put(v.vertex, index);
        Heapify_Up(index);
        
    }

    private static HeapNode FindMin(){

        return heap[1];

    }

    private static void Delete(int i){

        heap[i] = null;
        Position.remove(i);
        Heapify_Up(i + 1);

    }

    static HeapNode ExtractMin(){

        HeapNode min = heap[1];
        HeapNode node = heap[currentSize];
        //Position[node.vertex] = 1;
        Position.put(node.vertex, 1);
        heap[1] = node;
        heap[currentSize] = null;
        Heapify_Down(1);
        currentSize--;

        return min;

    }

    private static void Delete(HeapNode v){

        heap[Position.get(v.vertex)] = null;
        Heapify_Up(Position.get(v.vertex + 1));

    }

    static void ChangeKey(HeapNode v, float newValue){

        int index = Position.get(v.vertex);

        HeapNode temp = heap[index];
        temp.key = newValue;
        heap[index] = temp;

    }

    static boolean isEmpty() {
        return currentSize == 0;
    }

    public static void display() {
        for (int i = 0; i <=currentSize; i++) {
            System.out.println(" " + heap[i].vertex + "   key   " + heap[i].key);
        }
        System.out.println("________________________");
    }

}
