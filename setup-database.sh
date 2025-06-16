#!/bin/bash

if ! command -v psql &> /dev/null; then
    echo "❌ PostgreSQL не установлен."
    exit 1
fi

if ! pg_isready -q; then
    if command -v brew &> /dev/null; then
        brew services start postgresql@14
        sleep 3
    else
        echo "❌ Не удалось запустить PostgreSQL."
        exit 1
    fi
fi

DB_NAME="task_db"
DB_USER="task_user"
DB_PASSWORD="task_password"

psql postgres -c "SELECT 1 FROM pg_roles WHERE rolname='$DB_USER'" | grep -q 1
if [ $? -ne 0 ]; then
    psql postgres -c "CREATE USER $DB_USER WITH PASSWORD '$DB_PASSWORD';"
fi

psql postgres -c "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" | grep -q 1
if [ $? -ne 0 ]; then
    psql postgres -c "CREATE DATABASE $DB_NAME OWNER $DB_USER;"
fi

psql postgres -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;"
psql postgres -c "ALTER USER $DB_USER CREATEDB;"

if ! psql -h localhost -U $DB_USER -d $DB_NAME -c "SELECT 1;" &> /dev/null; then
    echo "Не удалось подключиться к базе данных."
fi