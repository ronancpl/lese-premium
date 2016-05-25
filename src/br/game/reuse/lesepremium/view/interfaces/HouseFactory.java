/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.game.reuse.lesepremium.view.interfaces;

import br.game.reuse.lesepremium.model.Joker;
import br.game.reuse.lesepremium.model.Question;
import java.util.List;

/**
 *
 * @author bruno
 */
public interface HouseFactory {
    
    public House createHouse(int codeHouse, List<Question> questions, List<Joker> jokers);
    
    public void createRequirementsLifting();
    
    public void createRequirementsAnalysis();
    
    public void createProject();
    
    public void createImplementation();
    
    public void createTest();
    
    public void createImplantation();
    
}