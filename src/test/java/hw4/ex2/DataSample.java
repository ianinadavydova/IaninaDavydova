package hw4.ex2;

import hw3.enums.Element;
import hw3.enums.Color;
import hw3.enums.Metal;
import hw4.enums.SummaryEven;
import hw4.enums.SummaryOdd;
import hw4.enums.Vegetable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataSample {
    @Getter
    public static class Summary {
        private final SummaryOdd odd;
        private final SummaryEven even;

        public Summary(SummaryOdd odd, SummaryEven even) {
            this.odd = odd;
            this.even = even;
        }
    }
    Summary summary;
    final Element[] elements;
    final Color color;
    final Metal metal;
    final Vegetable[] vegetables;
}
