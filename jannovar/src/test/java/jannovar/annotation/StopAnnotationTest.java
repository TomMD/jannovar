package jannovar.annotation;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.HashMap;

/* serialization */
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;


import jannovar.io.UCSCKGParser;
import jannovar.common.Constants;
import jannovar.io.AnnovarParser;
import jannovar.reference.KnownGene;
import jannovar.reference.Chromosome;
import jannovar.annotation.Annotation;
import jannovar.exome.Variant;
import jannovar.exception.AnnotationException;


import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;


/**
 * This class is intended to perform unuit testing on variants that
 * are STOPGAIN orn STOPLOSS. 
 */
public class StopAnnotationTest implements Constants {

    
   
    private static HashMap<Byte,Chromosome> chromosomeMap = null;

  

    @SuppressWarnings (value="unchecked")
    @BeforeClass 
    public static void setUp() throws IOException {
	HashMap<String,KnownGene> kgMap=null;

	try {
	    java.net.URL url = StopAnnotationTest.class.getResource("/ucsc.ser");
	    String path = url.getPath();
	    FileInputStream fileIn = new FileInputStream(path);
	    ObjectInputStream in = new ObjectInputStream(fileIn);
	    kgMap = (HashMap<String,KnownGene>) in.readObject();
            in.close();
            fileIn.close();
	} catch(IOException i) {
            i.printStackTrace();
	    System.err.println("Could not deserialize knownGeneMap");
	    System.exit(1);
           
        } catch(ClassNotFoundException c) {
            System.out.println("Could not find HashMap<String,KnownGene> class.");
            c.printStackTrace();
            System.exit(1);
        }
	//System.out.println("Done deserialization, size of map is " + kgMap.size());
	chromosomeMap = new HashMap<Byte,Chromosome> ();
	for (KnownGene kgl : kgMap.values()) {
	    byte chrom = kgl.getChromosome();
	    if (! chromosomeMap.containsKey(chrom)) {
		Chromosome chr = new Chromosome(chrom);
		chromosomeMap.put(chrom,chr);
	    }
	    Chromosome c = chromosomeMap.get(chrom);
	    c.addGene(kgl);	
	}
    }


/**
 *<P>
 * annovar: FAM71A:uc001hjk.3:exon1:c.1663A>T:p.K555X,
 * chr1:212799882A>T
 *</P>
 */
@Test public void testStopVar1() throws AnnotationException  {
	byte chr = 1;
	int pos = 212799882;
	String ref = "A";
	String alt = "T";
	Chromosome c = chromosomeMap.get(chr); 
	if (c==null) {
	    Assert.fail("Could not identify chromosome \"" + chr + "\"");
	} else {
	    Annotation ann =c.getAnnotation(pos,ref,alt); 
	    VariantType varType = ann.getVariantType();
	    Assert.assertEquals(VariantType.STOPGAIN,varType);
	    String annot = ann.getVariantAnnotation();
	    Assert.assertEquals("FAM71A(uc001hjk.3:exon1:c.1663A>T:p.K555*)",annot);
	}
}



/**
 *<P>
 * annovar: HLA-L:uc003npv.2:exon5:c.431G>A:p.W144X,
 * chr6:30229463G>A
 *</P>
 */
@Test public void testStopVar4() throws AnnotationException  {
	byte chr = 6;
	int pos = 30229463;
	String ref = "G";
	String alt = "A";
	Chromosome c = chromosomeMap.get(chr); 
	if (c==null) {
	    Assert.fail("Could not identify chromosome \"" + chr + "\"");
	} else {
	    Annotation ann =c.getAnnotation(pos,ref,alt); 
	    VariantType varType = ann.getVariantType();
	    Assert.assertEquals(VariantType.STOPGAIN,varType);
	    String annot = ann.getVariantAnnotation();
	    Assert.assertEquals("HLA-L(uc003npv.2:exon5:c.431G>A:p.W144*)",annot);
	}
}


/**
 *<P>
 * annovar: PTCRA:uc011duz.1:exon3:c.348G>A:p.W116X,PTCRA:uc010jxx.1:exon2:c.198G>A:p.W66X,
 * chr6:42891022G>A
 *</P>
 --Seems correct in jannovar, potential bug in annovar, which doesnt get the missese. I asked Kai.
@Test public void testStopVar5() throws AnnotationException  {
	byte chr = 6;
	int pos = 42891022;
	String ref = "G";
	String alt = "A";
	Chromosome c = chromosomeMap.get(chr); 
	if (c==null) {
	    Assert.fail("Could not identify chromosome \"" + chr + "\"");
	} else {
	    Annotation ann =c.getAnnotation(pos,ref,alt); 
	    VariantType varType = ann.getVarType();
	    Assert.assertEquals(VariantType.STOPGAIN,varType);
	    String annot = ann.getVariantAnnotation();
	    Assert.assertEquals("PTCRA(uc010jxx.1:exon2:c.198G>A:p.W66*,uc011duz.1:exon3:c.348G>A:p.W116*)",annot);
	}
}
*/




/**
 *<P>
 * annovar: OR4X1:uc010rht.2:exon1:c.819T>A:p.Y273X,
 * chr11:48286231T>A
 *</P>
 */
@Test public void testStopVar7() throws AnnotationException  {
	byte chr = 11;
	int pos = 48286231;
	String ref = "T";
	String alt = "A";
	Chromosome c = chromosomeMap.get(chr); 
	if (c==null) {
	    Assert.fail("Could not identify chromosome \"" + chr + "\"");
	} else {
	    Annotation ann =c.getAnnotation(pos,ref,alt); 
	    VariantType varType = ann.getVariantType();
	    Assert.assertEquals(VariantType.STOPGAIN,varType);
	    String annot = ann.getVariantAnnotation();
	    Assert.assertEquals("OR4X1(uc010rht.2:exon1:c.819T>A:p.Y273*)",annot);
	}
}


/**
 *<P>
 * annovar: METTL8:uc010zdp.2:exon9:c.1000T>C:p.X334R,METTL8:uc002ugu.4:exon11:c.1135T>C:p.X379R,
 * chr2:172180771A>G
 -- Note this is also a splice mutation, annovar has an additional splice annotation in the variant.function file
 *</P>
 */
@Test public void testStopLossVar1() throws AnnotationException  {
	byte chr = 2;
	int pos = 172180771;
	String ref = "A";
	String alt = "G";
	Chromosome c = chromosomeMap.get(chr); 
	if (c==null) {
	    Assert.fail("Could not identify chromosome \"" + chr + "\"");
	} else {
	    Annotation ann =c.getAnnotation(pos,ref,alt); 
	    VariantType varType = ann.getVariantType();
	    Assert.assertEquals(VariantType.STOPLOSS,varType);
	    String annot = ann.getVariantAnnotation();
	    Assert.assertEquals("METTL8(uc010zdp.2:exon9:c.1000T>C:p.*334R,uc010zdo.2:exon11:c.1134+1T>C,uc002ugu.4:exon11:c.1135T>C:p.*379R)",annot);
	}
}



/**
 *<P>
 * annovar: NPSR1:uc003teh.1:exon10:c.1171T>C:p.X391R,
 * chr7:34889222T>C
-- This is now picking up synonymous exonic variants as well as the stoploss. This is a bug in jannovar,
-- should be fixed soon
 *</P>
 */
@Test public void testStopLossVar2() throws AnnotationException  {
	byte chr = 7;
	int pos = 34889222;
	String ref = "T";
	String alt = "C";
	Chromosome c = chromosomeMap.get(chr); 
	if (c==null) {
	    Assert.fail("Could not identify chromosome \"" + chr + "\"");
	} else {
	    Annotation ann =c.getAnnotation(pos,ref,alt); 
	    VariantType varType = ann.getVariantType();
	    Assert.assertEquals(VariantType.STOPLOSS,varType);
	    String annot = ann.getVariantAnnotation();
	    Assert.assertEquals("NPSR1(uc003teh.1:exon10:c.1171T>C:p.*391R)",annot);
	}
}


}
