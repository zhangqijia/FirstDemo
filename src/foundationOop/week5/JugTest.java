package foundationOop.week5;

public class JugTest {

    public static void main(String[] args) {
        Jug jug3 = new Jug(3);
        Jug jug2 = new Jug(2);
        Jug jug5 = new Jug(5);

        jug2.fill();
        jug3.fill();

        jug2.pourInto(jug5);
        jug3.pourInto(jug5);

        jug5.display();

        jug5.pourInto(jug2);
        jug5.display();

        jug5.pourInto(jug3);
        jug5.display();

    }
}
