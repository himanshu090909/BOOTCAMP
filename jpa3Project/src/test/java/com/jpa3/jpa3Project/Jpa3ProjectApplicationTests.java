package com.jpa3.jpa3Project;

import com.jpa3.jpa3Project.entities.Address;
import com.jpa3.jpa3Project.entities.Author;
import com.jpa3.jpa3Project.repositories.AuthorRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Jpa3ProjectApplicationTests {

	@Autowired
	AuthorRepository authorRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void t()
	{
		Address address =  new Address();
		address.setLocation("vivek vihar");
		address.setState("delhi");
		address.setStreetNumber("jhilmil");
		Author author = new Author();
		author.setName("himanshu");
		author.setAddress(address);
		authorRepository.save(author);

	}

}
