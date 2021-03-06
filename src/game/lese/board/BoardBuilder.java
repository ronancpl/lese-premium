/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.lese.board;

import game.lese.question.JokerInfo;
import game.lese.question.QuestionInfo;
import game.lese.house.FinalHouse;
import game.lese.outcome.BonusOutcome;
import game.lese.outcome.HouseOutcome;
import game.lese.house.House;
import game.lese.house.InitialHouse;
import game.lese.house.IntermediateHouse;
import game.lese.house.QuestionHouse;
import game.lese.house.JokerHouse;
import game.lese.model.Project;

/**
 *
 * @author cass
 */
public class BoardBuilder {

    private Board board;

    public void buildBoard() {
        this.board = Board.getInstance();
    }
    
    public void buildProject(){
        
        //TODO: store project in the database
        String name = "Projeto Merci";
        String description = "Apoio  informatizado  ao  controle  de  vendas  e  de  compras  da  mercearia  Pereira  &  Pereira Comercial Ltda";
        int cycle = 2;
        Project p = new Project(name, description, cycle);
        this.board.setProject(p);
    }

    public void buildHouses() {

        int nHousesPerPhase = 6;
        int idHouse = 0;
        int developmentCycles = this.board.getProject().getNumCycles();
        for (int cycle = 1; cycle <= developmentCycles; cycle++) {
            for (DevelopmentPhase phase : DevelopmentPhase.values()) {
                for (int i = 1; i <= nHousesPerPhase; i++) {
                    if (idHouse == 0 || idHouse == ((cycle -1) * nHousesPerPhase * DevelopmentPhase.values().length)) {
                        buildInitialHouse(idHouse, cycle);
                    } else {
                        if (cycle == developmentCycles && idHouse == (cycle * nHousesPerPhase * DevelopmentPhase.values().length) - 1) {
                            buildFinalHouse(idHouse, cycle);
                        } else {
                            if (cycle < developmentCycles && (idHouse == (cycle * nHousesPerPhase * DevelopmentPhase.values().length) - 1)) {
                                buildIntermediateHouse(idHouse, cycle);
                            } else {
                                if (i % 3 != 0) {
                                    buildQuestionHouse(phase, idHouse, cycle);
                                } else {
                                    buildJokerHouse(phase, idHouse, cycle);
                                }
                            }
                        }
                    }
                    idHouse++;
                }
            }
        }
    }

    private void buildInitialHouse(int idHouse, int cycle) {
        String message = "Parabéns! \nVocê acaba de ser contratado pela empresa XYZ para trabalhar como engenheiro de software. "
                + "Prepare-se, muitos desafios estão por vir. Novos conhecimentos serão obtidos e muitos obstáculos serão encontrados. "
                + "Mas fique tranquilo. No final tudo isso valerá apenas.\n\n"
                + "Você acaba de ganhar 10 pontos pela sua contratação.";

        HouseOutcome outcome = new BonusOutcome(1, 10);
        House initHouse = new InitialHouse(idHouse, outcome, null, message, cycle);
        this.board.addHouse(initHouse);
    }

    private void buildIntermediateHouse(int idHouse, int cycle) {
        String message = "Parabéns! \nVocê acaba de finalizar o ciclo "+cycle+" de desenvolvimento do software.\n\n"
                + "Prepare-se, agora para o inicio do ciclo "+ (cycle+1) + ".";

        HouseOutcome outcome = new BonusOutcome(2, 0);
        House intermediateHouse = new IntermediateHouse(idHouse, outcome, null, message, cycle);
        this.board.addHouse(intermediateHouse);
    }

    private void buildFinalHouse(int idHouse, int cycle) {
        String message = "Parabéns! \nVocê completou todos os ciclos de desenvolvimento do software.\n\n"
                + "Fim do jogo.";

        HouseOutcome outcome = new BonusOutcome(0, 0);
        House finalHouse = new FinalHouse(idHouse, outcome, null, message, cycle);
        this.board.addHouse(finalHouse);
    }

    private void buildQuestionHouse(DevelopmentPhase phase, int idHouse, int cycle) {
        House questionHouse = new QuestionHouse(idHouse, phase, cycle);
        this.board.addHouse(questionHouse);
    }

    private void buildJokerHouse(DevelopmentPhase phase, int idHouse, int cycle) {
        House jokerHouse = new JokerHouse(idHouse, phase, cycle);
        this.board.addHouse(jokerHouse);
    }

}
