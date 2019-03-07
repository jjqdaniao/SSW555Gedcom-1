package org.Gedom;

import junit.framework.TestCase;
import org.Gedcom.BirthBeforeMarriage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BirthBeforeMarriageTest extends TestCase {
    BirthBeforeMarriage bbm = new BirthBeforeMarriage();
    public void test1() throws FileNotFoundException {
        ArrayList list = new ArrayList();
        list.add("1783");
        assertEquals(bbm.birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"),list);
    }

    public void test2() throws FileNotFoundException {
        ArrayList list = new ArrayList();
        list.add("1814");
        assertEquals(bbm.birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte2.ged"),list);
    }

    public void test3() throws FileNotFoundException {
        ArrayList list = new ArrayList();
        list.add("1817");
        assertEquals(bbm.birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte3.ged"),list);
    }

    public void test4() throws FileNotFoundException {
        ArrayList list = new ArrayList();
        list.add("1744");
        assertEquals(bbm.birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte4.ged"),list);
    }

    public void test5() throws FileNotFoundException {
        ArrayList list = new ArrayList();
        list.add("1783");
        list.add("1776");
        assertEquals(bbm.birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte5.ged"),list);
    }

    public void test6() throws FileNotFoundException {
        ArrayList list = new ArrayList();
        assertEquals(bbm.birthMarri("src/resources/GEDCOMsourcefile/bronte.ged"),list);
    }
}