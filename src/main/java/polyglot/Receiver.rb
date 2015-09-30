
eb = $vertx.event_bus()

puts "Ruby for the win! I'm consumer"

eb.consumer("producer.address") { |message|
  puts "Received a message: #{message.body()}"
}


