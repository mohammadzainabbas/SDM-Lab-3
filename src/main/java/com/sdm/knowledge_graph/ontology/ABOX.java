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
        OntClass author = model.getOntClass( constants.BASE_URI.concat("Author") );
       //===============================
       // Read the csv file 
       //===============================

        try {
            utils.line_separator();
            utils.print("Reading instances data from '" + constants.FILE_PATH + "'");
            
            BufferedReader csvReader = new BufferedReader(new FileReader(constants.FILE_PATH));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(csvReader);
            for(CSVRecord record : parser) {
                // utils.print(record.get("Author"));
                String author_name = record.get("Author");
                author.createIndividual( constants.BASE_URI.concat(author_name) );
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
