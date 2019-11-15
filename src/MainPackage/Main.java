package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static StringBuilder line = new StringBuilder();
    private static ArrayList<String> array = new ArrayList<>();
    private static ArrayList<String> verticesList = new ArrayList<>();
    private static int vertices;
    //private static boolean weighted, directed;

    public static void main(String[] args) throws FileNotFoundException {

        //Start program, ask user which file to load
        System.out.println("Welcome to Graph Libraries! A program that will create a data structure based on your input!");
        System.out.print("Please enter the name of the file you wish to load: ");

        //Input to recieve user request
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        System.out.println();

        //Load file and get header
        File file = new File(choice);
        Scanner sc = new Scanner(file);
        String header = getHeader(sc);

        //Determine if weighted graph
        boolean weighted, directed;
        if (header.contains("unweighted")){
            weighted = false;
        }
        else{
            weighted = true;
        }

        //Get size of vertices to later set size of linkedlist
        ArrayList<String> arrayTemp = new ArrayList<>();
        while (sc.hasNextLine()){

            String temp = sc.nextLine();
            array.add(temp);

            String var1 = temp.substring(0, temp.indexOf("="));
            String var2 = temp.substring(temp.indexOf("=") + 1, temp.lastIndexOf("="));
            if (!arrayTemp.contains(var1)){
                arrayTemp.add(var1);
                vertices++;
            }
            if(!arrayTemp.contains(var2)){
                arrayTemp.add(var2);
                vertices++;
            }

        }//end while loop

        //Instantiate graph size
        Graph.startGraph(weighted, vertices);

        //switch to determine which type of graph to create
        switch (header){
            case "directed unweighted":
                directed = true;
                for (String element : array) {
                    line.setLength(0);
                    line.append(element);
                    Graph.addEdge(directed, line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=") + 1, line.length()));
                }
                Graph.printGraph();
                break;
            case "directed weighted":
                directed = true;
                for (String item : array) {
                    line.setLength(0);
                    line.append(item);
                    //Loop to determine if there is a destination node
                    int j = 0;
                    for (int i = 0; i < line.length(); i++){
                        if (line.charAt(i) == '='){
                            j++;
                        }
                    }
                    if (j == 2){
                        Graph.addEdge(directed, line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=") + 1, line.lastIndexOf("=")), line.substring(line.lastIndexOf("=") + 1, line.length()));
                    }
                    else{
                        Graph.addEdge(directed, line.substring(0, line.indexOf("=")), "", line.substring(line.lastIndexOf("=") + 1, line.length()));
                    }
                }
                Graph.printWeighted();
                break;
            case "undirected unweighted":
                directed = false;
                for (String value : array) {
                    line.setLength(0);
                    line.append(value);
                    Graph.addEdge(directed, line.substring(0, line.indexOf("=")), line.substring(line.lastIndexOf("=") + 1, line.length()));
                }
                Graph.printGraph();
                break;
            case "undirected weighted":
                directed = false;
                for (String s : array) {
                    line.setLength(0);
                    line.append(s);
                    //Loop to determine if there is a destination node
                    int j = 0;
                    for (int i = 0; i < line.length(); i++){
                        if (line.charAt(i) == '='){
                            j++;
                        }
                    }
                    if (j == 2){
                        Graph.addEdge(directed, line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=") + 1, line.lastIndexOf("=")), line.substring(line.lastIndexOf("=") + 1, line.length()));
                    }
                    else{
                        Graph.addEdge(directed, line.substring(0, line.indexOf("=")), "", line.substring(line.lastIndexOf("=") + 1, line.length()));
                    }
                }
                Graph.printWeighted();
                Graph.Prims_Algorithm();
                break;
            default:
                System.out.println("Invalid Header");
                System.exit(1);
                break;
        }//end switch

    }//end main method

    private static String getHeader(Scanner sc) {

        return sc.nextLine();

    }//end getHeader method

}//end main class
