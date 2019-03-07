package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.*;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author: Yi Jing
 */
public class DateBeforeCurrent {
    /**
     * Return null if all dates are before current date, return person if dates indicating errors.
     * @param file
     * @throws IOException
     * @throws GedcomParserException
     */
    public static ArrayList<Individual> beforeCurrent(String file) throws IOException, GedcomParserException {
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<Individual> errorperson = new ArrayList<Individual>();
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g
                .getIndividuals().values());
        for(Individual individual: everybody){
            if(individual.getEventsOfType(IndividualEventType.BIRTH).size() != 0) {
                List BIRTH = individual.getEventsOfType(IndividualEventType.BIRTH);
                String birth = ((IndividualEvent) BIRTH.get(0)).getDate().toString();
                if(Integer.parseInt(birth.substring(birth.length() - 4)) > currentyear){
                    errorperson.add(individual);
                }
            }
            if(individual.getEventsOfType(IndividualEventType.DEATH).size() != 0) {
                List DEATH = individual.getEventsOfType(IndividualEventType.DEATH);
                String death = ((IndividualEvent) DEATH.get(0)).getDate().toString();
                if(Integer.parseInt(death.substring(death.length() - 4)) > currentyear){
                    errorperson.add(individual);
                }
            }
        }

        List<Family> everyfamily = new ArrayList<Family>(g.getFamilies().values());
        for (Family family : g.getFamilies().values()){
            if (family.getHusband() != null && family.getWife() != null){
                List events = family.getEvents();
                if(events.size() != 0) {
                    String marrige = ((FamilyEvent) events.get(0)).getDate().toString();
                    if(Integer.parseInt(marrige.substring(marrige.length() - 4)) > currentyear){
                        if(!errorperson.contains(family.getHusband()) && !errorperson.contains(family.getWife())){
                            errorperson.add(family.getHusband().getIndividual());
                            errorperson.add(family.getWife().getIndividual());
                        }
                    }if(events.size() > 1){
                        String divorce = ((FamilyEvent) events.get(1)).getDate().toString();
                        if(Integer.parseInt(divorce.substring(divorce.length() - 4)) > currentyear){
                            if(!errorperson.contains(family.getHusband()) && !errorperson.contains(family.getWife())){
                                errorperson.add(family.getHusband().getIndividual());
                                errorperson.add(family.getWife().getIndividual());
                            }
                        }
                    }
                }
            }
        }

        return errorperson;
    }

    public static void main(String[] args) throws IOException, GedcomParserException {
        System.out.println(beforeCurrent("D:\\SSw555Group5\\Sprint1\\GEDCOMsourcefile\\bronte.ged"));
    }
}
