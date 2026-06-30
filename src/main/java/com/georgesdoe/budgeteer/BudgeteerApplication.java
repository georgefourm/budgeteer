package com.georgesdoe.budgeteer;

import com.georgesdoe.budgeteer.common.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BudgeteerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgeteerApplication.class, args);
	}

}
