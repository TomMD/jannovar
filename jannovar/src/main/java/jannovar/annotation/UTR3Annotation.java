package jannovar.annotation;

import jannovar.reference.TranscriptModel;
import jannovar.reference.Translator;


/**
 * This class is intended to provide a static method to generate annotations for UTR3
 * mutations. This method is put in its own class only for convenience and to at least
 * have a name that is easy to find.
 * <P> The current version of this class basically does the same thing that Annovar does,
 * that is, the annotation is simply the gene symbol. TODO: Create HGVS conformant 3UTR 
 * annotations.
 * @version 0.05 (April 28, 2013)
 * @author Peter N Robinson
 */

public class UTR3Annotation {
    /** Number of nucleotides away from exon/intron boundary to be considered as potential splicing mutation. */
    public final static int SPLICING_THRESHOLD=2;


    /**
     * Return an annotation for a UTR3  mutation for a gene. TODO figure out +/- strand strategy
     * <P> See the description of this class for comments on the TODO. For now, KISS
     * @param trmdl Gene with splice mutation for current chromosomal variant.
     * @param start start position of the variant
     * @param  end position of the variant
     * @param ref reference sequence
     * @param alt variant sequence
     * @return An {@link jannovar.annotation.Annotation Annotation} object corresponding to the UTR3 mutation.
     */
    public static Annotation getUTR3Annotation(TranscriptModel trmdl, int start, int end, String ref, String alt) {
	String genesymbol = trmdl.getGeneSymbol();
	Annotation ann = Annotation.createUTR3Annotation(trmdl, genesymbol);
	return ann;
    }

}