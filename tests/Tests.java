import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.Optional;
import static org.junit.Assert.*;

public class Tests {

    @Test //Проверка 0 нулевой длины
    public void TestZeroLength(){
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(""), "Zero length string");
    }

    @Test //Проверка положительного/отрицательного числа при символах +-
    public void TestSigns(){
        Assertions.assertEquals(-1, Integer.decode("-1"));
        Assertions.assertEquals(1, Integer.decode("+1"));
        Assertions.assertEquals(1, Integer.decode("1"));
    }

    @Test //Проверка равенства +0 и -0
    public void TestDifferenceZeroes(){
        Assertions.assertEquals(Integer.decode("+0"), Integer.decode("-0"));
    }

    @Test //Проверка Integer.MIN_VALUE
    public void TestMinValue(){
        Assertions.assertEquals(Integer.MIN_VALUE, Integer.decode("-2147483648"));
    }

    @Test //Проверка Integer.MAX_VALUE
    public void TestMaxValue(){
        Assertions.assertEquals(Integer.MAX_VALUE, Integer.decode("2147483647"));
    }

    @Test //Проверка неверной позиции знака
    public void TestSignPosition() {
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0x-16"), "Sign character in wrong position");
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("#+32"), "Sign character in wrong position");
    }

    @Test //Проверка системы счисления
    public void TestNumberSystems() {
        Assertions.assertEquals(10, Integer.decode("0xA"));
        Assertions.assertEquals(-10, Integer.decode("-0xA"));

        Assertions.assertEquals(10, Integer.decode("0XA"));
        Assertions.assertEquals(-10, Integer.decode("-0XA"));

        Assertions.assertEquals(10, Integer.decode("#A"));
        Assertions.assertEquals(-10, Integer.decode("-#A"));

        Assertions.assertEquals(10, Integer.decode("0x00A"));
        Assertions.assertEquals(-10, Integer.decode("-0x00A"));

        Assertions.assertEquals(10, Integer.decode("0X00A"));
        Assertions.assertEquals(-10, Integer.decode("-0X00A"));

        Assertions.assertEquals(10, Integer.decode("012"));
        Assertions.assertEquals(-10, Integer.decode("-012"));
    }

    @Test
    public void TestStartsWithZero(){
        Assertions.assertNotEquals(12, Integer.decode("012"));
    }
}