package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SickDayBonusTest {
    @Test
    void shouldAddBonusForLowSickDays() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new SickDayBonus(wrappedComponent);

        // Fall 1: 3 Fehltage (0-5 Bereich)
        Employee employee1 = Employee.builder().sickDays(3).build();

        // Fall 2: 7 Fehltage (5-10 Bereich)
        Employee employee2 = Employee.builder().sickDays(7).build();

        // Fall 3: 12 Fehltage (außerhalb des Bonus-Bereichs)
        Employee employee3 = Employee.builder().sickDays(12).build();

        // When
        double bonus1 = decorator.calculateBonus(employee1);
        double bonus2 = decorator.calculateBonus(employee2);
        double bonus3 = decorator.calculateBonus(employee3);

        // Then
        assertThat(bonus1).isEqualTo(510.0); // 500 + 10
        assertThat(bonus2).isEqualTo(505.0); // 500 + 5
        assertThat(bonus3).isEqualTo(500.0); // kein Bonus

        verify(wrappedComponent, times(3)).calculateBonus(any(Employee.class));
    }
}
