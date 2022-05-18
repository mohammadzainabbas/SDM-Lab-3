package com.sdm.knowledge_graph.ontology;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

import com.sdm.knowledge_graph.common.constants;
import com.sdm.knowledge_graph.common.utils;

public class TBOX {

    public static void createAndSaveTBOX() {

        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModel.html
        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModelSpec.html
        // https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/rdf/model/ModelFactory.html#createOntologyModel(org.apache.jena.ontology.OntModelSpec)
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM_RDFS_INF );

        //==================================
        // For Persons
        //==================================
        
        OntClass person = model.createClass( constants.BASE_URI.concat("Person") );
        OntClass author = model.createClass( constants.BASE_URI.concat("Author") );
        OntClass chair = model.createClass( constants.BASE_URI.concat("Chair") );
        OntClass editor = model.createClass( constants.BASE_URI.concat("Editor") );
        OntClass reviewer = model.createClass( constants.BASE_URI.concat("Reviewer") );
        
        person.addSubClass( author );
        person.addSubClass( chair );
        person.addSubClass( editor );
        person.addSubClass( reviewer );
        
        //==================================
        // For Papers
        //==================================

        OntClass paper = model.createClass( constants.BASE_URI.concat("Paper") );
        OntClass fullPaper = model.createClass( constants.BASE_URI.concat("Full_Paper") );
        OntClass shortPaper = model.createClass( constants.BASE_URI.concat("Short_Paper") );
        OntClass demoPaper = model.createClass( constants.BASE_URI.concat("Demo_Paper") );
        OntClass posterPaper = model.createClass( constants.BASE_URI.concat("Poster_Paper") );
        
        paper.addSubClass( fullPaper );
        paper.addSubClass( shortPaper );
        paper.addSubClass( demoPaper );
        paper.addSubClass( posterPaper ); // Only for conference papers
        
        //==================================
        // For Venue
        //==================================
        
        OntClass venue = model.createClass( constants.BASE_URI.concat("Venue") );
        OntClass conference = model.createClass( constants.BASE_URI.concat("Conference") );
        OntClass journal = model.createClass( constants.BASE_URI.concat("Journal") );
        
        venue.addSubClass( conference );
        venue.addSubClass( journal );

        //==================================
        // For Venue
        //==================================

        OntClass venue = model.createClass( constants.BASE_URI.concat("Venue") );
        OntClass conference = model.createClass( constants.BASE_URI.concat("Conference") );
        OntClass journal = model.createClass( constants.BASE_URI.concat("Journal") );
        
        venue.addSubClass( conference );
        venue.addSubClass( journal );








        model.write(System.out);

    }
}
