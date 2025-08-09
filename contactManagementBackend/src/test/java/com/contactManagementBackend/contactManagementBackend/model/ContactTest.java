package com.contactManagementBackend.contactManagementBackend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testGettersAndSetters() {
        Contact contact = new Contact();

        contact.setId(10L);
        assertEquals(10L, contact.getId());

        contact.setFirstName("Ali");
        assertEquals("Ali", contact.getFirstName());

        contact.setLastName("Khan");
        assertEquals("Khan", contact.getLastName());

        contact.setTitle("Ali Khan");
        assertEquals("Ali Khan", contact.getTitle());

        contact.setEmail("ali.khan@example.com");
        assertEquals("ali.khan@example.com", contact.getEmail());

        contact.setEmailLabel("work");
        assertEquals("work", contact.getEmailLabel());

        contact.setPhone("1234567890");
        assertEquals("1234567890", contact.getPhone());

        contact.setPhoneLabel("mobile");
        assertEquals("mobile", contact.getPhoneLabel());

        contact.setAddress("123 Street, City");
        assertEquals("123 Street, City", contact.getAddress());
    }
}
