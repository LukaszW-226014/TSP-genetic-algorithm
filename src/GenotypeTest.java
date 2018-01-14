import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GenotypeTest {
    private static int problemSize = 5;
    private Genotype gen = new Genotype(problemSize);
    private static Genotype parent1 = new Genotype(problemSize);
    private static Genotype parent2 = new Genotype(problemSize);
    private static GeneticAlgorithm algorithm = new GeneticAlgorithm();


    @BeforeClass
    public static void setUpClass() throws Exception {

        parent1.currentRoute = algorithm.currentRoute;
        algorithm.setupCurrentSolution();
        parent2.currentRoute = algorithm.currentRoute;
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("test start");
    }

    @Test
    public void crossover() throws Exception {
        System.out.println("crossover test");
        parent2.crossover(parent1, problemSize);
    }

    @Test
    public void mutation() throws Exception {
        System.out.println("mutation test");
        gen.crossover(parent1, problemSize).mutation();
    }

}