package com.lehronrobinson.addressbook.controller;

import com.lehronrobinson.addressbook.model.AddressBook;
import com.lehronrobinson.addressbook.model.Person;
import com.lehronrobinson.addressbook.repository.AddressBookRepository;
import com.lehronrobinson.addressbook.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class AddressBookController {

	@Autowired
	private AddressBookRepository addressBookRepository;
	@Autowired
	private PersonRepository personRepository;



	@GetMapping("/")
	public String index() {

		return "/index";
	}

	@GetMapping("/addressbook")
	public String openApp() {

		return "/addressbook";
	}

	@GetMapping("/addressbook/create")
	public String newAddressBook(AddressBook addressBook, Model model) {
		model.addAttribute("addressBook", addressBook);
		return "/addressbook/new";
	}

	@GetMapping("/addressbook/all")
	public String showAllAddressBooks(Model model) {
		List<AddressBook> addressBooks = addressBookRepository.findAll();

		model.addAttribute("allAddressBooks", addressBooks);

		return "/addressbook/all";
	}

	@GetMapping("/addressbook/allpeople")
	public String getAllPeopleInAddressBook(AddressBook addressBook, Person person, Model model) {
		List<Person> peopleList = new ArrayList<>(personRepository.findAll());

		model.addAttribute("people", peopleList);
		System.out.println(addressBook);
		System.out.println(model);
		System.out.println(person);

		return "/addressbook/view";
	}

	@GetMapping(value = "/addressbook/search")
	public String showAddressBookSearch() {
		return "/addressbook/search";
	}

	@GetMapping("/addressbook/{id}")
	public String showAllPersonEntriesInAddressBook(@PathVariable Long id, Model model) {

		List<Person> allPersonEntriesInAddressBook = new ArrayList<>(personRepository.findByAddressBookId(id));

		model.addAttribute("allPeopleInAddressBook", allPersonEntriesInAddressBook);

		System.out.println(allPersonEntriesInAddressBook);

		return "/addressbook/view";
	}


	@PostMapping(value = "/addressbook/new")
	public String addNewAddressBook(@Valid AddressBook addressBook, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			return "addressbook/new";
		}

		addressBookRepository.save(addressBook);
		model.addAttribute("addressBook", addressBook);
		System.out.println("address book: " + addressBook);
		System.out.println("model: " + model);

		return "/addressbook";
	}

	@RequestMapping(value = "addressbook/delete/{id}")
	public String deleteAddressBook(@PathVariable Long id, AddressBook addressBook,Model model) {
		model.addAttribute("deletedAddressBook", addressBook);

		addressBookRepository.deleteById(id);

		return "/addressbook";
	}

	@PostMapping(value = "addressbook/search/result")
	public String lookupAddressBookByTitle(@RequestParam String addressBookSearch, Model model) {
		List<AddressBook> matchingAddressBooks = addressBookRepository.findByTitleContaining(addressBookSearch);

		model.addAttribute("matchingAddressBooks", matchingAddressBooks);
		model.addAttribute("addressBookSearch", addressBookSearch);

		return "/addressbook/addressbooksearchresult";
	}

}
