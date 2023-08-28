# python
In this branch we will make use of Spark Structured Streaming and Python to read the data from a Kafka topic and apply some transformations on it.

## Components

1. In the folder `producer` there is a Kafka producer which reads the data from this [API](https://digitransit.fi/en/developers/apis/4-realtime-api/vehicle-positions/high-frequency-positioning/) and inserts them inside a Kafka topic called `vehicle-positions`

2.  In the folder `docker` there is the docker compose file (`kafka-docker-compose.yml`) which contains all the containers we will make use of. In particular:
    - `zookeeper`, `broker` and  `control-center` are related to the Kafka infrastructure.
    - `init-kafka` is a container used to create the topic `vehicle-positions` in the cluster
    - `kafka-producer` is the container of the producer discussed in the previous step.
    - `jupiter-spark` is a container which contains both `Jupyter` and `PySpark` version 3.4.1.

3. In the folder `notebook` there is the notebook `spark_streaming.ipynb` which we will use in Jupiter to execute our tasks with Spark Structured Streaming.

## Execution

1. We need to create the docker image of the producer. To do that we need to run:
   `docker-compose  -f docker/kafka-docker-compose.yml build`
   
3. Execute the docker-compose file to start all the containers:
   `docker-compose  -f docker/kafka-docker-compose.yml up -d`

4. The producer should start to write in the topic `vehicle-positions` right away. After a while we can stop it if we don't want too much data. However to make sure that the producer is writing in the topic we can execute:
    `docker exec -it broker kafka-console-consumer --bootstrap-server broker:29092 --from-beginning --topic vehicle-positions --property print.key=true`

5. If we get the logs of the container `jupiter-pyspark` we should see a URL where the Jupiter server is listening, like `http://127.0.0.1:8888/lab?token=634bce1cd959e890b8c4c892386da72898027abc9c480751` . If we paste this URL in the browser we shold be able to connect to the Jupiter server and we can upload the notebook `spark_streaming.ipynb` and use it.

## SparkUI
SparkUI at http://localhost:4040/
