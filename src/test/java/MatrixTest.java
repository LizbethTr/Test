import example.org.Matrix;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.Assert;

public class MatrixTest {

    @Test
    public void addMatrix() {
        double[][] A = {{1, 2, 3}, {10, 12, 15}};
        double[][] B = {{72, 83, 94}, {57, 46, 4}};
        double[][] C = {{73, 85, 97}, {67, 58, 19}};
        Assert.assertArrayEquals(C, Matrix.add(A, B));
    }

    @Test
    public void subMatrix() {
        double[][] A = {{1, 2, 3}, {10, 12, 15}};
        double[][] B = {{72, 83, 94}, {57, 46, 4}};
        double[][] C = {{-71, -81, -91}, {-47, -34, 11}};
        Assert.assertArrayEquals(C, Matrix.subtract(A, B));
    }

    @Test
    public void multiplyByNumberMatrix() {
        double[][] A = {{1, 2, 3}, {10, 12, 15}};
        double k = 1.2;
        double[][] B = {{1.2, 2.4, 3.6}, {12, 14.4, 18}};

        A = Matrix.multiplyByNumber(A, k);
        for (int i = 0; i < B.length; i++) {
            Assert.assertArrayEquals(B[i], A[i], 1E-14);
        }
    }

    @Test
    public void multiply() {
        double[][] A = {{1, 2, 3}, {10, 12, 15}};
        double[][] B = {{72, 57}, {83, 46}, {94, 4}};
        double[][] C = {{520, 161}, {3126, 1182}};
        Assert.assertArrayEquals(C, Matrix.multiply(A, B));
    }

    @Test
    public void multiplyIncorrectSize() throws Exception {
        double[][] A = {{1, 2, 3}, {10, 12, 15}};
        double[][] B = {{72, 83, 94}, {57, 46, 4}};

        assertThrows(Exception.class, () -> Matrix.multiply(A, B));
    }


    double[][]D={{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C"})
    public void multiplyIncorrectSizeFromFiles(String candidate) throws Exception {
        assertThrows(Exception.class, () -> Matrix.multiply(D, Matrix.ReadFromFile(candidate)));
    }

    @Test
    public void readFromFile() {
        double[][] A = Matrix.ReadFromFile(getClass().getResource("/A.txt").getFile());
        Matrix.print(A);
        assertTrue(A != null);
    }

    @Test
    public void subFromFiles() {
        double[][] A = Matrix.ReadFromFile(getClass().getResource("/A.txt").getFile());
        double[][] B = Matrix.ReadFromFile(getClass().getResource("/B.txt").getFile());
        double[][] C = Matrix.ReadFromFile(getClass().getResource("/C.txt").getFile());
        assertArrayEquals(Matrix.subtract(A, B), C);
    }

    @Test
    public void multiplyFromFiles() {
        double[][] A = Matrix.ReadFromFile(getClass().getResource("/A.txt").getFile());
        double[][] B = Matrix.ReadFromFile(getClass().getResource("/B-1.txt").getFile());
        double[][] C = Matrix.ReadFromFile(getClass().getResource("/D.txt").getFile());
        assertArrayEquals(Matrix.multiply(A, B), C);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C"})
    public void readWithoutThrow(String candidate) {
        assertDoesNotThrow(() -> Matrix.ReadFromFile(candidate));
    }

    double[][] A = {{2,4},{6,8}};
    double[][] B = {{1,3},{5,7}};
    double[][][] C ={{{3,7},{11,15}}, {{1,1},{1,1}}, {{22,34},{46,74}}};

    @ParameterizedTest
    @CsvSource({
            "add",
            "sub",
            "mul"
    })
    public void operations(String op) {
        switch (op) {
            case "add":
                assertArrayEquals(Matrix.add(A, B), C[0]);
                break;
            case "sub":
                assertArrayEquals(Matrix.subtract(A, B), C[1]);
                break;
            case "mul":
                assertArrayEquals(Matrix.multiply(A, B), C[2]);
                break;
        }
    }

}