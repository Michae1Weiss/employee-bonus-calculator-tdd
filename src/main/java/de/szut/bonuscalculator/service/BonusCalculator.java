package de.szut.bonuscalculator.service;

import de.szut.bonuscalculator.bonus.*;
import de.szut.bonuscalculator.model.Employee;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class BonusCalculator {
    private final BonusService bonusService;

    public BonusCalculator(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    /**
     * Calculates the total bonus for the given employee.
     * Applies multiple bonus rules and enforces bonus limits.
     *
     * @param employee the employee whose bonus is being calculated
     * @return the final bonus after applying all rules and restrictions
     * @throws IllegalArgumentException if the employee is null
     */
    public double calculateTotalBonus(Employee employee) {
        Bonus bonus = createBonusChain();
        double calculatedBonus = bonus.calculateBonus(employee);

        return bonusService.applyRestrictions(calculatedBonus);
    }

    /**
     * Builds the full bonus calculation chain using the decorator pattern.
     *
     * @return the composed Bonus object representing the full calculation logic
     */
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
