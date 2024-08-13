## Build backend
cd backend
./mvnw install
./mvnw spring-boot:build-image -pl books-service,user-service
cd ..

## Build frontend
cd frontend
npm i
npm run build
docker build -t bookloom-frontend:0.0.1 .
cd ..

