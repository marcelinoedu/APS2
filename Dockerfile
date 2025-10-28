# ---- build stage ----
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copia pom + sources para tirar vantagem do cache do Docker
COPY pom.xml .
# copiar apenas as pastas que contêm código para acelerar cache
COPY src ./src

# Build do jar (ajuste se tiver perfil ou precisar executar testes)
RUN mvn -B -e -DskipTests package

# ---- runtime stage ----
FROM eclipse-temurin:17-jre-jammy

# Usuário não-root (opcional, mas recomendado)
ARG APP_USER=appuser
RUN useradd --create-home --shell /bin/false $APP_USER

WORKDIR /app

# Copia o jar produzido no build stage
COPY --from=build /workspace/target/*.jar app.jar

# Expor porta padrão do Spring Boot
EXPOSE 8080

# Mudar dono do jar para usuário não-root
RUN chown $APP_USER:$APP_USER /app/app.jar

USER $APP_USER

# Variáveis de ambiente sensíveis devem ser passadas em runtime (não hardcode)
ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app/app.jar" ]
