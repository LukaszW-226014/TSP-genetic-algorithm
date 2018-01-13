import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genotype {
    int problemSize;
    int weightSum;
    int[] currentRoute;

    public Genotype(int problemSize) {
        this.problemSize = problemSize;
        weightSum = Integer.MAX_VALUE;
        currentRoute = new int[problemSize];
    }

    public Genotype crossover(Genotype parent1, Genotype parent2, int problemSize){
        Genotype child = new Genotype(problemSize);
        List sameGens = new ArrayList();
        Random rand = new Random();

        int sectionStartPosition = rand.nextInt(problemSize);
        int sectionEndPosition = rand.nextInt(problemSize);
        if (sectionStartPosition > sectionEndPosition){
            int buf = sectionStartPosition;
            sectionStartPosition = sectionEndPosition;
            sectionEndPosition = buf;
        }
        int endIndex = sectionEndPosition;

        for (int i = 0; i < (sectionEndPosition - sectionStartPosition); i++){
            child.currentRoute[sectionStartPosition + i] = parent1.currentRoute[sectionStartPosition + i];
        }

        for (int i = 0; i < (sectionEndPosition - sectionStartPosition); i++){
            sameGens.add(parent1.currentRoute[sectionStartPosition + i]);
        }

        //TODO OX crossover
        for (int i = sectionEndPosition; i < problemSize; i++){

        }

        return child;
    }

    public void mutation(int problemSize){
        Random rand = new Random();
        int mutIndex1 = rand.nextInt(problemSize);
        int mutIndex2 = rand.nextInt(problemSize);
        int buf = this.currentRoute[mutIndex1];
        this.currentRoute[mutIndex1] = this.currentRoute[mutIndex2];
        this.currentRoute[mutIndex2] = buf;
    }
}
