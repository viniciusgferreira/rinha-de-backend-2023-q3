sudo docker-compose down
sudo docker rm rinha-db
sudo docker volume rm java_pg_data
mvn clean package
sudo docker-compose up -d --build
