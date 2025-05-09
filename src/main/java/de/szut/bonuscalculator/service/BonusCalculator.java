package de.szut.bonuscalculator.service;

import de.szut.bonuscalculator.bonus.*;
import de.szut.bonuscalculator.model.Employee;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class BonusCalculator {
    private final BonusService bonusService;

    public BonusCalculator(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    public double calculateTotalBonus(Employee employee) {
        Bonus bonus = createBonusChain();
        double calculatedBonus = bonus.calculateBonus(employee);

        return bonusService.applyRestrictions(calculatedBonus);
    }

    private Bonus createBonusChain() {
        // Grundbonus als Basis
        Bonus bonus = new BasicBonus();

        // Dekoratoren für zusätzliche Boni hinzufügen
        bonus = new SeniorityBonus(bonus);
        bonus = new ProjectCompletionBonus(bonus);
        bonus = new TeamLeaderBonus(bonus);
        bonus = new SickDayBonus(bonus);
        bonus = new SickDayDeduction(bonus);

        // Performance-Multiplikator als letztes anwenden
        bonus = new PerformanceBonus(bonus);

        return bonus;
    }
}
