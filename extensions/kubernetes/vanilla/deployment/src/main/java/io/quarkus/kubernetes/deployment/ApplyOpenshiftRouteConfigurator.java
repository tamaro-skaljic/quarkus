package io.quarkus.kubernetes.deployment;

import io.dekorate.kubernetes.config.Configurator;
import io.dekorate.openshift.config.OpenshiftConfigFluent;

public class ApplyOpenshiftRouteConfigurator extends Configurator<OpenshiftConfigFluent> {

    private final RouteConfig routeConfig;

    public ApplyOpenshiftRouteConfigurator(RouteConfig routeConfig) {
        this.routeConfig = routeConfig;
    }

    @Override
    public void visit(OpenshiftConfigFluent config) {
        if (routeConfig.expose) {
            var routeBuilder = config.editOrNewRoute();
            routeBuilder.withExpose(true);
            if (routeConfig.host.isPresent()) {
                routeBuilder.withHost(routeConfig.host.get());
            }

            routeBuilder.withTargetPort(routeConfig.targetPort);
            routeBuilder.endRoute();
        }
    }
}
