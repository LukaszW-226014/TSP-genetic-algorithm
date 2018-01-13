import java.math.BigDecimal;

public class Timer {
    private Long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    //public BigDecimal getElapsedTime() {
    public Long getElapsedTime() {
        Long endTime = System.nanoTime();
        Long result = endTime - startTime;
        //return BigDecimal.valueOf(endTime - startTime);
        return  result;
    }
}
