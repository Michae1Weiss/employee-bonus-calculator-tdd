package de.szut.bonuscalculator.service;

public class BonusService {
    private static final int maxBonus = 1000;
    private static final int minBonus = 10;

    /**
     * Applies bonus limits. Ensures the bonus is not below the minimum (10)
     * or above the maximum (1000).
     *
     * @param bonus the calculated bonus before limits
     * @return the adjusted bonus within allowed range
     */
    public double applyRestrictions(double bonus) {
        if (bonus < minBonus) {
            return minBonus;
        } else if (bonus > maxBonus) {
            return maxBonus;
        }
        return bonus;
    }
}
