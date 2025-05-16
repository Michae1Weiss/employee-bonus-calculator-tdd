package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class SickDayDeductionTest {
    @Test
    void shouldDeductBonusForHighSickDays() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new SickDayDeduction(wrappedComponent);

        // Fall 1: 9 Fehltage (keine Abzüge)
        Employee employee1 = Employee.builder().sickDays(9).build();

        // Fall 2: 15 Fehltage (10-20 Bereich)
        Employee employee2 = Employee.builder().sickDays(15).build();

        // Fall 3: 25 Fehltage (über 20)
        Employee employee3 = Employee.builder().sickDays(25).build();

        // When
        double bonus1 = decorator.calculateBonus(employee1);
        double bonus2 = decorator.calculateBonus(employee2);
        double bonus3 = decorator.calculateBonus(employee3);

        // Then
        assertThat(bonus1).isEqualTo(500.0); // kein Abzug
        assertThat(bonus2).isEqualTo(300.0); // 500 - 200
        assertThat(bonus3).isEqualTo(100.0); // 500 - 400

        verify(wrappedComponent, times(3)).calculateBonus(any(Employee.class));
    }

    @Test
    void givenNegativeSickDays_whenCalculateSichDayDeduction_thenIllegalArgumentException() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new SickDayDeduction(wrappedComponent);

        Employee employee1 = Employee.builder().sickDays(-1).build();

        // When
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    decorator.calculateBonus(employee1);
                }).withMessageContaining("sickDays cannot be a negative number");
    }

    @Test
    void given265SickDays_whenCalculateSichDayDeduction_thenIllegalArgumentException() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new SickDayDeduction(wrappedComponent);

        Employee employee1 = Employee.builder().sickDays(266).build();

        // When
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    decorator.calculateBonus(employee1);
                }).withMessageContaining("sickDays cannot be > 265");
    }
}
