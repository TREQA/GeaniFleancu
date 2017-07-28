import Ex.*;

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

        System.out.println("\n");

        int xi = 0;

        if (xi != 0) {
            System.out.println("x is different from zero");
        } else {
            System.out.println("x is equal to zero");
        }

        System.out.println("\n");

        int num = 15;
        if (num < 10) {
            System.out.println("number < 10");
        } else if (num > 10) {
            System.out.println("num > 10");
        } else if (num == 10) {
            System.out.println("num = 10");
        }

        System.out.println("\n");

        boolean isHappy = true;

        String mood = (isHappy == true) ? "I'm Happy!" : "I'm Sad!";
        System.out.println(mood);

        System.out.println("\n");

        int x = 36;

        if ((x > 1 && x < 25 && x != 10) || (x == 35))

            System.out.println("Condition is fulfilled");

        else

            System.out.println("Condition is not fulfilled");


        System.out.println("\n");

        int xs = 5;

        switch (xs)

        {

            case 1:

                System.out.println("x = 1");

                break;

            case 2:

                System.out.println("x = 2");

                break;

            case 3:

                System.out.println("x = 3");

                break;

            default:

                System.out.println("x!=1, x!=2, x!=3");

        }

        System.out.println("\n");

        String carMake = "Ford";

        int doors = 4;
        switch (carMake) {

            case "Ford":

                switch (doors) {

                    case 3:

                        System.out.println("You drive a Ford with three doors.");

                        break;

                    case 4:

                        System.out.println("You drive a Ford with four doors.");

                        break;

                    case 5:

                        System.out.println("You drive a Ford with five doors.");

                        break;

                }

                break;

            default:

                System.out.println("We're sorry. You do not drive a Ford car.");

                break;

        }


        System.out.println("\n");

        int counter;
        for (counter = 0; counter <= 5; counter++) {

            System.out.println(counter);

        }

        System.out.println(counter);

        System.out.println("\n");


        String[] colors = {"red", "green", "blue", "yellow", "white"};

        for (int i = 0; i < colors.length; i++)

        {

            System.out.println("Color of element " + i + " is " + colors[i] + ".");
        }


        System.out.println("\n");

        int i = 0;

        for (i = 0; i < 33; i++)

            System.out.println(i);

        System.out.println("\n");


        int[] numbers = {3, 5, 9, 45, 6546, 8756123, 65};

        System.out.println("Numbers in this array are: ");

        for (int number : numbers) {

            System.out.println(number);
        }

        System.out.println("\n");

        for (int yy = 0; yy < 10; yy++)

        {

            System.out.print("\n" + yy + ":");

            for (int u = 0; u < 10; u++)

            {

                System.out.print(u);

                if (u == 5)

                    break;

            }

        }

        System.out.println("\n");

        int number = 5;

        do {

            System.out.println(number);

            number--;

        }

        while (number >= 2);

        System.out.println("\n");

        Car car1 = new Car();
        Car car2 = new Car("Ford");

        Car car3 = new Car("Ford", "Fiesta");
        Car car4 = new Car("Ford", "Fiesta", 3);

        System.out.println("\n");

        car4.printDetails();

        System.out.println("\n");

        Conversions.f2c(117.3);

        int xm = 5;
        System.out.println("This is initial value: " + xm);
        passMethod(xm);
        System.out.println("This is the value after the completion of the method: " + xm);

        System.out.println("\n");

        int xrt = 5;


        ReferenceType rt = new ReferenceType();

        System.out.println("This is initial value: " + rt.xrt);
        passMethodrt(rt);
        System.out.println("This is the value after the completion of the method: " + rt.xrt);

        System.out.println("\n");

        getPerimeter(13, 13, 13);

        //end
    }

    public static void passMethod(int xm) {
        xm = 10;
        System.out.println("This is the value from method: " + xm);
    }

    public static void passMethodrt(ReferenceType rt) {
        rt.xrt = 10;
        System.out.println("This is the value from method: " + rt.xrt);
    }

    public static void getPerimeter(int... edges) {
        int perimeter = 0;
        if (edges.length < 3) {
            System.out.println("Polygon must have more than two sides.");
        } else {
            for (int i = 0; i < edges.length; i++) {
                perimeter += edges[i];
            }
            System.out.println(perimeter);
        }

    }
}


