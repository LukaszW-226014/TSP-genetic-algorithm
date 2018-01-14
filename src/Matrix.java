import java.util.Random;

public class Matrix {

    private int[][] matrix;

    private int edgeCount;

    public Matrix(int[][] matrix) {
        edgeCount = matrix.length;
       this.matrix = matrix;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public int getWeight(int from, int to) {
        return matrix[from][to];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return edgeCount;
    }

    public void printMatrix() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println("\n ");
        }
    }

    //wyliczanie kosztu
    public int calculateDistance(int solution[]) {
        int cost = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            cost += matrix[solution[i]][solution[i + 1]];
            //System.out.println("Koszt z mista: " + solution[i] + " do " + solution[i+1] + " to: " + matrix[solution[i]][solution[i + 1]]);
        }

        cost += matrix[solution[solution.length - 1]][solution[0]];
        //System.out.println("Koszt z mista: " + solution[solution.length - 1] + " do " + solution[0] + " to: " + matrix[solution.length - 1][0]);
        return cost;
    }
}