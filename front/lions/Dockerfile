# Stage 1: Build
FROM node:lts AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
RUN ls -la /app # 디버그 명령: /app 디렉토리의 내용을 확인
RUN ls -la /app/dist # 디버그 명령: /app/dist 디렉토리의 내용을 확인

# Stage 2: Serve with nginx
FROM nginx:stable-alpine
RUN rm -rf /etc/nginx/conf.d
COPY config/config.d /etc/nginx/conf.d
COPY --from=build /app/dist /usr/share/nginx/html
