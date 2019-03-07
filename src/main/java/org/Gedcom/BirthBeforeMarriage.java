package org.Gedcom;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author Yi Jing
 */
public class BirthBeforeMarriage {
    private static Scanner scan;
    private static BufferedWriter writer;

    /**
     * If found BIRTH before MARRY DATE, return thoes DATES as list. If not, return empty list.
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList birthMarri(String file) throws FileNotFoundException {
        ArrayList invalid = new ArrayList();
        ArrayList family = new ArrayList();
        scan = new Scanner(new FileReader(file));
        String reader = scan.nextLine();
        String string = "";

        while(scan.hasNextLine()){
            string += reader;
            reader = scan.nextLine();
        }
        int a = 0;
        int b = 0;
        String[] str = string.split("0 @F");
        for(int i = 0; i < str.length; i++){
            family.add(str[i]);
        }

        int fam[][] = new int[][]{{Integer.parseInt(family.get(1).toString().substring(20,21)),Integer.parseInt(family.get(1).toString().substring(34,35)),Integer.parseInt(family.get(1).toString().substring(61,65))},{Integer.parseInt(family.get(2).toString().substring(20,21)),Integer.parseInt(family.get(2).toString().substring(34,35)),Integer.parseInt(family.get(2).toString().substring(56,60))},{Integer.parseInt(family.get(3).toString().substring(19,21)),Integer.parseInt(family.get(3).toString().substring(33,35)),Integer.parseInt(family.get(3).toString().substring(49,53))},{Integer.parseInt(family.get(4).toString().substring(19,21)),Integer.parseInt(family.get(4).toString().substring(33,35)),Integer.parseInt(family.get(4).toString().substring(49,53))}};


        String[] indi = family.get(0).toString().split("@ INDI");

        ArrayList Birth = new ArrayList();
        for(int i = 1; i < indi.length; i++){
            a = indi[i].lastIndexOf("BIRT");
            if(indi[i].lastIndexOf("BIRT") != -1) {
                Birth.add(indi[i].substring(a + 11, a + 24));
            }else{
                Birth.add("No Data");
            }
        }

        ArrayList<Integer> birth = new ArrayList();
        birth.add(Integer.parseInt(Birth.get(0).toString().substring(7,11)));
        birth.add(Integer.parseInt(Birth.get(1).toString().substring(7,11)));
        birth.add(Integer.parseInt(Birth.get(2).toString().substring(7,11)));
        birth.add(Integer.parseInt(Birth.get(3).toString().substring(6,10)));
        birth.add(Integer.parseInt(Birth.get(4).toString().substring(7,11)));
        birth.add(Integer.parseInt(Birth.get(5).toString().substring(7,11)));
        birth.add(Integer.parseInt(Birth.get(6).toString().substring(7,11)));
        birth.add(Integer.parseInt(Birth.get(7).toString().substring(7,11)));
        birth.add(0);
        birth.add(0);
        birth.add(Integer.parseInt(Birth.get(10).toString().substring(0,4)));
        birth.add(Integer.parseInt(Birth.get(11).toString().substring(4,8)));
        birth.add(Integer.parseInt(Birth.get(12).toString().substring(0,4)));
        birth.add(Integer.parseInt(Birth.get(13).toString().substring(0,4)));

        for(int i = 0; i < fam.length; i++){
            if(birth.get(fam[i][0] - 1) > fam[i][2]){
                invalid.add(Integer.toString(birth.get(fam[i][0])));
            }
            if(birth.get(fam[i][1] - 1) > fam[i][2]){
                invalid.add(Integer.toString(birth.get(fam[i][1])));
            }
        }

        return invalid;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList list = new ArrayList();
        list = birthMarri("GEDCOMsourcefile/bronte.ged");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
