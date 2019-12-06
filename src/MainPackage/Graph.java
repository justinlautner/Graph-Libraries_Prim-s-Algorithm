package MainPackage;

import java.util.*;

class Graph {

    private static int vertices, sourceLocation, destinationLocation;
    private static LinkedList<Edge> [] adjacencylist;
    private static LinkedList<WeightedEdge> [] adjacencylistWeighted;
    private static ArrayList<String> verticesArray = new ArrayList<>();
    private static float globalMinimumCost = Integer.MAX_VALUE;
    private static ArrayList<TSPNode> globalMinimumPath = new ArrayList<>();
    //private static HashMap<String, Float> verticesWeight = new HashMap<>();
    //private static int index;

    static void startGraph(boolean weighted, int vertices) {

        Graph.vertices = vertices;

        //Weighted boolean, so as to not waste time creating linkedlists for an unused arraylist
        if (weighted){
            adjacencylistWeighted = new LinkedList[vertices];
            //Initialize new linkedlists for each new vertices
            for (int i = 0; i < vertices; i++){
                adjacencylistWeighted[i] = new LinkedList<>();
            }
        }
        else{
            adjacencylist = new LinkedList[vertices];
            //Initialize new linkedlists for each new vertices
            for (int i = 0; i < vertices; i++){
                adjacencylist[i] = new LinkedList<>();
            }
        }

    }//end Graph method

    static void addEdge(boolean directed, String sourceNode, String destinationNode)    {
        //Create two edges, one for the initial edge, and another for the unweighted graph (which goes both ways)
        Edge edge = new Edge(sourceNode, destinationNode);
        Edge edgeReverse = new Edge(destinationNode, sourceNode);

        /*
        *   If else statements here to ensure 0 corresponds to a, 1 to b and so on
        *   This is so that, during printing, it can find all edges associated with one node at a time
        *   But, i cannot use a letter as a location, or else i would need over a hundred linkedlists (ouch)
         */
        if (!verticesArray.contains(sourceNode)){
            verticesArray.add(sourceNode);
            sourceLocation = verticesArray.lastIndexOf(sourceNode);
        }
        else{
            sourceLocation = verticesArray.lastIndexOf(sourceNode);
        }
        //Ensures no destination is added to vertices without an edge
        if (!destinationNode.equals("")){
            if (!verticesArray.contains(destinationNode)){
                verticesArray.add(destinationNode);
                destinationLocation = verticesArray.lastIndexOf(destinationNode);
            }
            else {
                destinationLocation = verticesArray.lastIndexOf(destinationNode);
            }

            /*
             *  This determines whether to create a directed or undirected graph
             *  An undirected graph adds an edge to both vertices
             *  avoids adding redundant edges if self-referential nodes
             */
            if (directed || sourceNode.equals(destinationNode)){
                adjacencylist[sourceLocation].addFirst(edge);
            }
            else {
                adjacencylist[sourceLocation].addFirst(edge);
                adjacencylist[destinationLocation].addFirst(edgeReverse);
            }
        }

    }//end addEdge method

    static void addEdge(boolean directed, String sourceNode, String destinationNode, String weight)   {
        //Create two edges, one for the initial edge, and another for the unweighted graph (which goes both ways)
        WeightedEdge edge = new WeightedEdge(sourceNode, destinationNode, weight);
        WeightedEdge edgeReverse = new WeightedEdge(destinationNode, sourceNode, weight);

        /*
         *   If else statements here to ensure 0 corresponds to a, 1 to b and so on
         *   This is so that, during printing, it can find all edges associated with one node at a time
         *   But, i cannot use a letter as a location, or else i would need over a hundred linkedlists (ouch)
         */
        if (!verticesArray.contains(sourceNode)){
            verticesArray.add(sourceNode);
            sourceLocation = verticesArray.lastIndexOf(sourceNode);
        }
        else{
            sourceLocation = verticesArray.lastIndexOf(sourceNode);
        }
        //Ensures no destination is added to vertices without an edge
        if(!destinationNode.equals("")){
            if (!verticesArray.contains(destinationNode)){
                verticesArray.add(destinationNode);
                destinationLocation = verticesArray.lastIndexOf(destinationNode);
            }
            else {
                destinationLocation = verticesArray.lastIndexOf(destinationNode);
            }

            /*
             *  This determines whether to create a directed or undirected graph
             *  An undirected graph adds an edge to both vertices
             *  avoids adding redundant edges if self-referential nodes
             */

            if (directed || sourceNode.equals(destinationNode)){
                adjacencylistWeighted[sourceLocation].addFirst(edge);
            }
            else{
                adjacencylistWeighted[sourceLocation].addFirst(edge);
                adjacencylistWeighted[destinationLocation].addFirst(edgeReverse);
            }
        }

    }//end addEdge method

