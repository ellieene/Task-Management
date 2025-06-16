#!/bin/bash

DB_NAME="task_db"
DB_USER="task_user"

if [[ $REPLY =~ ^[Yy]$ ]]; then
    dropdb $DB_NAME 2>/dev/null
    dropuser $DB_USER 2>/dev/null
fi 