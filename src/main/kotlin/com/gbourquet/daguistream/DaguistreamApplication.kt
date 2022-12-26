package com.gbourquet.daguistream

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class DaguistreamApplication

fun main(args: Array<String>) {
	runApplication<DaguistreamApplication>(*args)
}

@Component
internal class Initializr(val repository: CustomerRepository) : ApplicationRunner {
	@Throws(Exception::class)
	override fun run(args: ApplicationArguments) {
		listOf("A", "B", "C", "D")
			.map { CustomerRepository.Customer(null, it) }
			.map(repository::save)
			.forEach(System.out::println)
	}
}

@RestController
class CustomerRestController(val repository: CustomerRepository) {
	@GetMapping("/customers")
	fun customers(): Collection<CustomerRepository.Customer> {
		return repository.findAll()
	}
}

@Service
class CustomerRepository {

	data class Customer(val id: Int? = null, val name: String? = null)

	fun save(customer: Customer): Customer = customer
	fun findAll() = listOf(
		Customer(1,"A"),
		Customer(2,"B"),
		Customer(3,"C"),
		Customer(4,"D")
	)
}
