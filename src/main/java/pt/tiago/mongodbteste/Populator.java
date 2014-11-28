/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mongodbteste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pt.tiago.mondodbteste.dto.Adress;
import pt.tiago.mondodbteste.dto.Person;

/**
 *
 * @author NB20708
 */
public class Populator {

    public static List<Person> populate() {
        List<Person> personList = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            Person p1 = new Person();
            p1.setAge(i * r.nextInt(50));
            p1.setName("Pessoa" + i );
            p1.setWorking(true);
            p1.setWorkingYears(i * r.nextInt(50));
            Adress a1 = new Adress();
            a1.setCity("lisboa" + i);
            a1.setCountry("portugal" + i);
            a1.setHouseNumber(91);
            a1.setStreet("avenida das couves" + i);
            a1.setZip("2710-73" + i);
            p1.setAdress(a1);

            personList.add(p1);
        }

        return personList;
    }

}
