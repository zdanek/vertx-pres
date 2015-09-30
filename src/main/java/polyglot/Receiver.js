
var eb = vertx.eventBus();

eb.consumer("producer.address", function (message) {

    console.log("JAVASCRIPT: Received a message: " + message.body());
});

console.log("Receiver ready!");
