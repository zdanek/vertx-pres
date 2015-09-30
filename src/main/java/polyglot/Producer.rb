
puts "Ruby for the win!"

eb = $vertx.event_bus()

# Send a message every second

$vertx.set_periodic(1000) { |v|
    puts "RUBY: sending Ruby message"
    eb.publish("producer.address", "Ruby shines!")
}

