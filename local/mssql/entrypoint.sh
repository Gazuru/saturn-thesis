#!/usr/bin/env bash
set -m
  /opt/mssql/bin/sqlservr & bash init_db.sh
fg
