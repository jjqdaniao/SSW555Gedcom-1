package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarriageBeforeDivorce {
    public static ArrayList beforeDivorce(String file) throws IOException, GedcomParserException {
        ArrayList<Individual> errorperson = new ArrayList<Individual>();
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Family> everyfamily = new ArrayList<Family>(g.getFamilies().values());
        for (Family family : g.getFamilies().values()){
            List events = family.getEvents();
            String divorce = ((FamilyEvent) events.get(1)).getDate().toString();
            String marriage = ((FamilyEvent) events.get(0)).getDate().toString();
            if(Integer.parseInt(divorce.substring(divorce.length() - 4)) > Integer.parseInt(marriage.substring(divorce.length() - 4))){
                errorperson.add(family.getHusband().getIndividual());
                errorperson.add(family.getWife().getIndividual());
            }
        }
        return errorperson;
    }
}
