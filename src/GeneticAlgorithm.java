import java.util.Random;

public class GeneticAlgorithm
{
    private final Matrix matrix;

    int[] currSolution;
    int problemSize;


    public GeneticAlgorithm(Matrix matrix) {
        this.matrix = matrix;
        problemSize = matrix.getEdgeCount();
        setupCurrentSolution();
    }

    private void setupCurrentSolution() {
        // wypelnienie obecnego rozwiazania 0,1,2,...
        currSolution = new int[problemSize + 1];
        for (int i = 0; i < problemSize; i++)
            currSolution[i] = i;
        currSolution[problemSize] = 0;
        // ustalenie nowego losowego rozwiazania
        Random r = new Random();
        for(int k = problemSize-1, j, buf; k > 1; k--)
        {
            j = r.nextInt(k);
            buf = currSolution[k];
            currSolution[k] = currSolution[j];
            currSolution[j] = buf;
        }
        for (int m = 0; m < problemSize; m++){
            if (currSolution[m] == 0){
                currSolution[m] = currSolution[0];
            }
        }
        currSolution[0] = 0;
        currSolution[problemSize] = 0;
    }

    private void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
        System.out.println();
    }

}
