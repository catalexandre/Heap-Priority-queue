public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        SmarterPQ<Character, Integer> queue = new SmarterPQ<>(new NumericalValue());

        queue.insert('I', 8);
        queue.insert('C', 2);
        queue.insert('B', 1);
        queue.insert('D', 3);
        queue.insert('E', 4);
        queue.insert('F', 5);
        queue.insert('G', 6);
        queue.insert('H', 7);
        queue.insert('A', 0);

        queue.printArray();
    }
}