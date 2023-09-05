package com.lehronrobinson.addressbook.controller;

import com.lehronrobinson.addressbook.model.AddressBook;
import com.lehronrobinson.addressbook.model.Person;
import com.lehronrobinson.addressbook.repository.AddressBookRepository;
import com.lehronrobinson.addressbook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressBookRepository addressBookRepository;

	@GetMapping("addressbook/person/newentry")
	public String createNewPersonEntry(Person person, AddressBook addressBook, Model model) {
		List<Person> people = personRepository.findByAddressBookId(1L);

		List<AddressBook> addressBookList = new ArrayList<>(addressBookRepository.findAll());

		model.addAttribute("person", person);
		model.addAttribute("people", people);
		model.addAttribute("addressBook", addressBook);
		model.addAttribute("addressBookList", addressBookList);

		System.out.println(personRepository.findByAddressBookId(1L));

		return "/person/newentry";
	}

	@GetMapping("/addressbook/person/delete")
	public String showAllPersonEntries(Model model) {
		List<Person> allPersonEntries = new ArrayList<>(personRepository.findAll());

		model.addAttribute("allPeopleEntries", allPersonEntries);

		System.out.println(allPersonEntries);

		return "/person/delete";
	}

	@GetMapping(value = "addressbook/person/search")
	public String showPersonEntrySearch(Person person, String searchParameter, Model model) {

		model.addAttribute("person", person);
		model.addAttribute("searchParameter", searchParameter);

		return "/person/search";
	}

	@GetMapping(value = "addressbook/person/search/firstname")
	public String findByFirstName(Model model) {

		return "person/firstname";
	}

	@GetMapping(value = "addressbook/person/search/lastname")
	public String findByLastName(Model model) {

		return "person/lastname";
	}

	@GetMapping(value = "addressbook/person/search/phonenumber")
	public String findByPhoneNumber(Model model) {

		return "person/phonenumber";
	}

	@GetMapping(value = "addressbook/person/search/email")
	public String findByEmail(Model model) {

		return "person/email";
	}

	@PostMapping(value = "/person")
	public String addNewPerson(Person person, AddressBook addressBook, Model model) {
		personRepository.save(person);
		System.out.println("address book: " + addressBook);
		System.out.println("person: " + person);
		System.out.println("model: " + model);
		System.out.println(model.getAttribute("id"));
		model.addAttribute("person", person);
		model.addAttribute("addressBook", addressBook);

		return "/addressbook";
	}



	@RequestMapping(value = "/person/delete/{id}")
	public String deletePersonEntry(@PathVariable Long id,  Person person, Model model) {

		model.addAttribute("person", person);
		personRepository.deleteById(id);

		return "/addressbook";
	}

	@RequestMapping(value = "/addressbook/person/search/firstname/result")
	public String lookupPersonByFirstName(@RequestParam String firstName, Model model) {
		List<Person> matchingPeopleList = personRepository.findByFirstNameContaining(firstName);

		model.addAttribute("firstName", firstName);
		model.addAttribute("matchingPeopleList", matchingPeopleList);


		System.out.println(matchingPeopleList);
		System.out.println(firstName);

		return "/person/searchresult";
	}

	@RequestMapping(value = "/addressbook/person/search/lastname/result")
	public String lookupPersonByLastName(@RequestParam String lastName, Model model) {
		List<Person> matchingPeopleList = personRepository.findByLastNameContaining(lastName);

		model.addAttribute("lastName", lastName);
		model.addAttribute("matchingPeopleList", matchingPeopleList);


		System.out.println(matchingPeopleList);
		System.out.println(lastName);

		return "/person/searchresult";
	}

	@RequestMapping(value = "/addressbook/person/search/phonenumber/result")
	public String lookupPersonByPhoneNumber(@RequestParam String phoneNumber, Model model) {
		List<Person> matchingPeopleList = personRepository.findByPhoneNumberContaining(phoneNumber);

		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("matchingPeopleList", matchingPeopleList);


		System.out.println(matchingPeopleList);
		System.out.println(phoneNumber);

		return "/person/searchresult";
	}

	@RequestMapping(value = "/addressbook/person/search/email/result")
	public String lookupPersonByEmail(@RequestParam String email, Model model) {
		List<Person> matchingPeopleList = personRepository.findByEmailContaining(email);

		model.addAttribute("email", email);
		model.addAttribute("matchingPeopleList", matchingPeopleList);


		System.out.println(matchingPeopleList);
		System.out.println(email);

		return "/person/searchresult";
	}

}
