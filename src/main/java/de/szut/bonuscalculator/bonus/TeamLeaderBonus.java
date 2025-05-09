package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class TeamLeaderBonus extends BonusDecorator {
    protected TeamLeaderBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
