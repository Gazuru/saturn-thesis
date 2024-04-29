FROM nginx:stable-alpine3.17
COPY ./dist/ /user/share/nginx/html
COPY ./nginx.conf.template /etc/nginx/templates/default.conf.template

EXPOSE 80
