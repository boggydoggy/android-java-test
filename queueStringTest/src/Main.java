import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> queue = new ArrayList<>();
        queue.add("this");
        queue.add("is");
        queue.add("a");
        queue.add("test");

        System.out.println(queue);
        System.out.println(queue.get(1));

        queue.remove(1);
        System.out.println(queue);
        System.out.println(queue.get(1));
    }
}