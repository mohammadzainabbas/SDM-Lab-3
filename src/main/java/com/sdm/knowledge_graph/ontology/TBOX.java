package com.sdm.knowledge_graph.ontology;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;


public class TBOX {

    public static void createTBOX() {

        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModel.html
        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModelSpec.html
        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/rdf/model/ModelFactory.html#createOntologyModel(org.apache.jena.ontology.OntModelSpec)
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM_RDFS_INF );

        OntClass paper = model.createClass( );



    }
}
