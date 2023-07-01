package springbook.learningtest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilepath;

	@Before
	public void setUp() {
		this.calculator = new Calculator();
		this.numFilepath = getClass().getResource("numbers.txt").getPath();
	}

	@Test public void sumOfNumbers() throws IOException {
		int sum = calculator.calcSum(this.numFilepath);

		assertThat(sum, is(10));
	}

	@Test public void multiplyOfNumbers() throws IOException {
		int mul = calculator.calcMultiply(getClass().getResource("numbers.txt").getPath());

		assertThat(mul, is(24));
	}

}

