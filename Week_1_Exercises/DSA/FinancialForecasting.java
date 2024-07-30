public class FinancialForecasting {
    public static double forecastValue(double initialAmount, double rate, int duration) {
        if (duration == 0) {
            return initialAmount;
        }
        return forecastValue(initialAmount * (1 + rate), rate, duration - 1);
    }

    public static void main(String[] args) {
        double initialAmount = 1000.0;
        double rate = 0.05; // 5% growth rate
        int duration = 10;
        double projectedValue = forecastValue(initialAmount, rate, duration);
        System.out.println("Projected Future Value: $" + projectedValue);
    }
}

