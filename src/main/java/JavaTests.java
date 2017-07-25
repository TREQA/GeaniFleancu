public class JavaTests {

    public static void main(String[] args) {

        System.out.println("Geani test");
       /* System.out.println("Geani test2");
        System.out.println("Geani test3");*/

        Integer x = 81;
        if (x < 10) {
            System.out.println("\n" + "Ei manacrena");
        } else {
            System.out.println("\n" + "no macarena");
        }

        Object obj = new Object();

        int variableNum = 32;

        int[] anArray;

        int decVal = 26;

        int octVal = 032; // 2 * 8^0 + 3 * 8^1 = 2 + 24 = 26

        int hexVal = 0x1a; // 10 * 16^0 + 1 * 16^1 = 10 + 16 = 26

        int binVal = 0b11010; // 2^1 + 2^3 + 2^4 = 2 + 8 + 16 = 26

        System.out.println(x.getClass());

        double z = 10.5;

        int y = (int) z;

        System.out.println(y);


        String geaniString = "Geani String test";

        System.out.println("\n" + geaniString);


        long creditCardNumber = 1234_5678_9012_3456L;

        long socialSecurityNumber = 999_99_9999L;

        float pi = 3.14_15F;

        long hexBytes = 0xFF_EC_DE_5E;

        long hexWords = 0xCAFE_BABE;

        long maxLong = 0x7fff_ffff_ffff_ffffL;

        byte nybbles = 0b0010_0101;

        long bytes = 0b11010010_01101001_10010100_10010010;

    }
}
