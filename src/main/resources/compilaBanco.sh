#!/bin/bash
source ./SBProjeto.prop
mysqldump -u root -psenhaDev#123  $NOME_BANCO > bancoHomologacao.sql
