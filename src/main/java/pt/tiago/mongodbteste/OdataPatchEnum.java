/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mongodbteste;

import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

/**
 *
 * @author NB20708
 */
public enum OdataPatchEnum {

    
    PERSON {

                /**
                 * @return
                 */
                @Override
                public FilterProvider getDefaultFilters() {
                    Set<String> toIgnoreChallenge = new HashSet<String>();
                    toIgnoreChallenge.add("__id");
                    

                    SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept(toIgnoreChallenge);
                    FilterProvider filters = new SimpleFilterProvider().addFilter("challengeFilter", theFilter);

                    return filters;
                }

                /**
                 * @param fields
                 * @return
                 */
                @Override
                public FilterProvider getFiltersByFields(Set fields) {
                    SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
                    FilterProvider filters = new SimpleFilterProvider().addFilter("personFilter", theFilter);

                    return filters;
                }
            };

    /**
     *
     * @return
     */
    public abstract FilterProvider getDefaultFilters();

    /**
     *
     * @return
     */
    public abstract FilterProvider getFiltersByFields(Set fields);

}
