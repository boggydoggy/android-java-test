import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] sa = {"armeabi-v7a"};

        System.out.println(Arrays.toString(sa));

        String s = sa[0];

        System.out.println(s);

        String[] sp = s.split(",");

        String s2 = sp[0];

        System.out.println(s2);
    }
}