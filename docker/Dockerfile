FROM cypress/browsers:node-20.14.0-chrome-125.0.6422.141-1-ff-126.0.1-edge-125.0.2535.85-1

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

ENV OUTPUT_DIR=/app/output

CMD ["npm", "run", "cy:run"]