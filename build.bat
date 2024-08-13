@echo off
setlocal

:: Build backend
cd backend
call mvnw.cmd install
call mvnw.cmd spring-boot:build-image -pl books-service,user-service
cd ..

:: Build frontend
cd frontend
call npm install
call npm run build
call docker build -t bookloom-frontend:0.0.1 .
cd ..

endlocal

