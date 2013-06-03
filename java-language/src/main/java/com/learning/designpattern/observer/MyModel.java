package com.learning.designpattern.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MyModel {

    private List<Person> persons = new ArrayList<Person>();
    private List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();

    public class Person {

        private String firstName;

        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {

            return firstName;
        }

        public void setFirstName(String firstName) {
            notifyListeners(this, "firstName", 
            		this.firstName, 
                    this.firstName = firstName);
            // Remark 
          // this.firstName = firstName sends out the
          // set the field and send out the new value

            // Longer version:
            // String oldName = this.firstName;
            // notifyListeners(this, "firstName", 
            //  oldName, firstName);    
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            notifyListeners(this, "lastName", 
            		this.lastName, 
                    this.lastName = lastName);
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public MyModel() {
        // Just for testing we hard-code the persons here:
        persons.add(new Person("Lars", "Vogel"));
        persons.add(new Person("Jim", "Knopf"));
        persons.add(new Person("Miguel", "Olave"));
    }

    private void notifyListeners(Object object, String property, String oldValue, String newValue) {
        for (PropertyChangeListener name : listeners) {
            name.propertyChange(new PropertyChangeEvent(this, 
                            property, 
                            oldValue, 
                            newValue));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listeners.add(newListener);
    }

} 