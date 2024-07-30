interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPal {
    public void makePayment(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through PayPal.");
    }
}

class Stripe {
    public void pay(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through Stripe.");
    }
}

class AmazonPay {
    public void processTransaction(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through Amazon Pay.");
    }
}

class PayPalAdapter implements PaymentProcessor {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    public void processPayment(double amount) {
        payPal.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private Stripe stripe;

    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }

    public void processPayment(double amount) {
        stripe.pay(amount);
    }
}

class AmazonPayAdapter implements PaymentProcessor {
    private AmazonPay amazonPay;

    public AmazonPayAdapter(AmazonPay amazonPay) {
        this.amazonPay = amazonPay;
    }

    public void processPayment(double amount) {
        amazonPay.processTransaction(amount);
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        PayPal payPal = new PayPal();
        Stripe stripe = new Stripe();
        AmazonPay amazonPay = new AmazonPay();

        PaymentProcessor payPalAdapter = new PayPalAdapter(payPal);
        PaymentProcessor stripeAdapter = new StripeAdapter(stripe);
        PaymentProcessor amazonPayAdapter = new AmazonPayAdapter(amazonPay);

        payPalAdapter.processPayment(100.00);
        stripeAdapter.processPayment(200.00);
        amazonPayAdapter.processPayment(300.00);
    }
}
