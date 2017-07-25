package Ex;

public class L_2 {

    public static void main(String[] args) {

        //Ex1

        /*cerinta

        Exerciţiul 1

        Trebuie să păstrăm în program anii de muncă și salariul unui angajat.
        Sarcina dvs. este să declarați variabilele care se pot utiliza în acestscop.
         */

        int years;
        double salary;

        /*Exerciţiul 2
        Trebuie să declarăm două variabile. O variabilă de tip double cu
        valoarea 15,757 și o variabilă întreagă de tip int. Trebuie să convertiți
        variabila cu virgulă mobilă într-o valoare întreagă.
        */

        double varDbl = 15.757;
        int varConv = (int) varDbl;
        System.out.println("Var converita " + varConv);



        /*
        Exercițiul 1
        Numele variabilelor trebuie schimbate astfel încât să fie corecte
        sintactic şi în acord cu convenţiile:
        String 1stUserName;
        int bdt; //date of birthday
        float o1; //first operand
         */
        String firstUserName;
        int birthDate;
        float firstOperand;

        /*
        Exercițiul 2
        Editaţi numele constantei:
        public static final short pageWidth = 800;
         */

        /*public static*/
        final short PAGE_WIDTH = 800;

        /*
        Exerciţiul 1:
        Unitate: Operatori
        Este necesar să creăm un program care adună două numere
        reprezentate prin două variabile. Primul număr este variabila x, cu
        valoarea 1, iar celălalt este variabila y, cu valoarea 2. Trebuie să
        atribuim variabilei z rezultatul adunării acestor două variabile.
         */

        //definire x
        int x = 1;
        //definire y
        int y = 2;
        //calcul z=x+y
        int z = x + y;
        //afisare z
        System.out.println("Valoare z = " + z);

        /*
        Exerciţiul 2
        Trebuie să creăm un program care calculează aria unui cerc.
         */

        //definire PI
        final double PI = 3.14;
        //definire raza
        double r = 15;
        //calcul arie PI*r^2
        double arie = PI * (r * r);
        //afisare arie
        System.out.println("Arie  = " + arie);


//end
    }
}