package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class TeamLeaderBonus extends BonusDecorator {
    private static final double BONUS_PER_YEAR = 20.0;

    protected TeamLeaderBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        double baseBonus = decoratedBonus.calculateBonus(employee);


        return 0;
    }
}
