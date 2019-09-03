package com.javondavis;

import java.util.*;

/**
 * Class representing a network of nodes represented by integers
 * Assumes integers representing nodes are contiguous integers based on description
 */
public class Network {

    private final int numElements;
    private Map<Integer, Set<Integer>> adjMap;

    /**
     * @param elementCount the number of elements in the network
     * @throws Exception
     */
    public Network(int elementCount) throws Exception {
        if(elementCount <= 0) {
            throw new Exception("Element count parameter must be a positive integer.");
        }
        this.numElements = elementCount;
        this.adjMap = new HashMap<>();
    }

    /**
     * Makes a connection in the network between two nodes
     * @param node1 the integer representing a node
     * @param node2 the integer representing a node
     * @throws Exception
     */
    public void connect(int node1, int node2) throws Exception {
        validateNodes(node1, node2);

        if(adjMap.containsKey(node1)) {
            adjMap.get(node1).add(node2);
        } else {
            Set<Integer> connections = new HashSet<>();
            connections.add(node2);
            adjMap.put(node1, connections);
        }

        if(adjMap.containsKey(node2)) {
            adjMap.get(node2).add(node1);
        } else {
            Set<Integer> connections = new HashSet<>();
            connections.add(node1);
            adjMap.put(node2, connections);
        }
    }

    /**
     * Checks if two nodes are connected in the network, assumes all noes are connected to themselves
     * @param node1 the integer representing a node
     * @param node2 the integer representing a node
     * @throws Exception
     * @return true if there's a connection, false otherwise
     */
    public boolean query(int node1, int node2) throws Exception {
        validateNodes(node1, node2);

        List<Integer> toVisit = new ArrayList<Integer>(this.adjMap.get(node1));
        Set<Integer> visited = new HashSet<>();

        while (!toVisit.isEmpty()) {
            if(toVisit.get(0) == node2) {
                return true;
            }

            int currNode = toVisit.remove(0);
            visited.add(currNode);
            if(this.adjMap.containsKey(currNode)) {
                for(int n: this.adjMap.get(currNode)) {
                    if(!visited.contains(n)) {
                        toVisit.add(n);
                    }
                }
            }

        }
        return false;
    }

    private void validateNodes(int node1, int node2) throws Exception {
        if(node1 < 0 || node2 < 0) {
            throw new Exception("Invalid value for either parameter 1 or 2, value must a positive integer");
        }

        if(node1 > numElements) {
            throw new Exception("Node "+node1+" does't exist in network");
        }
        if(node2 > numElements) {
            throw new Exception("Node "+node2+" does't exist in network");
        }
    }

}
