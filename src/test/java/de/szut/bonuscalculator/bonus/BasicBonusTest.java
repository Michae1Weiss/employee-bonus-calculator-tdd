package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicBonusTest {

    @Test
    void shouldReturnBasicBonusAmount() {
        // Given
        Bonus basicBonus = new BasicBonus();
        Employee employee = Employee.builder()
                .name("Max Mustermann")
                .yearsOfService(5)
                .performanceRating(6)
                .completedProjects(3)
                .sickDays(8)
                .isTeamLeader(false)
                .build();

        // WhenEd
        double bonus = basicBonus.calculateBonus(employee);

        // Then
        assertThat(bonus).isEqualTo(500.0);
    }

    @Test
    void shouldThrowExceptionForNullEmployee() {
        // Given
        Bonus basicBonus = new BasicBonus();
        // Then
        assertThrows(IllegalArgumentException.class, () -> basicBonus.calculateBonus(null));

    }
}
