package de.szut.bonuscalculator.service;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BonusCalculatorTest {

    @Mock
    private BonusService bonusService;

    @InjectMocks
    private BonusCalculator bonusCalculator;

    @Test
    void givenEmployeeWithLowBonus_whenCalculateTotalBonus_thenRespectMinimumLimit() {
        // given
        when(bonusService.applyRestrictions(anyDouble())).thenAnswer(invocation -> {
            double bonus = invocation.getArgument(0);
            return Math.max(10.0, Math.min(1000.0, bonus));
        });

        Employee employee = Employee.builder()
                .name("Max Mustermann")
                .yearsOfService(2)
                .performanceRating(2)
                .completedProjects(1)
                .sickDays(5)
                .isTeamLeader(false)
                .build();

        // when
        double bonus = bonusCalculator.calculateTotalBonus(employee);

        // then
        assertThat(bonus).isEqualTo(270.0);
    }

    @Test
    void givenEmployeeWithHighBonus_whenCalculateTotalBonus_thenRespectMaximumLimit() {
        // given
        when(bonusService.applyRestrictions(anyDouble())).thenAnswer(invocation -> {
            double bonus = invocation.getArgument(0);
            return Math.max(10.0, Math.min(1000.0, bonus));
        });

        Employee employee = Employee.builder()
                .name("Anna Schmidt")
                .yearsOfService(15)
                .performanceRating(9)
                .completedProjects(20)
                .sickDays(3)
                .isTeamLeader(true)
                .build();

        // when
        double bonus = bonusCalculator.calculateTotalBonus(employee);

        // then
        assertThat(bonus).isEqualTo(1000.0);
    }

    @Test
    void givenNullEmployee_whenCalculateTotalBonus_thenThrowIllegalArgumentException() {
        // given - kein Stub nötig, da Methode vorher abbricht

        // when + then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> bonusCalculator.calculateTotalBonus(null))
                .withMessageContaining("Employee cannot be null");

        verifyNoInteractions(bonusService);
    }
}
