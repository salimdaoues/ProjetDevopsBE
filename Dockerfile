##FROM openjdk:8
##EXPOSE 8081
##ADD 'target/tpAchatProject-1.0.jar tpachatPorject-1.0.jar
##ENTRYPOINT ["java","-jar","tpachatPorject-1.0.jar"]

# Utiliser une image Java 11 comme base
FROM openjdk:11-jre-slim

# Créer un répertoire pour l'application
RUN mkdir /app

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'application dans le répertoire de travail
COPY target/tpAchatProject-1.0.jar /app/tpAchatProject-1.0.jar

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8089

# Définir la commande à exécuter pour lancer l'application
CMD ["java", "-jar", "tpAchatProject-1.0.jar"]
