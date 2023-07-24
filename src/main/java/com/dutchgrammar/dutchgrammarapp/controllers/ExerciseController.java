package com.dutchgrammar.dutchgrammarapp.controllers;

import com.dutchgrammar.dutchgrammarapp.dao.HetDeWordsDAO;
import com.dutchgrammar.dutchgrammarapp.dao.PresentTenseDAO;
import com.dutchgrammar.dutchgrammarapp.entity.HetDeWords;
import com.dutchgrammar.dutchgrammarapp.entity.PresentTense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class ExerciseController {


    private int pagePresentTense = 0;
    private int pageHetDe = 0;

    private String exercise = "None";

    private PresentTenseDAO presentTenseDAO;
    private HetDeWordsDAO hetDeWordsDAO;

    @Autowired
    public void setPresentTenseDAO(PresentTenseDAO presentTenseDAO){
        this.presentTenseDAO = presentTenseDAO;
    }

    @Autowired
    public void setHetDeWordsDAO(HetDeWordsDAO hetDeWordsDAO) {
        this.hetDeWordsDAO = hetDeWordsDAO;
    }

    public void setParamsPresentTense(Model model, int page){
        int start = page * 10;

        model.addAttribute("exerciseTitle", "Tegenwoordige tijd/simple present");

        for(int i = start; i < start + 10; i++){
            int id = i + 1 - start;
            int databaseId = i + 1;
            PresentTense example = presentTenseDAO.findById(databaseId);
            String question = example.getQuestion();

            String[] parts = question.split("___");
            model.addAttribute("ex" + id + "part1", parts[0]);
            model.addAttribute("ex" + id + "part2", parts[1]);
        }
    }

    public void setParamsHetDeWords(Model model, int page){
        int start = page * 10;

        model.addAttribute("exerciseTitle", "Het en de woorden");

        for(int i = start; i < start + 10; i++){
            int id = i + 1 - start;
            int databaseId = i + 1;
            HetDeWords example = hetDeWordsDAO.findById(databaseId);
            String question = example.getQuestion();

            String[] parts = question.split("___");
            model.addAttribute("ex" + id + "part1", parts[0]);
            model.addAttribute("ex" + id + "part2", parts[1]);
        }
    }

    public void nextPage(int[] array, int page){
        int[] baseArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int i = 0; i < 10; i++){
            array[i] = baseArray[i];
        }

        if(array[0] < 50){
            for(int i = 0; i < 10; i++){
                array[i] += page * 10;
            }
        }
    }

    @PostMapping("/next")
    public String nextPage(Model model){

        if(exercise.equals("present tense")){
            if(this.pagePresentTense < presentTenseDAO.exercisesNumber()/10 - 1){
                this.pagePresentTense++;
            }

            setParamsPresentTense(model, pagePresentTense);
            return "redirect:/present_tense";

        } else if(exercise.equals("het de words")){

            if(this.pageHetDe < hetDeWordsDAO.exercisesNumber()/10 - 1){
                this.pageHetDe++;
            }
            setParamsHetDeWords(model, pageHetDe);
            return "redirect:/het_de";
        }

        return "redirect:/";
    }

    @PostMapping("/previous")
    public String previousPage(Model model){

        if(exercise.equals("present tense")){
            if(this.pagePresentTense > 0){
                this.pagePresentTense--;
            }
            return "redirect:/present_tense";
        } else if(exercise.equals("het de words")){
            if(this.pageHetDe > 0){
                this.pageHetDe--;
            }
            return "redirect:/het_de";
        }

        return "redirect:/";
    }

    @GetMapping("/")
    public String start(){
        return "start";
    }

    @GetMapping("/present_tense")
    public String presentTenseExercise(Model model){
        pageHetDe = 0;
        exercise = "present tense";

        for(int i = 1; i < 11; i++){
            model.addAttribute("check" + i, " ");
        }
        setParamsPresentTense(model, pagePresentTense);

        return "index";
    }

    @GetMapping("/het_de")
    public String hetDeExercise(Model model){
        pagePresentTense = 0;
        exercise = "het de words";

        for(int i = 1; i < 11; i++){
            model.addAttribute("check" + i, " ");
        }
        setParamsHetDeWords(model, pageHetDe);

        return "index";

    }

    @PostMapping("/check")
    public String checkAnswer(@RequestParam("ans1") String ans1,
                              @RequestParam("ans2") String ans2,
                              @RequestParam("ans3") String ans3,
                              @RequestParam("ans4") String ans4,
                              @RequestParam("ans5") String ans5,
                              @RequestParam("ans6") String ans6,
                              @RequestParam("ans7") String ans7,
                              @RequestParam("ans8") String ans8,
                              @RequestParam("ans9") String ans9,
                              @RequestParam("ans10") String ans10,
                              Model model){

        String[] answers = {ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9, ans10};

        if(exercise.equals("present tense")){
            int start = 10 * pagePresentTense;
            for(int i = start; i < start + 10; i++){
                int id = i + 1 - start;
                int databaseId = i + 1;
                PresentTense example = presentTenseDAO.findById(databaseId);
                String correctAnswer = example.getAnswer().toLowerCase();
                String userAnswer = answers[i - start].toLowerCase();

                if(userAnswer.equals(correctAnswer)){
                    model.addAttribute("check" + id, "correct");
                } else {
                    model.addAttribute("check" + id, "wrong, correct answer: " + correctAnswer);
                }
            }

            setParamsPresentTense(model, pagePresentTense);


        } else if(exercise.equals("het de words")){
            int start = 10 * pageHetDe;
            for(int i = start; i < start + 10; i++){
                int id = i + 1 - start;
                int databaseId = i + 1;
                HetDeWords example = hetDeWordsDAO.findById(databaseId);
                String correctAnswer = example.getAnswer().toLowerCase();
                String userAnswer = answers[i - start].toLowerCase();
                if(userAnswer.equals(correctAnswer)){
                    model.addAttribute("check" + id, "correct");
                } else {
                    model.addAttribute("check" + id, "wrong, correct answer: " + correctAnswer);
                }
            }
            setParamsHetDeWords(model, pageHetDe);

        }

        for(int i = 0; i < 10; i++){
            int id = i + 1;
            model.addAttribute("ans" + id, answers[i]);
        }

        return "index";
    }

}
