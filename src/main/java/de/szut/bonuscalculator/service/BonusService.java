package de.szut.bonuscalculator.service;

public class BonusService {
    private static final int maxBonus = 1000;
    private static final int minBonus = 10;

    public double applyRestrictions(double bonus) {
        if (bonus < minBonus) {
            return minBonus;
        } else if (bonus > maxBonus) {
            return maxBonus;
        }
        return bonus;
    }
}
