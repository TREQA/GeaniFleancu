import java.util.Arrays;


public class JavaTests {

    private int classVariable;

    private static int classVariable2;

    private static int variableA; // variabila de clasă

// variabile de instanţă

    private int variableB;

    private String variableC;

    private boolean variableD;

    private static final double PI = 3.14;


    public static void main(String[] args) {

        System.out.println("Geani test");
       /* System.out.println("Geani test2");
        System.out.println("Geani test3");*/

        Integer xn = 81;
        if (xn < 10) {
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

        System.out.println(xn.getClass());

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

        System.out.println("\n");


        JavaTests objClass = new JavaTests();

        objClass.classVariable = 7;

        System.out.println("cls var = " + objClass.classVariable);

        JavaTests.classVariable2 = 37;
        System.out.println("cls var2 = " + JavaTests.classVariable2);

        System.out.println("\n");

        System.out.println(variableA);

        System.out.println(objClass.variableB);

        System.out.println(objClass.variableC);

        System.out.println(objClass.variableD);

        int varE = 12312312;
        System.out.println(varE);

        System.out.println("\n");


        System.out.println(PI);

        int w = 55555;

        System.out.printf("The value of variable x is %d", w);

        double zz = 12.1313467445;

        System.out.printf("The value of variable x is %d%n " +
                "The value of variable y is %.4f", w, zz);

        System.out.println("\n");

        int zx = 3;

        int yx = zx;

        System.out.println(yx);

        System.out.println("\n");

        int a = 10;

        int b = a << 1;

        System.out.println(a); // 10

        System.out.println(b); // 20

        // metoda toBinaryString()returnează reprezentaţia binară string a numărului întreg (în baza 2)

        System.out.println(Integer.toBinaryString(a)); // 1010

        System.out.println(Integer.toBinaryString(b)); // 10100

        int aa = 10;

        int ab = ~aa;

        System.out.println(ab); // -11

        System.out.println(Integer.toBinaryString(ab));
        // 11111111111111111111111111110101
        System.out.println(Integer.toBinaryString(5 & 11)); // 1 tj. 0001

        System.out.println("\n");

        int[] arrayx = new int[5];
        arrayx[0] = 1314;
        arrayx[3] = 5;
        int[] array2 = {131, 312, 312, 321, 312};

        Object objectArray[] = {3, 6.5, 2, 6, 1, 6};

        System.out.println(arrayx.length);

        System.out.println("\n");

        int array[] = {3, 5, 2, 6, 1, 6};

        int array1[] = {12, 13, 14};

        int destArray[] = new int[array.length + array1.length];

        System.arraycopy(array, 0, destArray, 0, array.length);

        System.arraycopy(array1, 0, destArray, array.length, array1.length);

        System.out.println("Array length is: " + destArray.length);

        System.out.println("Elements are: ");

        System.out.println(Arrays.toString(destArray));
        System.out.println("\n");


        int arr[] = {3, 5, 2, 6, 1, 6};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
        int[] a6 = {5, 1, 3, 4, 2};

        Arrays.sort(a6);

        System.out.println(Arrays.binarySearch(a6, 2));

        System.out.println(Arrays.binarySearch(a6, 6));

        System.out.println("\n");

        int[] array3 = new int[6];

        Arrays.fill(array, 7);
        int[] array4 = new int[6];

        System.out.println("\n");

        int[][] matrix

                = {
                {1, 2, 3, 4},

                {5, 6, 7, 8},

                {9, 10, 11, 12}
        };

        System.out.println(matrix[1][2]);



    }
}
