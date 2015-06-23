require 'vertx/vertx'
vertx = Vertx::Vertx.vertx()

vertx.set_periodic(2000) { |id|
  # This handler will get called every second
  puts "timer fired!"
    eventBus.publish("producer.address", "I was written in Ruby!")
}
