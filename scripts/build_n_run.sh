#! /usr/bin/bash
rm -r target | echo "No target directory found"
mvn compile
mvn exec:java -Dexec.mainClass=com.sdm.knowledge_graph.Main -Dexec.args="'$1'"