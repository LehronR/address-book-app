package com.lehronrobinson.addressbook.repository;

import com.lehronrobinson.addressbook.model.AddressBook;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AddressBookRepository extends ListCrudRepository<AddressBook, Long> {
	List<AddressBook> findByTitleContaining(String addressBookSearch);
}
