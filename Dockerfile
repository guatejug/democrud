FROM payara/server-full

COPY target/democrud.war $DEPLOY_DIR
