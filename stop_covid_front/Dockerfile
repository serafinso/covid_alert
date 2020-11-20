FROM node

WORKDIR /app/frontend
COPY package.json /app/frontend
COPY package-lock.json /app/frontend

RUN npm install

COPY . /app/frontend

CMD ["npm", "start"]