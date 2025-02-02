FROM cypress/browsers:node22.13.1-chrome91-ff89

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

CMD ["npm", "run", "cy:run"]