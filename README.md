## SDM - Lab 3 @ UPC 👨🏻‍💻

</br>

<div>
  <a href="https://open.vscode.dev/mohammadzainabbas/SDM-Lab-3" target="_blank" style="cursor: pointer;"> 
    <img src="https://open.vscode.dev/badges/open-in-vscode.svg" style="cursor: pointer;"/>
  </a>
</div>

</br>

### Table of contents

- [Introduction](#introduction)
  * [GraphDB](#graph-db)
  * [Apache Jena](#apache-jena)
  * [Ontology](#ontology)
    * [TBOX](#tbox)
    * [ABOX](#abox)
- [Dataset](#dataset)
- [Preprocess](#preprocess)

---

<a id="introduction" />

#### 1. Introduction

__`Data drives the world.`__ 


<a id="graph-db" />

##### 1.1. GraphDB

GraphDB is a graph database compliant with RDF and SPARQL specifications. It supports open APIs based on RDF4J (ex-Sesame) project and enables fast publishing of linked data on the web. The Workbench is used for searching, exploring and managing GraphDB semantic repositories.

You can find the documentation [here](https://graphdb.ontotext.com/documentation/free/)


<a id="apache-jena" />

##### 1.2. Apache Jena

Apache Jena is an open source Java framework for building Semantic Web and Linked Data applications. It is quite powerful as it can be used to create ontologies, query and add constraints (via SHACL) in semantic web world.

For the purpose of this lab, we have used Apache Jena API to create TBOX and ABOX (and their links) for our publications' data. 

You can find the documentation for Apache Jena API [here](https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/package-summary.html)


<a id="ontology" />

##### 1.3. Ontology

We have mentioned `TBOX` and `ABOX` but what are they ?

<a id="tbox" />

###### 1.3.1 TBOX

`TBOX` can be think as the meta-data for our knowledge graph (or semantic web data/linked data). It tells you what are the atomic concepts (Classes) are there and how they are linked to each other (Properties)

<a id="abox" />

###### 1.3.2 ABOX

`ABOX` is the data instance layer. You create instance via triplets (`subject` `predicate` `object`) format. You basically tells which instance of data belongs to which atomic concept. And how it is linked to another instance of data.

---

<a id="dataset" />

#### 2. Dataset

We used `BYU Engineering Publications in Scopus 2017-21` publications' dataset available on Kaggle. You can find it [here](https://www.kaggle.com/datasets/dpixton/byu-engineering-publications-in-scopus-201721)

> Note: We renamed the file to `publications.csv` for ease of use.

---

<a id="preprocess" />

#### 3. Preprocess

In order to create correct topology (TBOX and ABOX), you may need to pre-process your data first. We wrote a python script which you can use to get the preprocessed data. Just run the following to get the `instances_data.csv` file.

```bash
git clone https://github.com/mohammadzainabbas/SDM-Lab-3.git
cd SDM-Lab-3/
python scripts/preprocess_publication_data.py
```

