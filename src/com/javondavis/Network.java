package com.javondavis;

import java.util.*;

/**
 * Class representing a network of nodes represented by integers
 * Assumes integers representing nodes can be non-contiguous
 */
public class Network {

    private final int numElements;
    private Map<Integer, Set<Integer>> adjMap;
    private Set<Integer> elements = new HashSet<>();

    /**
     * @param elementCount the number of elements in the network
     * @throws Exception
     */
    public Network(int elementCount) throws Exception {
        if(elementCount < 0) {
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
        if(node1 < 0 || node2 < 0) {
            throw new Exception("Invalid value for either parameter 1 or 2, value must a positive integer");
        }

        // if network full and one of the nodes doesn't exist in network
        if((elements.size() == numElements) && (!elements.contains(node1) || !elements.contains(node2))) {
            throw new Exception("Size out of bounds, can't add new node network full");
        }

        // if we only have space for one and both don't exist in the network
        if(elements.size() == numElements - 1) {
            if(!elements.contains(node1) && !elements.contains(node2)) {
                throw new Exception("Size out of bounds, can't add two new nodes to network");
            }
        }

        elements.add(node1);
        elements.add(node2);

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
        if(node1 < 0 || node2 < 0) {
            throw new Exception("Invalid value for either parameter 1 or 2, value must a positive integer");
        }

        if(!elements.contains(node1)) {
            throw new Exception("Node "+node1+" does't exist in network");
        }
        if(!elements.contains(node2)) {
            throw new Exception("Node "+node2+" does't exist in network");
        }


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

}
