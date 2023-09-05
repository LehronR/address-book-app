package com.lehronrobinson.addressbook.repository;

import com.lehronrobinson.addressbook.model.Person;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PersonRepository extends ListCrudRepository<Person, Long> {
	List<Person> findByAddressBookId(Long id);
	List<Person> findByFirstNameContaining(String firstName);
	List<Person> findByLastNameContaining(String lastName);
	List<Person> findByPhoneNumberContaining(String phoneNumber);
	List<Person> findByEmailContaining(String email);
}
