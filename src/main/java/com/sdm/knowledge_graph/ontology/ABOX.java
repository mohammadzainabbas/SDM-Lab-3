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
        
        try {
            //===============================================
            // Reading & building Ontology model from TBOX
            //===============================================

            utils.log("Reading TBOX from '" + constants.MODEL_PATH + "'");
            
            Model m = ModelFactory.createDefaultModel().read(constants.MODEL_PATH);
            OntModel model = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM_RDFS_INF, m );
            
            utils.log("Ontology Model built from TBOX");
            
            //===============================================
            // Getting all OntClasses (concepts) from TBOX
            //===============================================

            utils.log("Getting all Ontology Classes (concepts) from TBOX");
            
            OntClass person = model.getOntClass( constants.BASE_URI.concat("Person") );
            OntClass author = model.getOntClass( constants.BASE_URI.concat("Author") );
            OntClass chair = model.getOntClass( constants.BASE_URI.concat("Chair"));
            OntClass editor = model.getOntClass( constants.BASE_URI.concat("Editor"));
            OntClass reviewer = model.getOntClass( constants.BASE_URI.concat("Reviewer"));
            OntClass paper = model.getOntClass( constants.BASE_URI.concat("Paper"));
            OntClass fullPaper = model.getOntClass( constants.BASE_URI.concat("Full_Paper") );
            OntClass shortPaper = model.getOntClass( constants.BASE_URI.concat("Short_Paper") );
            OntClass demoPaper = model.getOntClass( constants.BASE_URI.concat("Demo_Paper") );
            OntClass posterPaper = model.getOntClass( constants.BASE_URI.concat("Poster_Paper") );
            OntClass year = model.getOntClass( constants.BASE_URI.concat("Year"));
            OntClass venue = model.getOntClass( constants.BASE_URI.concat("Venue") );
            OntClass conference = model.getOntClass( constants.BASE_URI.concat("Conference") );
            OntClass journal = model.getOntClass( constants.BASE_URI.concat("Journal") );
            OntClass workshop = model.getOntClass( constants.BASE_URI.concat("Workshop") );
            OntClass symposium = model.getOntClass( constants.BASE_URI.concat("Symposium") );
            OntClass expertGroup = model.getOntClass( constants.BASE_URI.concat("Expert_Group") );
            OntClass regularConference = model.getOntClass( constants.BASE_URI.concat("Regular_Conference") );
            OntClass decision = model.getOntClass( constants.BASE_URI.concat("Decision") );
            OntClass acceptOrRejected = model.getOntClass( constants.BASE_URI.concat("Accepted_Or_Rejected") );
            OntClass reveiwtext = model.getOntClass( constants.BASE_URI.concat("Review_Text") );
            OntClass areas = model.getOntClass( constants.BASE_URI.concat("Areas") );
            OntClass publications = model.getOntClass( constants.BASE_URI.concat("Publications") );
            OntClass conferenceProceedings = model.getOntClass( constants.BASE_URI.concat("Conference_Proceedings") );
            OntClass journalVolume = model.getOntClass( constants.BASE_URI.concat("Journal_Volume") );
            
            //===============================================
            // Getting all Ontology Properties from TBOX
            //===============================================
            
            utils.log("Getting all Ontology Properties from TBOX");

            OntProperty submit = model.getOntProperty( constants.BASE_URI.concat("submit") );
            OntProperty submittedToVenue = model.getOntProperty( constants.BASE_URI.concat("submitted_to") );
            OntProperty handlesConferences = model.getOntProperty( constants.BASE_URI.concat("handles_conference") );
            OntProperty handlesJournals = model.getOntProperty( constants.BASE_URI.concat("handles_journal") );
            OntProperty assignedByChairs = model.getOntProperty( constants.BASE_URI.concat("assigned_by_chairs") );
            OntProperty assignedByEditors = model.getOntProperty( constants.BASE_URI.concat("assigned_by_editors") );
            OntProperty assignedTo = model.getOntProperty( constants.BASE_URI.concat("assigned_to") );
            OntProperty takesDecision = model.getOntProperty( constants.BASE_URI.concat("takes_decision") );
            OntProperty reviewIsGiven = model.getOntProperty( constants.BASE_URI.concat("is_paper_accepted") );
            OntProperty hasReviweComments = model.getOntProperty( constants.BASE_URI.concat("has_review_comments") );
            OntProperty hasArea = model.getOntProperty( constants.BASE_URI.concat("has_area") );
            // OntProperty conferenceHasAreas = model.getOntProperty( constants.BASE_URI.concat("conference_has_areas") );
            // OntProperty journalHasAreas = model.getOntProperty( constants.BASE_URI.concat("journal_has_areas") );
            OntProperty paperHasPublication = model.getOntProperty( constants.BASE_URI.concat("has_publication") );
            // OntProperty publicationHasConfProceedings = model.getOntProperty( constants.BASE_URI.concat("publication_has_conference_proceeding") );
            // OntProperty publicationHasJourVolume = model.getOntProperty( constants.BASE_URI.concat("publication_has_journal_volume") );
            OntProperty paperYear = model.getOntProperty( constants.BASE_URI.concat("published_year") );
                
            utils.line_separator();
            utils.print("Reading instances data from '" + constants.FILE_PATH + "'");
            
            //===============================================
            // Read & Parse the csv file 
            //===============================================

            BufferedReader csvReader = new BufferedReader(new FileReader(constants.FILE_PATH));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(csvReader);
            for(CSVRecord record : parser) {
                String author_name = utils.str_clean( record.get("Author") );
                author.createIndividual( constants.BASE_URI.concat( author_name ));

                String document_type = utils.str_clean( record.get("Document_Type") );
                String handler = utils.str_clean( record.get("Handler") );
                if(document_type.equals("Conference"))
                {
                    chair.createIndividual( constants.BASE_URI.concat( handler ) );
                }
                else
                {
                    editor.createIndividual( constants.BASE_URI.concat( handler ) );
                }

                String reviewer1_name = utils.str_clean( record.get("Reviewer_1") );
                String reviewer2_name = utils.str_clean( record.get("Reviewer_2") );
                reviewer.createIndividual( constants.BASE_URI.concat( reviewer1_name ));
                reviewer.createIndividual( constants.BASE_URI.concat( reviewer2_name ));

                String paper_name = utils.str_clean( record.get("Paper") );
                paper.createIndividual( constants.BASE_URI.concat( paper_name ));

                String paper_year = utils.str_clean( record.get("Year") ); //nullpointer error
                year.createIndividual( constants.BASE_URI.concat( paper_year ));

                String paper_type = utils.str_clean( record.get("Paper_Type") );
                if(paper_type.equals("Full_Paper"))
                {
                    fullPaper.createIndividual( constants.BASE_URI.concat( paper_type ));
                }
                else if(paper_type.equals("Short_Paper"))
                {
                    shortPaper.createIndividual( constants.BASE_URI.concat( paper_type ));
                }
                else if(paper_type.equals("Demo_Paper"))
                {
                    demoPaper.createIndividual( constants.BASE_URI.concat( paper_type ));
                }
                else
                {
                    posterPaper.createIndividual( constants.BASE_URI.concat( paper_type ));
                }

                String conference_type = utils.str_clean( record.get("Conference_Type") );
                if(conference_type.equals("Workshop"))
                {
                    workshop.createIndividual( constants.BASE_URI.concat( conference_type));
                }
                else if(conference_type.equals("Symposium"))
                {
                    symposium.createIndividual( constants.BASE_URI.concat( conference_type));
                }
                else if(conference_type.equals("Expert_Group"))
                {
                    expertGroup.createIndividual( constants.BASE_URI.concat( conference_type));
                }
                else
                {
                    regularConference.createIndividual( constants.BASE_URI.concat( conference_type));
                }

                String source_type = utils.str_clean( record.get("Source") );
                if(source_type.equals("Conference"))
                {
                    conference.createIndividual( constants.BASE_URI.concat(source_type));
                }
                else
                {
                    journal.createIndividual( constants.BASE_URI.concat(source_type));
                }

                String publications = utils.str_clean( record.get("Publication") );
                publication.createIndividual( constants.BASE_URI.concat( publications ));
                
                String area = utils.str_clean( record.get("Areas") );
                if(area.equals("Artificial_Intelligence"))
                {
                    artificialIntelligence.createIndividual( constants.BASE_URI.concat( area ));
                }
                else if(area.equals("Machine_Learning"))
                {
                    machineLearning.createIndividual( constants.BASE_URI.concat( area ));
                }
                else if(area.equals("Natural_Language_Processing"))
                {
                    naturalLanguageProcessing.createIndividual( constants.BASE_URI.concat( area ));
                }
                else
                {
                    database.createIndividual( constants.BASE_URI.concat( area ));
                }
                
                String review_decision = utils.str_clean( record.get("Reviewer_Decision") );
                if(review_decision.equals("Accepted"))
                {
                    acceptOrRejected.createIndividual( constants.BASE_URI.concat( review_decision ));
                }  
                else
                {
                    acceptOrRejected.createIndividual( constants.BASE_URI.concat( review_decision ));
                }  
                
                String review_text = utils.str_clean( record.get("Reviewer_Text") );
                reveiwtext.createIndividual( constants.BASE_URI.concat( review_text ));

            }

            utils.line_separator();
            
            FileOutputStream writerStream = new FileOutputStream( constants.DATA_PATH );
            // model.write(System.out, "N-TRIPLE");
            // model.write(writerStream, "RDF/XML");
            // model.write(writerStream, "RDF/XML-ABBREV");
            // model.write(writerStream, "TURTLE");
            model.write(writerStream, "N-TRIPLE");
            writerStream.close();

            // model.write(new FileWriter("some-file.owl"), "TURTLE");
            
            utils.line_separator();

            utils.print("Done with ABOX creation!");
            utils.line_separator();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
