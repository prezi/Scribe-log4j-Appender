Scribe log4j Appender
====================================

Much of this work is based on Alex Loddengaard (http://github.com/alexlod/scribe-log4j-appender).

I cleaned up his code to work in non-hadoop environments. I added support to reconnect if it loses a connection or if scribe goes away.

Deploy on maven
=====

A separate settings file with username and password is required

        <?xml version="1.0" encoding="utf-8"?>
        <settings>
        <servers>
            <server>
            <id>artifactory</id>
            <username><![CDATA[USERNAME]]></username>
            <password><![CDATA[PASSWORD]]></password>
            </server>
        </servers>
        </settings>

mvn -s SECRETS.xml deploy

Example to enable in log4j:

# Add scribe to end of rootLogger:

log4j.rootLogger=DEBUG,stdout,scribe

#
# Add this to your log4j.properties
#
# You can adjust the scribe_host and scribe_port you want messages sent to by setting
# scribe_host and scribe_port
#
# You can also set the hostname if you do not want to rely on Java picking the correct hostname

log4j.appender.scribe=org.apache.log4j.scribe.ScribeAppender
log4j.appender.scribe.scribe_category=MyScribeCategoryName
log4j.appender.scribe.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.scribe.layout=org.apache.log4j.PatternLayout
log4j.appender.scribe.layout.ConversionPattern=%5p [%t] %d{ISO8601} %F (line %L) %m%n

