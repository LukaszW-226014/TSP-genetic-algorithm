import java.util.Random;

public class GeneticAlgorithm
{
    private final Matrix matrix;

    int[] currentRoute;
    int problemSize;
    int weightSum;



    public GeneticAlgorithm(Matrix matrix) {
        this.matrix = matrix;
        problemSize = matrix.getEdgeCount();
        setupCurrentSolution();
    }

    private void setupCurrentSolution() {
        // wypelnienie obecnego rozwiazania 0,1,2,...
//        currentRoute = new int[problemSize + 1];
        currentRoute = new int[problemSize];
        for (int i = 0; i < problemSize; i++){
            currentRoute[i] = i;
        }

//        currentRoute[problemSize] = 0;
        // ustalenie nowego losowego rozwiazania
        Random r = new Random();
        for(int k = problemSize-1, j, buf; k > 0; k--) //k > 1
        {
            j = r.nextInt(k);
            buf = currentRoute[k];
            currentRoute[k] = currentRoute[j];
            currentRoute[j] = buf;
        }
        /*
        for (int m = 0; m < problemSize; m++){
            if (currentRoute[m] == 0){
                currentRoute[m] = currentRoute[0];
            }
        }
        currentRoute[0] = 0;
        currentRoute[problemSize] = 0;*/
    }

    private void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
        System.out.println();
    }

    public void search(){
        setupCurrentSolution();
        printSolution(currentRoute);
    }
}