    static void printWeighted() {
        System.out.println(verticesArray);
        for (int i = 0; i < verticesArray.size(); i++) {
            LinkedList<WeightedEdge> list = adjacencylistWeighted[i];
            System.out.print(verticesArray.get(i) + ": ");
            for (int j = 0; j < list.size() ; j++) {
                if (j != 0){
                    System.out.print( ", ");
                }
                System.out.print("( " + list.get(j).destinationNode + ", " + list.get(j).weight + " )");
            }
            System.out.println();
        }
        Collections.sort(verticesArray);
    }//end printWeighted method

    static void printGraph()    {
        for (int i = 0; i < verticesArray.size(); i++) {
            LinkedList<Edge> list = adjacencylist[i];
            System.out.print(verticesArray.get(i) + ": ");
            for (int j = 0; j < list.size() ; j++) {
                if (j != 0){
                    System.out.print( ", ");
                }
                System.out.print(list.get(j).destinationNode);
            }
            System.out.println();
        }
    }// end printGraph method

    static void Prims_Algorithm(){

        boolean[] inHeap = new boolean[vertices];
        float [] key = new float[vertices];
        Mods[] mods = new Mods[vertices];
        Map<String, Integer> keyMap = new HashMap<>();
        //ArrayList<String> keyMap = new ArrayList<>();

        int temp = 0;
        HeapNode[] heapNodes = new HeapNode[vertices];
        for (int i = 0; i < vertices ; i++) {
            heapNodes[i] = new HeapNode(i, Integer.MAX_VALUE);
            mods[i] = new Mods();
            mods[i].parent = -1;
            inHeap[i] = true;
            //System.out.print("this is in heap: " + i);
            LinkedList<WeightedEdge> list1 = adjacencylistWeighted[i];
            for (WeightedEdge weightedEdge : list1) {
                if (!keyMap.containsValue(i)){
                    keyMap.put(weightedEdge.destinationNode, i);
                }

                //keyMap.add(weightedEdge.destinationNode);
                //System.out.print(weightedEdge.destinationNode + i);
                //System.out.print(keyMap.get(weightedEdge.destinationNode));
            }
            /*for (int k = 0; k < list1.size(); k++){
                WeightedEdge edge1 = list1.get(k);
                keyMap.put(edge1.destinationNode, k);
            }*/
            /*for (int l = 0; l < keyMap.size(); l++){
                System.out.print(keyMap.get(list1.get(l).destinationNode));
            }*/
            key[i] = Integer.MAX_VALUE;
        }
        //System.out.println(keyMap);

        heapNodes[0].key = 0;

        BinaryHeap.StartHeap(vertices);
        for (int i = 0; i <vertices ; i++) {
            BinaryHeap.Insert(heapNodes[i]);
        }

        while(!BinaryHeap.isEmpty()){

            HeapNode extractedNode = BinaryHeap.ExtractMin();

            int extractedVertex = extractedNode.vertex;
            inHeap[extractedVertex] = false;
            //BinaryHeap.display();
            //System.out.println("extractedvertex: " + extractedVertex);

            LinkedList<WeightedEdge> list = adjacencylistWeighted[extractedVertex];
            for (int i = 0; i < list.size() ; i++) {
                WeightedEdge edge = list.get(i);
                //System.out.println(edge.weight);

                //System.out.print("is " + keyMap.get(edge.destinationNode) + " inheap? " + inHeap[i]);
                if (inHeap[keyMap.get(edge.destinationNode)]){
                    String destination = edge.destinationNode;
                    float newKey = Float.parseFloat(edge.weight);

                    //System.out.print("key " + key[keyMap.get(destination)] + " newkey" + newKey);
                    if (key[keyMap.get(destination)] > newKey){

                        BinaryHeap.ChangeKey(heapNodes[keyMap.get(destination)], newKey);

                        mods[keyMap.get(destination)].parent = extractedVertex;
                        mods[keyMap.get(destination)].weight = newKey;
                        key[keyMap.get(destination)] = newKey;
                    }
                    /*if (key[keyMap.indexOf(destination)] > newKey){

                        BinaryHeap.ChangeKey(heapNodes[keyMap.indexOf(destination)], newKey);

                        mods[keyMap.indexOf(destination)].parent = extractedVertex;
                        mods[keyMap.indexOf(destination)].weight = newKey;
                        key[keyMap.indexOf(destination)] = newKey;
                        keyMap.remove(destination);
                        System.out.println(keyMap);
                    }*/
                }
                //BinaryHeap.display();

            }
        }

        printMinimumSpanningTree(mods);

    }

