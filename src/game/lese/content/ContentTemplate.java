/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.content;

import game.lese.house.House;
import game.lese.house.JokerHouse;
import game.lese.house.QuestionHouse;
import game.lese.model.Answer;
import game.lese.model.Joker;
import game.lese.model.Question;
import game.lese.outcome.BonusOutcome;
import game.lese.outcome.HouseOutcome;
import game.lese.outcome.PenalityOutcome;
import game.lese.question.JokerBoard;
import game.lese.question.QuestionBoard;

/**
 *
 * @author bruno
 */
public abstract class ContentTemplate {

    public abstract Question draftQuestion();

    public abstract Joker draftJoker();
    
    public House refreshQuestion(House house){
        Question q = draftQuestion();
        HouseOutcome outcome = new BonusOutcome(q.getHouse(), q.getScore(), (float) 0.0);
        QuestionBoard questionBoard = new QuestionBoard(q.getDescription(), q.getExplanation());
        for(Answer answer : q.getAnswer()){
            questionBoard.addChoice(answer.getDescription(), (answer.getStatus().equals("1")));
        }
        QuestionHouse questionUpdated = new QuestionHouse(house.getId(), outcome, house.getDevPhase(), questionBoard, house.getCycle());
        return questionUpdated;
    }
    
    public House refreshJoker(House house){
        Joker j = draftJoker();
        HouseOutcome outcome;
        if(j.getAction().equals("1")){
            outcome = new BonusOutcome(1, j.getScore(), (float) 0.0);
        }else{
            outcome = new PenalityOutcome(1, j.getScore());
        }
        JokerBoard jokerBoard = new JokerBoard(j.getTitle(), j.getDescription());
        JokerHouse jokerUpdated = new JokerHouse(house.getId(), outcome, house.getDevPhase(), jokerBoard, house.getCycle());
        return jokerUpdated;
    }

}