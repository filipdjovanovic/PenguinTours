# Baza slike koju treba da kreiramo
FROM node:lts

# Promena radnog foldera
WORKDIR /app

# Kopiranje package.json i package-lock.json
COPY package.json package.json
COPY package-lock.json package-lock.json

# Instaliranje svih zavisnosti (clean install)
RUN npm install

# Kopiranje svih ostalih fajlova
COPY . .

# Otkrivanje porta
EXPOSE 3000

# Pokretanje razvojnog servera
CMD [ "npm", "start" ]
