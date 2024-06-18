public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        SmarterPQ<Character, Integer> queue = new SmarterPQ<>(new NumericalValue());

        queue.insert('C', 2);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('B', 1);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('D', 3);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('E', 4);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('F', 5);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('G', 6);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('H', 7);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('I', 8);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('A', 0);
        System.out.println();
        queue.printArray();
        System.out.println();
        
        for(int i = 0; i <= 8; i++) {
            System.out.println(queue.removeTop().getKey());
            queue.printArray();
            System.out.println();
        }

        queue.insert('C', 2);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('B', 1);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('D', 3);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('E', 4);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('F', 5);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('G', 6);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('H', 7);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('I', 8);
        System.out.println();
        queue.printArray();
        System.out.println();
        queue.insert('A', 0);
        System.out.println();
        queue.printArray();
        System.out.println();

        queue.toggle();
        
        for(int i = 0; i <= 8; i++) {
            System.out.println(queue.removeTop().getKey());
            queue.printArray();
            System.out.println();
        }

    }
}