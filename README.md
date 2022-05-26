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

You can find the documentation [here](https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/package-summary.html)

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

