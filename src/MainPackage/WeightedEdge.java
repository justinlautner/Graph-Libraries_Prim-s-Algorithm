package MainPackage;

public class WeightedEdge extends Edge  {

    String weight;

    WeightedEdge(String sourceNode, String destinationNode, String weight)    {
        super(sourceNode, destinationNode);
        this.weight = weight;
    }//end WeightedEdge method

}//end WeightedEdge class
