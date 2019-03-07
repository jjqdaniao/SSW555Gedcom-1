package org.Gedom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.Gedcom.DateBeforeCurrent.beforeCurrent;
import static org.Gedcom.DateBeforeCurrent.beforeCurrent;
import static org.junit.Assert.*;

public class DateBeforeCurrentTest {

    @Test
    public void beforeCurrentTest() throws IOException, GedcomParserException {
        ArrayList list = new ArrayList();
        assertEquals(list, beforeCurrent("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
}