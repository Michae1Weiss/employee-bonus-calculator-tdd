package de.szut.bonuscalculator.service;

import de.szut.bonuscalculator.model.Employee;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class BonusCalculator {
    private final BonusService bonusService;

    public BonusCalculator(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    public double calculateTotalBonus(Employee employee) {
        return 0;
    }
}
