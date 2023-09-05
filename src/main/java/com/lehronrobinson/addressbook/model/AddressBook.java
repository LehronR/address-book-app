package com.lehronrobinson.addressbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


@Entity
public class AddressBook {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "addBk_seq", sequenceName = "address_book_sequence", allocationSize = 1)
	private Long id;
	@NotEmpty(message = "Required: Address book must have a title")
	private String title;

	@OneToMany(
			mappedBy = "addressBook",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Person> allPeopleInAddressBook = new ArrayList<>();

	public AddressBook() {
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Person> getAllPeopleInAddressBook() {
		return allPeopleInAddressBook;
	}

	public void setAllPeopleInAddressBook(List<Person> allPeopleInAddressBook) {
		this.allPeopleInAddressBook = allPeopleInAddressBook;
	}

	@Override
	public String toString() {
		return "AddressBook{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}
}
