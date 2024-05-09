package mcqs;

public class StaticBlock {

    private static String name = "Gaurav";
    private static int instances = 0;

    static {
        name = "Kabra";
        instances++;
    }

    public static void main(String[] args) {
        StaticBlock s1 = new StaticBlock();
        StaticBlock s2 = new StaticBlock();

        System.out.println(name);
        System.out.println(instances);
    }
}

/*
Kabra
1
 */