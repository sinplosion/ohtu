package statistics;

import statistics.matcher.*;

public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    private QueryBuilder(Matcher matcher) {
        this.matcher = matcher;
    }

    public Matcher build() {
        return this.matcher;
    }

    public QueryBuilder playsIn(String team) {
        Matcher m = new And(matcher, new PlaysIn(team));
        return new QueryBuilder(m);
    }

    public QueryBuilder hasFewerThan(int value, String score) {
        Matcher m = new And(matcher, new HasFewerThan(value, score));
        return new QueryBuilder(m);
    }

    public QueryBuilder hasAtLeast(int value, String score) {
        Matcher m = new And(matcher, new HasAtLeast(value, score));
        return new QueryBuilder(m);
    }

    public QueryBuilder or(Matcher m1, Matcher m2) {
        Matcher m = new Or(m1, m2);
        return new QueryBuilder(m);
    }

}