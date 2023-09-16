sudo docker-compose down
sudo docker rm rinha-db
sudo docker volume rm node_pg_data
sudo docker-compose up -d --build