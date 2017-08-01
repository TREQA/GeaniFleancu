package Ex;

import java.io.*;
import java.util.ArrayList;

public class L_7 {
    public static void main(String[] args) throws IOException {

        /*
        Problemă:
        Avem fișierul users.txt cu următorul conținut:
        id:01|firstName:Petar|lastName:Petrovic|jmbg:1234567890123
        id:02|firstName:Jovan|lastName:Jovanovic|jmbg:1234567890124
        id:03|firstName:Nikola|lastName:Nikolic|jmbg:1234567890125
        Creați o aplicație care va citi fișierul și va afișa conținutul în următorul
        format:
        User Id: 01
        First name: Petar
        Last name: Petrovic
        Jmbg: 1234567890123
        ------------------------------------
        User Id: 02
        First name: Jovan
        Last name: Jovanovic
        Jmbg: 1234567890124
         */
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] user = line.split("\\|");
            System.out.println("User Id: " + user[0].split("\\:")[1]);
            System.out.println("First name: " + user[1].split("\\:")[1]);
            System.out.println("Last name: " + user[2].split("\\:")[1]);
            System.out.println("Jmbg: " + user[3].split("\\:")[1]);

            System.out.println("------------------------------------");
        }
        br.close();


    /*
    Exercițiul 2
    Creați o aplicație care concatenează fișierele (pune două fișiere într-
    unul singur). Denumirile acestor fișiere sunt: firstFile.txt și
    secondFile.txt. Uniți conținuturile acestor două fișiere și puneți-le în
    fișierul denumit thirdFile.txt.
     */

        String tmpLine;
        BufferedWriter bw = new BufferedWriter(new FileWriter("thirdFile.txt"));
        ArrayList<BufferedReader> filesToRead = new ArrayList<BufferedReader>();
        filesToRead.add(new BufferedReader(new FileReader("users.txt")));
        filesToRead.add(new BufferedReader(new FileReader("myFile3.txt")));
        for (BufferedReader br2 : filesToRead) {
            while ((tmpLine = br2.readLine()) != null) bw.write(tmpLine + "\n");
            br.close();
        }
        bw.close();
    }


//////////////////////end
}