    private static void printMinimumSpanningTree(Mods[] mods){
        float total_min_weight = 0;
        System.out.println("Minimum Spanning Tree: ");
        for (int i = 1; i <vertices ; i++) {
            System.out.println("Edge: " + i + " - " + mods[i].parent +
                    " weight: " + mods[i].weight);
            total_min_weight += mods[i].weight;
        }
        System.out.println("Total minimum key: " + total_min_weight);
    }

    private static void travellingSalesmanProblem(LinkedList<WeightedEdge> graph, String currentVertice, ArrayList<TSPNode> currentPath, float pathCost, ArrayList<String> visited){
        Collections.sort(visited);
        System.out.println("SORTED" + visited);
        if (verticesArray.equals(visited)){
            for (int j = 0; j < graph.size(); j++){
                //add last node to start node & update cost
                if (graph.get(j).destinationNode.equals(currentPath.get(0).vertice)){
                    currentPath.add(new TSPNode(graph.get(j).destinationNode, Float.parseFloat(graph.get(j).weight)));
                    globalMinimumCost += Float.parseFloat(graph.get(j).weight);
                }
            }
            System.out.println(globalMinimumPath + "HELLO?");
            if (pathCost < globalMinimumCost){
                globalMinimumCost = pathCost;
                globalMinimumPath = currentPath;
                System.out.println(globalMinimumCost);
            }
        }
        else{
            visited.add(currentVertice);
            System.out.println(visited);
            for (int j = 0; j < graph.size(); j++){
                if (!visited.contains(graph.get(j).destinationNode)){
                    currentPath.add(new TSPNode(graph.get(j).destinationNode, Float.parseFloat(graph.get(j).weight)));
                    System.out.println(graph.get(j).destinationNode);
                    System.out.println(pathCost);

                    travellingSalesmanProblem(adjacencylistWeighted[j], graph.get(j).destinationNode, currentPath, pathCost + Float.parseFloat(graph.get(j).weight), visited);
                    currentPath.remove(j); System.out.println("IS THIS HAPPENING?");
                }
                //travellingSalesmanProblem(adjacencylistWeighted[index++], currentVertice, currentPath, pathCost, visited);
            }

        }

    }

    public static void initiateTS(){

        /*for (int i = 0; i < verticesArray.size(); i++) {
            LinkedList<WeightedEdge> list = adjacencylistWeighted[i];
            System.out.print(verticesArray.get(i) + ": ");
            for (int j = 0; j < list.size() ; j++) {
                if (j != 0){
                    System.out.print( ", ");
                }
                System.out.print("( " + list.get(j).destinationNode + ", " + list.get(j).weight + " )");
            }
            System.out.println();
        }
*/
        LinkedList<WeightedEdge> list = adjacencylistWeighted[0];
        ArrayList<String> visited = new ArrayList<>();
        //visited.add(verticesArray.get(0));
        ArrayList<TSPNode> currentPath = new ArrayList<>();
        float pathCost = 0;
        String currentVertice = verticesArray.get(0);
        travellingSalesmanProblem(list, currentVertice, currentPath, pathCost, visited);

    }

    public static void printTSP(){
        for (int i = 0; i < globalMinimumPath.size(); i++){
            System.out.print("DESTINATION: " + globalMinimumPath.get(i).vertice + " WEIGHT: " + globalMinimumPath.get(i).weight);
            System.out.println();
            System.out.println("TOTAL COST OF PATH: " + globalMinimumCost);
        }

    }

}//end Graph class
