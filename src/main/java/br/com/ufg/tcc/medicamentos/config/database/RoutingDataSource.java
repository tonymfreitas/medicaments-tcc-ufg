package br.com.ufg.tcc.medicamentos.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<Route> context = new ThreadLocal<>();


    public enum Route {
        WRITE("write"), READ("read");

        private final String prefix;

        Route(final String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public static void clearRoute() {
        context.remove();
    }

    public static void setReadRoute() {
        context.set(Route.READ);
    }

    public static void setWriteRoute() {
        context.set(Route.WRITE);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return context.get();
    }

    static void setReadonlyDataSource(boolean isReadonly) {
        context.set(isReadonly ? Route.READ : Route.WRITE);
    }
}
