package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.*;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DivorceBeforeDeath {
    public static ArrayList beforeDeath(String file) throws IOException, GedcomParserException {
        ArrayList<Individual> errorperson = new ArrayList<Individual>();
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Family> everyfamily = new ArrayList<Family>(g.getFamilies().values());
        for (Family family : g.getFamilies().values()){
            List events = family.getEvents();
            List husbanddeath = family.getHusband().getIndividual().getEventsOfType(IndividualEventType.DEATH);
            List wifedeath = family.getWife().getIndividual().getEventsOfType(IndividualEventType.DEATH);
            String divorce = ((FamilyEvent) events.get(1)).getDate().toString();
            String husbandDeath = ((IndividualEvent) husbanddeath.get(0)).getDate().toString();
            String wifeDeath = ((IndividualEvent) wifedeath.get(0)).getDate().toString();
            if(Integer.parseInt(divorce.substring(divorce.length() - 4)) > Integer.parseInt(husbandDeath.substring(divorce.length() - 4))){
                errorperson.add(family.getHusband().getIndividual());
            }
            if(Integer.parseInt(divorce.substring(divorce.length() - 4)) > Integer.parseInt(wifeDeath.substring(divorce.length() - 4))){
                errorperson.add(family.getWife().getIndividual());
            }
        }
        return errorperson;
    }
}
