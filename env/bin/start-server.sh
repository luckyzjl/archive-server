#!/bin/sh
echo ""
echo "start mediator service"

nohup java -jar /home/admin/mediator-server/application/mediation-server-1.0.jar --spring.config.location=/home/admin/mediator-server/application/develop.properties &
