FROM node:16

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
COPY express/package*.json ./
COPY express/server.mjs ./

RUN npm ci --only=production

# Bundle app source
COPY build ./

EXPOSE 80
CMD [ "node", "server.mjs" ]
