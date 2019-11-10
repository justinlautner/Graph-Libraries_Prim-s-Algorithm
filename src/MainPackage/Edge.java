package MainPackage;

class Edge {

    /*
     *  sourceNode is not used to achieve the result, but I left it here in case it will be needed later
     *  I instead used the verticesArray to show the relationship between nodes
     *  say, for instance, if I later need to print out each edge it will be prepared
     *  plus, it keeps the entirety of the data structure intact
     */
    String sourceNode;
    String destinationNode;

    Edge(String sourceNode, String destinationNode)    {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
    }//end Edge method

}//end Edge class
