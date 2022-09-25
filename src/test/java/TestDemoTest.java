import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TestDemoTest {
	
	@BeforeEach
	protected void setUp() throws Exception {
		TestDemo testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		// here I instantiated a new object because it wasn't working with the @BeforeEach
		TestDemo testDemo = new TestDemo();

		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		}
		else {
			assertThatThrownBy(() ->
			testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
		
	}
	
	@Test
	private static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(
				arguments(2, 4, 6, false),
				arguments(0, 3, 5, true));
	}
	
	@Test
	protected void assertThatNumberSquaredIsCorrect() {
		TestDemo testDemo = new TestDemo();
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}

}
