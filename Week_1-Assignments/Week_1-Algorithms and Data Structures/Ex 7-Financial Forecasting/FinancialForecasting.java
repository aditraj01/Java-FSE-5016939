public class FinancialForecasting {

    // Recursive method to calculate future value
    public double calculateFutureValue(double initialValue, double growthRate, int periods) {
        // Base case: if no more periods, return the initial value
        if (periods == 0) {
            return initialValue;
        }
        // Recursive case: calculate the future value for the next period
        return calculateFutureValue(initialValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        FinancialForecasting ff = new FinancialForecasting();
        
        double initialValue = 1000.0; // initial investment
        double growthRate = 0.05; // 5% growth rate
        int periods = 10; // predicting for 10 periods into the future

        double futureValue = ff.calculateFutureValue(initialValue, growthRate, periods);
        System.out.printf("The future value after %d periods is: %.2f%n", periods, futureValue);
    }
}
