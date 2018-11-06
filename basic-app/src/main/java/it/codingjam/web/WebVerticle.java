package it.codingjam.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class WebVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(WebVerticle.class);
    private static final int HTTP_PORT = 9000;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        final Router router = Router.router(getVertx());
        router.route(HttpMethod.GET, "/").handler(StaticHandler.create("static"));

        logger.info("Try to start WebServer on port: {}", HTTP_PORT);
        getVertx().createHttpServer()
                .requestHandler(router::accept)
                .listen(HTTP_PORT, result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });

        logger.info("Successful start WebServer on port: {}", HTTP_PORT);

    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        super.stop(stopFuture);
    }
}
