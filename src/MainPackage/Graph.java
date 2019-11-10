package MainPackage;

import java.util.ArrayList;
import java.util.LinkedList;

class Graph {

    private static int vertices, sourceLocation, destinationLocation;
    private static LinkedList<Edge> [] adjacencylist;
    private static LinkedList<WeightedEdge> [] adjacencylistWeighted;
    private static ArrayList<String> verticesArray = new ArrayList<>();

    Graph(boolean weighted, int vertices) {

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
            if (!verticesArray.contains(destinationNode) && !destinationNode.equals("")){
                verticesArray.add(destinationNode);
                destinationLocation = verticesArray.lastIndexOf(destinationNode);
            }
            else if (!destinationNode.equals("")){
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

}//end Graph class