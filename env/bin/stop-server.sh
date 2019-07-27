#!/bin/sh

ps -ef|grep java | grep mediator |grep -v grep|awk '{print $2}' |xargs kill -9
