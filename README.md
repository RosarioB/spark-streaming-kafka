# scala
In this branch we will make use of Spark Structured Streaming and Scala to read the data from a Kafka topic and apply some transformations on it.

## Components

1. In the folder `producer` there is a Kafka producer which reads the data from this [API](https://digitransit.fi/en/developers/apis/4-realtime-api/vehicle-positions/high-frequency-positioning/) and inserts them inside a Kafka topic called `vehicle position`

2.  In the folder `docker` there is the docker compose file (`kafka-docker-compose.yml`) which contains all the containers we will make use of. In particular:
    - `zookeeper`, `broker` and  `control-center` are related to the Kafka infrastructure.
    - `init-kafka` is a container used to create the topic `vehicle-positions` in the cluster
    - `kafka-producer` is the container of the producer discussed in the previous step.

3. In the folder `spark-streaming` there is the Scala code of our Spark Job. In IntelliJ we need to set Java 11.


## Execution

1. We need to create the docker image of the producer. To do that we need to run:
   `docker-compose  -f docker/kafka-docker-compose.yml build`
   
3. Execute the docker-compose file to start all the containers:
   `docker-compose  -f docker/kafka-docker-compose.yml up -d`

4. The producer should start to write in the topic `vehicle-positions` right away. After a while we can stop it if we don't want too much data. However to make sure that the producer is writing in the topic we can execute:
    `docker exec -it broker kafka-console-consumer --bootstrap-server broker:29092 --from-beginning --topic vehicle-positions --property print.key=true`

5. Start the Spark Application from the IDE or with spark-submit. (I need to test the jar creation by the way)
