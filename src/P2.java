import java.util.ArrayList;
import java.util.List;

public class P2 {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(10);
        a.add(20);
        a.add(30);
        a.add(40);

        System.out.println(a);

        a.remove(0);
        System.out.println(a);
        a.set(0,100);
        System.out.println(a);
    }
}
