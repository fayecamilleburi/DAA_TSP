import java.util.*;

public class TSPSolver {

    //Declaring the values given
    String[] source = {"St. Peter", "St. John", "Lanao", "Maguindanao"};

    int[][] distance = {
        {0, 300, 150, 200},
        {150, 0, 200, 300}, 
        {100, 120, 0, 200}, 
        {200, 200, 100, 0}
    };

    //Method to solve TSP
    public void solveTSP() {
        //Initializing of variables
        String[] shortestPath = null;
        int minDistance = Integer.MAX_VALUE;

        //Calls permute() method to create all possible permutations
        ArrayList<String[]> permutation = permute(source); //Will return the values here

        //Iterates over each permutation generated (Loop)
        for (String[] p : permutation) {
            //Initializes totalDistance
            int totalDistance = 0;

            for (int i = 0; i < p.length - 1; i++) {
                int fromIndex = getIndex(p[i]);
                int toIndex = getIndex(p[i+1]);

                totalDistance += distance[fromIndex][toIndex];
            }

            totalDistance += distance[getIndex(p[p.length -1])][getIndex(p[0])];

            //Print out all possible permutations
            for (String location : p) {
                System.out.print(location + " -> ");
            }
            System.out.println("\nDistance: " + totalDistance);
            System.out.println();

            //Updating shortest path and distance if the current path is shorter
            if (totalDistance < minDistance) {
                minDistance = totalDistance;
                shortestPath = p;
            }
        }

        //Print optimal path and distance
        System.out.print("Shortest Path: ");
        for (String location : shortestPath) {
            System.out.print(location + " -> ");
        }
        System.out.println("\nDistance: " + minDistance);
    }

    //Method to generate all possible permutations
    private ArrayList<String[]> permute(String[] source) {
        //Initializes list to store the permutations
        ArrayList<String[]> permutation = new ArrayList<>();

        //Recursive method to generate permutations
        permuteSolve(source, 0, permutation);

        return permutation;
    }

    //Recursive method to generate permutations
    private void permuteSolve(String[] source, int index, ArrayList<String[]> permutation) {
        //If index reaches the length of source array, adds the current permutation to the list
        if (index == source.length - 1) {
            String[] permutations = source.clone(); //clone() method makes the copy of the data itself
            permutation.add(permutations);
        } else {
            for (int i = index; i < source.length; i++) {
                swap(source, index, i); //Swapping elements
                permuteSolve(source, index + 1, permutation); //Recursive permutation
                swap(source, index, i); //Maintaining original array for next permutations
            }
        }
    }

    //Method to swap elements in array
    private void swap(String[] source, int x, int y) {
        String temp = source[x];
        source[x] = source[y];
        source[y] = temp;
    }

    //Method to get the indices of location in source array
    private int getIndex(String location) {
        for (int i = 0; i < source.length; i++) {
            if(source[i].equals(location)) {
                return i;
            }
        }
        return -1; //Default; or not found
    }
}