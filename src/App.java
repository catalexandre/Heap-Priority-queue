public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        //creates the queue at length two so that we can test the expansion
        SmarterPQ<Character, Integer> queue = new SmarterPQ<>(new NumericalValue(), 2);

        //insterting in the MAX priority
        queue.insert('A', 1);

        Entry<Character, Integer> removeEntry = queue.top();

        queue.insert('B', 7);
        queue.insert('C', 2);

        //removing entry with value A
        queue.remove(removeEntry);

        queue.insert('D', 4);
        //changing entry with value B to -9
        queue.replaceKey(queue.top(), -9);
        System.out.println(queue.top().getValue());

        Entry<Character, Integer> DEntry = queue.top();

        //prints states and toggles
        System.out.println(queue.state());
        queue.toggle();
        System.out.println(queue.state());

        //testEntry will be entry with value b
        Entry<Character, Integer> testEntry = queue.top();
        queue.insert('E', 5);

        queue.insert('F', 0);
        queue.insert('G', -1);
        //this will place B as the fifth value in the queue
        System.out.println("previous test entry key: " + queue.replaceKey(testEntry, 3));

        //removing entry with value D and key 4 that was converted to value L and key 2
        System.out.println(queue.replaceValue(DEntry, 'L'));
        System.out.println(queue.replaceKey(DEntry, 2));
        Entry<Character, Integer> removedDEntry = queue.remove(DEntry);
        System.out.println("removed entry value: " + removedDEntry.getValue());
        System.out.println("removed entry key: " + removedDEntry.getKey());

        System.out.println("Final queue-----------------");

        //size of queue before removing everything
        System.out.println("queue item number: " + queue.getSize());
        
        //replacing value of top, which is G to K
        System.out.println("previous top value: " + queue.replaceValue(queue.top(), 'K'));

        //prints states and toggles
        System.out.println(queue.state());
        queue.toggle();
        System.out.println(queue.state());

        //removing everything in order of priority from smallest key to biggest, numerically
        while(!queue.isEmpty()) {
            Entry<Character, Integer> topEntry = queue.removeTop();
            System.out.println(topEntry.getValue());
        }

        //size once empty
        System.out.println("queue item number: " + queue.getSize());

    }
}