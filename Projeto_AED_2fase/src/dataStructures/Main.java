package dataStructures;

public class Main {
    public static void main(String[] args) {
        OrderedDictionary<Integer, String> ages = new AVLTree<>();

        ages.insert(10, "A");
        ages.insert(20, "B");
        ages.insert(5, "C");

        // Test case 2: Removal and balancing
        ages.remove(20);

        // Test case 3: Insertion, removal, and balancing
        ages.insert(8, "D");
        ages.insert(25, "E");
        ages.remove(5);

        ages.remove(25);
        ages.insert(28, "W");
        ages.insert(16, "L");
        ages.insert(4, "M");
        ages.insert(15, "K");
        ages.insert(45, "T");

        ages.insert(89, "W");
        ages.insert(78, "L");
        ages.insert(-5000, "M");
        ages.insert(233, "K");
        ages.insert(777, "T");

        ages.remove(89);
        ages.remove(78);
        ages.remove(-5000);
        ages.remove(233);
        ages.remove(777);

        // Test case 5: Insertion and removal of multiple nodes
        ages.insert(32, "F");
        ages.remove(10);




        /*
        ages.insert(-1, "z");
        ages.insert(10000, "k");
        ages.insert(50, "q");
        ages.insert(-5000, "w");
        */
        Iterator<Entry<Integer, String>> it = ages.iterator();

        while(it.hasNext()){
            System.out.println(it.next().getKey());
        }


    }
}
