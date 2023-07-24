package com.dutchgrammar.dutchgrammarapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="het_de_woorden")
public class HetDeWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="question")
    private String question;

    @Column(name="answer")
    private String answer;

    @Column(name="pl_translation")
    private String plTranslation;

    @Column(name="en_translation")
    private String enTranslation;

    public HetDeWords() {

    }

    public HetDeWords(String question, String answer, String plTranslation, String enTranslation) {
        this.question = question;
        this.answer = answer;
        this.plTranslation = plTranslation;
        this.enTranslation = enTranslation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPlTranslation() {
        return plTranslation;
    }

    public void setPlTranslation(String plTranslation) {
        this.plTranslation = plTranslation;
    }

    public String getEnTranslation() {
        return enTranslation;
    }

    public void setEnTranslation(String enTranslation) {
        this.enTranslation = enTranslation;
    }

    @Override
    public String toString() {
        return "HetDeWords{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", plTranslation='" + plTranslation + '\'' +
                ", enTranslation='" + enTranslation + '\'' +
                '}';
    }
}
