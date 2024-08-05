# Stage 1: Build
FROM node:lts AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Stage 2: Serve with nginx
FROM nginx:stable-alpine
RUN rm -rf /etc/nginx/conf.d
COPY config/config.d /etc/nginx/conf.d
COPY --from=build /app/build /usr/share/nginx/html
