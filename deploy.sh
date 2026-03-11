./mvnw package

echo 'Copying files...'
scp target/demo-0.0.1-SNAPSHOT.jar root@192.168.122.121:/SID-superset-itap-dossier

echo 'Restarting server...'
ssh ssh root@192.168.122.121 << EOF

    pkill -9 java
    nohup java -jar /var/demo-0.0.1-SNAPSHOT.jar  &
EOF

echo 'Bye'