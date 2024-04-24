FROM mcr.microsoft.com/mssql/server:2022-latest
ENV ACCEPT_EULA Y
ENV MSSQL_SA_PASSWORD saturn.PW
COPY init_db.sh init_db.sh
COPY entrypoint.sh entrypoint.sh
HEALTHCHECK --interval=10s --timeout=3s --start-period=10s --retries=10 \
    CMD /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P saturn.PW -d master -Q "SELECT 1" -b -o /dev/null
CMD /bin/bash ./entrypoint.sh