package com.sdm.knowledge_graph.ontology;

import java.io.FileOutputStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import com.sdm.knowledge_graph.common.constants;
import com.sdm.knowledge_graph.common.utils;

public class ABOX {
   
    public static void createAndSaveABOX() {

        Model m = ModelFactory.createDefaultModel().read(constants.MODEL_PATH);
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM_RDFS_INF, m );
        //===================================
        // Getting the class data from TBOX
        //===================================
        
        OntClass author = model.getOntClass( constants.BASE_URI.concat("Author") );
        OntClass chair = model.getOntClass( constants.BASE_URI.concat("Chair"));
        OntClass editor = model.getOntClass( constants.BASE_URI.concat("Editor"));
        OntClass reviewer = model.getOntClass( constants.BASE_URI.concat("Reviewer"));
        OntClass paper = model.getOntClass( constants.BASE_URI.concat("Paper"));


        //===============================
        // Read the csv file 
        //===============================

        try {
            utils.line_separator();
            utils.print("Reading instances data from '" + constants.FILE_PATH + "'");
            
            BufferedReader csvReader = new BufferedReader(new FileReader(constants.FILE_PATH));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(csvReader);
            for(CSVRecord record : parser) {
                String author_name = record.get("Author");
                String document_type = record.get("Document_Type");
                String handler = record.get("Handler");
                if(document_type.equals("Conference"))
                {
                    chair.createIndividual( constants.BASE_URI.concat( handler ) );
                }
                else
                {
                    editor.createIndividual( constants.BASE_URI.concat( handler ) );
                }
                String reviewer1_name = record.get("Reviewer_1");
                String reviewer2_name = record.get("Reviewer_2");
                String paper_name = record.get("Paper");

                author.createIndividual( constants.BASE_URI.concat( author_name ));
                reviewer.createIndividual( constants.BASE_URI.concat( reviewer1_name ));
                reviewer.createIndividual( constants.BASE_URI.concat( reviewer2_name ));
                paper.createIndividual( constants.BASE_URI.concat( paper_name ));
                model.write(System.out);
            }

            utils.print("Done with ABOX creation!");
            utils.line_separator();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
