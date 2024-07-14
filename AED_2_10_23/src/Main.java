import java.io.*;
import java.util.Scanner;

import dataStructures.*;

public class Main {

    private static final String ENQUEUE = "ENQ";
    private static final String DEQUEUE = "DEQ";
    private static final String DEQUEUE_ALL = "DEQ-ALL";
    private static final String INVERT = "INVERT";
    private static final String EXIT = "XS";

    private static final String ENQUEUE_OK = "Enqueue efectuado com sucesso.";
    private static final String DEQUEUE_OK = "Dequeue efectuado com sucesso.";
    private static final String EMPTY_QUEUE = "Fila vazia.";
    private static final String DEQUEUE_ALL_OK = "Dequeue de todos os elementos da fila:";
    private static final String INVERT_OK = "Inversao da fila efectuada com sucesso.";


    private static final String DATA_FILE = "storedqueue.dat";

    /**
     * @param args
     */
    public static void main(String[] args) {
        InvertibleQueue<Integer> queue = load();
        Scanner in = new Scanner(System.in);
        String cmd = in.next().toUpperCase();
        while (!cmd.equals(EXIT)) {
            switch (cmd) {
                case ENQUEUE :
                    enqueue(in, queue);
                    break;
                case DEQUEUE :
                    dequeue(in, queue);
                    break;
                case INVERT:
                    invert(in, queue);
                    break;
                case DEQUEUE_ALL :
                    dequeueAll(in, queue);
                default:
                    break;
            }
            System.out.println();
            cmd = in.next().toUpperCase();
        }
        save(queue);
    }

    private static void enqueue(Scanner in, InvertibleQueue<Integer> queue) {
        int element = in.nextInt();
        in.nextLine();
        queue.enqueue(element);
        System.out.println(ENQUEUE_OK);
    }

    private static void dequeue(Scanner in, InvertibleQueue<Integer> queue) {
        try {
            int element = queue.dequeue();
            System.out.println(element);
            System.out.println(DEQUEUE_OK);
        }
        catch (EmptyQueueException E) {
            System.out.println(EMPTY_QUEUE);
        }
    }

    private static void invert(Scanner in, InvertibleQueue<Integer> queue) {
        queue.invert();
        System.out.println(INVERT_OK);
    }

    private static void dequeueAll(Scanner in, InvertibleQueue<Integer> queue) {
        if (queue.isEmpty())
            System.out.println(EMPTY_QUEUE);
        else {
            System.out.println(DEQUEUE_ALL_OK);
            while (!queue.isEmpty())
                System.out.println(queue.dequeue());
        }
    }

    @SuppressWarnings("unchecked")
    private static void save(InvertibleQueue<Integer> queue){
        //COMPLETAR
        try {
            ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(DATA_FILE));
            out.writeObject(queue);
            out.flush();
            out.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static InvertibleQueue<Integer> load() {
        //COMPLETAR
        InvertibleQueue<Integer> q = new InvertibleQueueInList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(DATA_FILE));
            q = (InvertibleQueue<Integer>) in.readObject();
            in.close();
        }catch(IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return q;
    }
}
