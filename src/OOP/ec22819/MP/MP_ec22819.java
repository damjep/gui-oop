package OOP.ec22819.MP;

public class MP_ec22819 {
    public static void main(String[] args) {
        Visitable r = new Room_ec22819();
        Visitor v = new GUIVisitor_test(System.out,System.in);
        r.visit(v, Direction.FROM_SOUTH);
    }
}
