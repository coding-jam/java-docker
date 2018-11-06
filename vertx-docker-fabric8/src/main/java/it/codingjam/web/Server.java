package it.codingjam.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;

public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        getVertx().deployVerticle(new WebVerticle(), result -> {
            if (result.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(result.cause());
            }
        });
    }

    public static void main(String[] args) {
        Launcher.executeCommand("run", Server.class.getName());
    }

}