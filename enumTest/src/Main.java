public class Main {
    public enum STATE {
        CRASHED,
        START,
        DIED,
        KILL,
    }
    public static void main(String[] args) {
        STATE state1 = STATE.START;
        STATE state2;

        System.out.println(state1);
        if (state2==null) {
            System.out.println("heloo");
        }
    }
}