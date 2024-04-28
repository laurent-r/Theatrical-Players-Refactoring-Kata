package theatricalplays;

import java.util.Map;

public record Play (String name, Type type) {

    enum Type { TRAGEDY, COMEDY, WESTERN;

        private static final Map<Type, PricingStrategy> PRICING_STRATEGIES = Map.of(
                COMEDY, new ComedyPricingStrategy(),
                TRAGEDY, new TragegyPricingStrategy()
        );

        interface PricingStrategy {
            int calculateAmount(int audience);
        }

        static class ComedyPricingStrategy implements PricingStrategy {
            @Override
            public int calculateAmount(int audience) {
                int amount = 30000 + 300 * audience;
                if (audience > 20) {
                    amount += 10000 + 500 * (audience - 20);
                }
                return amount;
            }
        }

        static class TragegyPricingStrategy implements PricingStrategy {
            @Override
            public int calculateAmount(int audience) {
                int amount = 40000;
                if (audience > 30) {
                    amount += 1000 * (audience - 30);
                }
                return amount;
            }
        }
        int calculateAmount(int audience) {
            PricingStrategy strategy = PRICING_STRATEGIES.get(this);
            if (strategy == null) {
                throw new IllegalArgumentException("unknown type: " + this);
            }
            return strategy.calculateAmount(audience);
        }

        int calculateVolumeCredits(int audience) {
            int volumeCredits = Math.max(audience - 30, 0);
            // add extra credit for every five comedy attendees
            if (this == COMEDY) volumeCredits += audience / 5;
            return volumeCredits;
        }
    }

}
