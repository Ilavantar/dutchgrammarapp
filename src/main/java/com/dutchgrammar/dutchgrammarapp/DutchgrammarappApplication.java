package com.dutchgrammar.dutchgrammarapp;

import com.dutchgrammar.dutchgrammarapp.dao.HetDeWordsDAO;
import com.dutchgrammar.dutchgrammarapp.dao.PresentTenseDAO;
import com.dutchgrammar.dutchgrammarapp.entity.HetDeWords;
import com.dutchgrammar.dutchgrammarapp.entity.PresentTense;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DutchgrammarappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DutchgrammarappApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(PresentTenseDAO presentTenseDAO){
//		return runner -> {
//			exNum(presentTenseDAO);
//		};
//	}

	private void exNum(PresentTenseDAO presentTenseDAO){
		long count = presentTenseDAO.exercisesNumber();
		System.out.println("Exercise number " + count);
	}

	private void findAllExercises(PresentTenseDAO presentTenseDAO) {
		List<PresentTense> examples = presentTenseDAO.findAll();
		for (PresentTense temp : examples){
			System.out.println(temp);
		}
	}

	private void findExampleById(HetDeWordsDAO hetDeWordsDAO, int id) {
		HetDeWords hetDeWords = hetDeWordsDAO.findById(id);
		System.out.println(hetDeWords);
	}

}
