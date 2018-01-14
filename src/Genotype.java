import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genotype implements Comparable<Genotype> {
    int problemSize;
    int weightSum;
    int[] currentRoute;

    public Genotype(int problemSize) {
        this.problemSize = problemSize;
        weightSum = Integer.MAX_VALUE;
        currentRoute = new int[problemSize];
    }

    public Genotype crossover(Genotype parent1, int problemSize){
        Genotype child = new Genotype(problemSize);
        List parent1Gens = new ArrayList();
        List parent2Gens = new ArrayList();
        Random rand = new Random();

        int sectionStartPosition = rand.nextInt(problemSize);
        //System.out.println("start = " + sectionStartPosition);
        int sectionEndPosition = rand.nextInt(problemSize);
        //System.out.println("end = " + sectionEndPosition);

        if (sectionStartPosition > sectionEndPosition){
            int buf = sectionStartPosition;
            sectionStartPosition = sectionEndPosition;
            sectionEndPosition = buf;
        }
        int endIndex = sectionEndPosition;

        for (int i = 0; i < (sectionEndPosition - sectionStartPosition); i++){
            child.currentRoute[sectionStartPosition + i] = parent1.currentRoute[sectionStartPosition + i];
        }

        /*System.out.println("Child:");
        for (int i = 0; i < problemSize; i++){
            System.out.print(" " + child.currentRoute[i]);
        }*/

        for (int i = 0; i < (sectionEndPosition - sectionStartPosition); i++){
            parent1Gens.add(parent1.currentRoute[sectionStartPosition + i]);
        }

        //System.out.print("\nParent1Gens: " + parent1Gens.toString());

        //OX crossover
        for (int i = sectionEndPosition; i < problemSize; i++){
            if (!parent1Gens.contains(this.currentRoute[i])){
                parent2Gens.add(this.currentRoute[i]);
            }
        }

        for (int i = 0; i < sectionEndPosition; i++){
            if (!parent1Gens.contains(this.currentRoute[i])){
                parent2Gens.add(this.currentRoute[i]);
            }
        }

        //System.out.print("\nParent2Gens: " + parent2Gens.toString());

        int j = 0;
        for (int i = sectionEndPosition; i < problemSize; i++, j++){
            child.currentRoute[i] = (int)parent2Gens.get(j);
        }

        for (int i = 0; i < sectionStartPosition; i++, j++) {
            child.currentRoute[i] = (int)parent2Gens.get(j);
        }

        /*System.out.println("\nChild:");
        for (int i = 0; i < problemSize; i++){
            System.out.print(" " + child.currentRoute[i]);
        }*/

        return child;
    }

    public void mutation(){
        Random rand = new Random();
        int mutIndex1 = rand.nextInt(this.currentRoute.length);
        int mutIndex2 = rand.nextInt(this.currentRoute.length);
        if (mutIndex1 == mutIndex2){
            mutIndex2 = rand.nextInt(this.currentRoute.length);
        }
        int buf = this.currentRoute[mutIndex1];
        this.currentRoute[mutIndex1] = this.currentRoute[mutIndex2];
        this.currentRoute[mutIndex2] = buf;

        /*System.out.println("\nAfter mutation Child: ");
        for (int i = 0; i < problemSize; i++){
            System.out.print(this.currentRoute[i]);
        }*/
    }

    @Override
    public int compareTo(Genotype o) {
        if (weightSum < o.weightSum){
            return -1;
        }
        else if (weightSum == o.weightSum){
            return 0;
        }
        else {
            return 1;
        }
    }
}