#!/bin/bash
set -e

for i in {1..50};
do
	if /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P saturn.PW -d master -Q "SELECT 1" -b -o /dev/null
	then
		break
	else
		echo "Waiting for the database to startup..."
		sleep 1
	fi
	if [ "$i" == 50 ]
	then
		echo "Initialization failed. Database timeout."
		exit
	fi
done

echo "Initializing databases..."
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P saturn.PW -d master -b -Q <<-EOSQL "
  	CREATE DATABASE saturn_education;
  	CREATE DATABASE saturn_management;
  	CREATE DATABASE saturn_portal;
"
EOSQL
echo "Initializing databases is complete."
echo "Initializing schemas and users..."
for DB_NAME in saturn_education saturn_management saturn_portal
do
	/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P saturn.PW -d "${DB_NAME}" -b -Q <<-EOSQL "
		CREATE LOGIN ${DB_NAME} WITH PASSWORD='saturn.PW';
		CREATE USER ${DB_NAME} FOR LOGIN ${DB_NAME};
		GO
		ALTER ROLE db_ddladmin ADD MEMBER ${DB_NAME};
		ALTER ROLE db_datareader ADD MEMBER ${DB_NAME};
		ALTER ROLE db_datawriter ADD MEMBER ${DB_NAME};
		GO
	"
	EOSQL
done
echo "Initializing schemas and users is complete."