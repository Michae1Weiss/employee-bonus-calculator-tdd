package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class TeamLeaderBonus implements Bonus{
    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
