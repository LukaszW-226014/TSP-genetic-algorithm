import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm
{
    private final Matrix matrix;

    int[] currentRoute;
    int problemSize;
    int weightSum;

    public GeneticAlgorithm(){
        int[][] mt = {{0, 1, 3, 4, 5},
                        {1, 0, 1, 4, 8},
                        {3, 1, 0, 5, 1},
                        {4, 4, 5, 0, 2},
                        {5, 8, 1, 2, 0}};
        matrix = new Matrix(mt);
        problemSize = matrix.getEdgeCount();
        setupCurrentSolution();
    }

    public GeneticAlgorithm(Matrix matrix) {
        this.matrix = matrix;
        problemSize = matrix.getEdgeCount();
        setupCurrentSolution();
    }

    public void setupCurrentSolution() {
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

    public void search(int populationSize, double crossoverProbability, double mutationProbability){
//        setupCurrentSolution();
//        printSolution(currentRoute);
        int bestSolution = Integer.MAX_VALUE;
        int[] bestRoute = new int[problemSize];

        int childsCount = ((populationSize / 4) - 1) * (populationSize / 4);
        int parentsCount = populationSize / 4;
        List <Genotype> population = new ArrayList<>(populationSize);
        List <Genotype> childs = new ArrayList<>();
        List <Genotype> parents = new ArrayList<>();
        List tournamentTable = new ArrayList();
        for (int i = 0; i < populationSize; i++){
            tournamentTable.add(i);
        }

        List generationTable = new ArrayList();
        for (int i = 0; i < problemSize; i++){
            generationTable.add(i);
        }

        for (int i = 0; i < populationSize; i++){
            setupCurrentSolution();
            population.get(i).currentRoute = currentRoute;
            population.get(i).weightSum = matrix.calculateDistance(currentRoute);
        }

        Timer timer = new Timer();
        timer.start();
        long timeEnd = 300000000000L; // 60000000000 = 60 sec
        while (timer.getElapsedTime() < timeEnd){
            Random rand = new Random();
            for (int k = populationSize - 1, b, buf; k > 1; k--){
                b = rand.nextInt(k);
                buf = (int) tournamentTable.get(k);
                tournamentTable.set(k, tournamentTable.get(b));
                tournamentTable.set(b, buf);
            }
            // szukaj najlepszych w podgrupach 4os i dodaj ich do rodzicow
            for (int i = 0, j = 0, k = 0, currentbestSolution = Integer.MAX_VALUE, bestElement = 0; i < populationSize; i++, j++){
                if (population.get((Integer) tournamentTable.get(i)).weightSum < currentbestSolution){
                    currentbestSolution = population.get((Integer) tournamentTable.get(i)).weightSum;
                    bestElement = (int) tournamentTable.get(i);
                }

                if (j == 3){
                    j = -1;
                    parents.set(k, population.get(bestElement));
                    currentbestSolution = Integer.MAX_VALUE;
                    k++;
                }
            }

            //Krzyzowanie
            int childCounter = 0;
            for (int i =0; i < parentsCount; i++){
                for (int j = i + 1; j < parentsCount; j++){
                    if (rand.nextDouble() < crossoverProbability){
                        childs.set(childCounter, parents.get(i).crossover(parents.get(j)));
                        childCounter++;
                        childs.set(childCounter, parents.get(j).crossover(parents.get(i)));
                        childCounter++;
                    }
                }
            }

            //Mutowanie + liczenie wag
            for (int i = 0; i < childCounter; i++){
                if (rand.nextDouble() < mutationProbability){
                    childs.get(i).mutation();
                }
                childs.get(i).weightSum = matrix.calculateDistance(childs.get(i).currentRoute);
            }

            //Dodaj dzieci do populacji i sortuj
            //TODO

            //sprawdz czy najlepszy osobnik w historii - jezeli tak to zapisz go i wyjdz
            if (population.get(0).weightSum < bestSolution){
                bestSolution = population.get(0).weightSum;
                bestRoute = population.get(0).currentRoute;

                System.out.println("Czas: " + (float)timer.getElapsedTime() / 600000000L + "Naj rozw: " + bestSolution);
            }

        }

    }
}
