package polyglot;

vertx.setPeriodic(2000L, { timerId =>
    vertx.eventBus.publish("I'm producing Scala events!")

})
