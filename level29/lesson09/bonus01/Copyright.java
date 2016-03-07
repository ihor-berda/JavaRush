package com.javarush.test.level29.lesson09.bonus01;

public class Copyright implements Computable<Copyright.Period, String> {
    @Override
    public String compute(Period period) throws InterruptedException {
        return "All rights reserved (c) " + period.firstYear + "-" + period.secondYear;
    }

    public static class Period {
        int firstYear;
        int secondYear;

        public Period(int firstYear, int secondYear) {
            this.firstYear = firstYear;
            this.secondYear = secondYear;
        }

        @Override
        public String toString() {
            return "Period{" +
                    "firstYear=" + firstYear +
                    ", secondYear=" + secondYear +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Period)) return false;

            Period period = (Period) o;

            if (firstYear != period.firstYear) return false;
            if (secondYear != period.secondYear) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = firstYear;
            result = 31 * result + secondYear;
            return result;
        }
    }
}
