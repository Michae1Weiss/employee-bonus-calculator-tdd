package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class TeamLeaderBonus extends BonusDecorator {
    private static final double BONUS = 20.0;

    public TeamLeaderBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        double baseBonus = decoratedBonus.calculateBonus(employee);

        if (employee.isTeamLeader()) {
            return baseBonus + BONUS;
        } else {
            return baseBonus;
        }
    }
}
