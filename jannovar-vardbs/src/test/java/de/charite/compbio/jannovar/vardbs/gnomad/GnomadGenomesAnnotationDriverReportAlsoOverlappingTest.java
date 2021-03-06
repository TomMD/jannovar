package de.charite.compbio.jannovar.vardbs.gnomad;

import com.google.common.collect.Lists;
import de.charite.compbio.jannovar.vardbs.base.JannovarVarDBException;
import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFHeader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Test for annotation with gnomAD with default options
 *
 * @author <a href="mailto:manuel.holtgrewe@bihealth.de">Manuel Holtgrewe</a>
 */
public class GnomadGenomesAnnotationDriverReportAlsoOverlappingTest extends GnomadGenomesAnnotationDriverBaseTest {

	@Before
	public void setUpClass() throws Exception {
		super.setUpClass();
		options.setReportOverlapping(true);
		options.setReportOverlappingAsMatching(false);
	}

	@Test
	public void testAnnotateExtendHeaderWithDefaultPrefix() throws JannovarVarDBException {
		options.setIdentifierPrefix("GNOMAD_");
		GnomadAnnotationDriver driver = new GnomadAnnotationDriver(gnomadVCFPath, fastaPath, options);

		VCFHeader header = vcfReader.getFileHeader();

		// Check header before extension
		Assert.assertEquals(0, header.getFilterLines().size());
		Assert.assertEquals(0, header.getInfoHeaderLines().size());
		Assert.assertEquals(0, header.getFormatHeaderLines().size());
		Assert.assertEquals(0, header.getIDHeaderLines().size());
		Assert.assertEquals(0, header.getOtherHeaderLines().size());

		driver.constructVCFHeaderExtender().addHeaders(header);

		// Check header after extension
		Assert.assertEquals(0, header.getFilterLines().size());
		Assert.assertEquals(122, header.getInfoHeaderLines().size());
		Assert.assertEquals(0, header.getFormatHeaderLines().size());
		Assert.assertEquals(122, header.getIDHeaderLines().size());
		Assert.assertEquals(0, header.getOtherHeaderLines().size());

		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_ALL"));

		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HET_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HOM_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_HEMI_ALL"));

		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AC_POPMAX"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AF_POPMAX"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_AN_POPMAX"));

		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_ALL"));

		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_AFR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_AMR"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_EAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_FIN"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_NFE"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_OTH"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_SAS"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HOM_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HEMI_ALL"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_HET_ALL"));

		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AC_POPMAX"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AF_POPMAX"));
		Assert.assertNotNull(header.getInfoHeaderLine("GNOMAD_OVL_AN_POPMAX"));
	}

	@Test
	public void testAnnotateVariantContext() throws JannovarVarDBException {
		GnomadAnnotationDriver driver = new GnomadAnnotationDriver(gnomadVCFPath, fastaPath, options);
		VariantContext vc = vcfReader.iterator().next();

		Assert.assertEquals(0, vc.getAttributes().size());
		Assert.assertEquals(".", vc.getID());

		VariantContext annotated = driver.annotateVariantContext(vc);

		Assert.assertEquals(".", annotated.getID());

		Assert.assertEquals(117, annotated.getAttributes().size());
		ArrayList<String> keys = Lists.newArrayList(annotated.getAttributes().keySet());
		Collections.sort(keys);
		Assert.assertEquals(
			"[AC_AFR, AC_ALL, AC_AMR, AC_ASJ, AC_EAS, AC_FIN, AC_NFE, AC_OTH, AC_POPMAX, AC_SAS, "
				+ "AF_AFR, AF_ALL, AF_AMR, AF_ASJ, AF_EAS, AF_FIN, AF_NFE, AF_OTH, AF_POPMAX, AF_SAS, "
				+ "AN_AFR, AN_ALL, AN_AMR, AN_ASJ, AN_EAS, AN_FIN, AN_NFE, AN_OTH, AN_POPMAX, AN_SAS, "
				+ "HEMI_AFR, HEMI_ALL, HEMI_AMR, HEMI_ASJ, HEMI_EAS, HEMI_FIN, HEMI_NFE, HEMI_OTH, "
				+ "HEMI_POPMAX, HEMI_SAS, HET_AFR, HET_ALL, HET_AMR, HET_ASJ, HET_EAS, HET_FIN, HET_NFE, "
				+ "HET_OTH, HET_POPMAX, HET_SAS, HOM_AFR, HOM_ALL, HOM_AMR, HOM_ASJ, HOM_EAS, HOM_FIN, "
				+ "HOM_NFE, HOM_OTH, HOM_POPMAX, HOM_SAS, OVL_AC_AFR, OVL_AC_ALL, OVL_AC_AMR, OVL_AC_ASJ, "
				+ "OVL_AC_EAS, OVL_AC_FIN, OVL_AC_NFE, OVL_AC_OTH, OVL_AC_POPMAX, OVL_AF_AFR, OVL_AF_ALL, "
				+ "OVL_AF_AMR, OVL_AF_ASJ, OVL_AF_EAS, OVL_AF_FIN, OVL_AF_NFE, OVL_AF_OTH, OVL_AF_POPMAX, "
				+ "OVL_AN_AFR, OVL_AN_ALL, OVL_AN_AMR, OVL_AN_ASJ, OVL_AN_EAS, OVL_AN_FIN, OVL_AN_NFE, "
				+ "OVL_AN_OTH, OVL_AN_POPMAX, OVL_AN_SAS, OVL_HEMI_AFR, OVL_HEMI_ALL, OVL_HEMI_AMR, "
				+ "OVL_HEMI_ASJ, OVL_HEMI_EAS, OVL_HEMI_FIN, OVL_HEMI_NFE, OVL_HEMI_OTH, OVL_HEMI_POPMAX, "
				+ "OVL_HET_AFR, OVL_HET_ALL, OVL_HET_AMR, OVL_HET_ASJ, OVL_HET_EAS, OVL_HET_FIN, OVL_HET_NFE, "
				+ "OVL_HET_OTH, OVL_HET_POPMAX, OVL_HOM_AFR, OVL_HOM_ALL, OVL_HOM_AMR, OVL_HOM_ASJ, OVL_HOM_EAS, "
				+ "OVL_HOM_FIN, OVL_HOM_NFE, OVL_HOM_OTH, OVL_HOM_POPMAX, OVL_POPMAX, POPMAX]",
			keys.toString());

		Assert.assertEquals("[0, 210, 11]", annotated.getAttributeAsString("AC_AFR", null));
		Assert.assertEquals("[0, 303, 11]", annotated.getAttributeAsString("AC_ALL", null));
		Assert.assertEquals("[0, 1, 0]", annotated.getAttributeAsString("AC_AMR", null));
		Assert.assertEquals("[0, 1, 0]", annotated.getAttributeAsString("AC_ASJ", null));
		Assert.assertEquals("[0, 39, 0]", annotated.getAttributeAsString("AC_EAS", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("AC_FIN", null));
		Assert.assertEquals("[0, 46, 0]", annotated.getAttributeAsString("AC_NFE", null));
		Assert.assertEquals("[0, 6, 0]", annotated.getAttributeAsString("AC_OTH", null));
		Assert.assertEquals("[0, 39, 11]", annotated.getAttributeAsString("AC_POPMAX", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("AC_SAS", null));

		Assert.assertEquals("[0.0, 0.3488372093023256, 0.018272425249169437]",
			annotated.getAttributeAsString("AF_AFR", null));
		Assert.assertEquals("[0.0, 0.04265202702702703, 0.0015484234234234234]",
			annotated.getAttributeAsString("AF_ALL", null));
		Assert.assertEquals("[0.0, 0.013888888888888888, 0.0]", annotated.getAttributeAsString("AF_AMR", null));
		Assert.assertEquals("[0.0, 0.027777777777777776, 0.0]", annotated.getAttributeAsString("AF_ASJ", null));
		Assert.assertEquals("[0.0, 0.5571428571428572, 0.0]", annotated.getAttributeAsString("AF_EAS", null));
		Assert.assertEquals("[0.0, 0.0, 0.0]", annotated.getAttributeAsString("AF_FIN", null));
		Assert.assertEquals("[0.0, 0.02346938775510204, 0.0]", annotated.getAttributeAsString("AF_NFE", null));
		Assert.assertEquals("[0.0, 0.061224489795918366, 0.0]", annotated.getAttributeAsString("AF_OTH", null));
		Assert.assertEquals("[0.0, 0.5571428571428572, 0.018272425249169437]",
			annotated.getAttributeAsString("AF_POPMAX", null));
		Assert.assertEquals("[0.0, 0.0, 0.0]", annotated.getAttributeAsString("AF_SAS", null));

		Assert.assertEquals("[602]", annotated.getAttributeAsString("AN_AFR", null));
		Assert.assertEquals("[7104]", annotated.getAttributeAsString("AN_ALL", null));
		Assert.assertEquals("[72]", annotated.getAttributeAsString("AN_AMR", null));
		Assert.assertEquals("[36]", annotated.getAttributeAsString("AN_ASJ", null));
		Assert.assertEquals("[70]", annotated.getAttributeAsString("AN_EAS", null));
		Assert.assertEquals("[714]", annotated.getAttributeAsString("AN_FIN", null));
		Assert.assertEquals("[1960]", annotated.getAttributeAsString("AN_NFE", null));
		Assert.assertEquals("[98]", annotated.getAttributeAsString("AN_OTH", null));
		Assert.assertEquals("[70, 602]", annotated.getAttributeAsString("AN_POPMAX", null));
		Assert.assertEquals("[0]", annotated.getAttributeAsString("AN_SAS", null));

		Assert.assertEquals("[0, 206, 11]", annotated.getAttributeAsString("HET_AFR", null));
		Assert.assertEquals("[0, 289, 11]", annotated.getAttributeAsString("HET_ALL", null));
		Assert.assertEquals("[0, 1, 0]", annotated.getAttributeAsString("HET_AMR", null));
		Assert.assertEquals("[0, 1, 0]", annotated.getAttributeAsString("HET_ASJ", null));
		Assert.assertEquals("[0, 29, 0]", annotated.getAttributeAsString("HET_EAS", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HET_FIN", null));
		Assert.assertEquals("[0, 46, 0]", annotated.getAttributeAsString("HET_NFE", null));
		Assert.assertEquals("[0, 6, 0]", annotated.getAttributeAsString("HET_OTH", null));
		Assert.assertEquals("[0, 39, 11]", annotated.getAttributeAsString("HET_POPMAX", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HET_SAS", null));

		Assert.assertEquals("[0, 2, 0]", annotated.getAttributeAsString("HOM_AFR", null));
		Assert.assertEquals("[0, 7, 0]", annotated.getAttributeAsString("HOM_ALL", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_AMR", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_ASJ", null));
		Assert.assertEquals("[0, 5, 0]", annotated.getAttributeAsString("HOM_EAS", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_FIN", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_NFE", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_OTH", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_POPMAX", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("HOM_SAS", null));

		Assert.assertEquals("[., EAS, AFR]", annotated.getAttributeAsString("POPMAX", null));

		Assert.assertEquals("[210, 210, 210]", annotated.getAttributeAsString("OVL_AC_AFR", null));
		Assert.assertEquals("[303, 303, 303]", annotated.getAttributeAsString("OVL_AC_ALL", null));
		Assert.assertEquals("[1, 1, 1]", annotated.getAttributeAsString("OVL_AC_AMR", null));
		Assert.assertEquals("[1, 1, 1]", annotated.getAttributeAsString("OVL_AC_ASJ", null));
		Assert.assertEquals("[39, 39, 39]", annotated.getAttributeAsString("OVL_AC_EAS", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_AC_FIN", null));
		Assert.assertEquals("[46, 46, 46]", annotated.getAttributeAsString("OVL_AC_NFE", null));
		Assert.assertEquals("[6, 6, 6]", annotated.getAttributeAsString("OVL_AC_OTH", null));
		Assert.assertEquals("[39, 39, 39]", annotated.getAttributeAsString("OVL_AC_POPMAX", null));
		Assert.assertNull(annotated.getAttributeAsString("OVL_AC_SAS", null));

		Assert.assertEquals("[0.3488372093023256, 0.3488372093023256, 0.3488372093023256]",
			annotated.getAttributeAsString("OVL_AF_AFR", null));
		Assert.assertEquals("[0.04265202702702703, 0.04265202702702703, 0.04265202702702703]",
			annotated.getAttributeAsString("OVL_AF_ALL", null));
		Assert.assertEquals("[0.013888888888888888, 0.013888888888888888, 0.013888888888888888]",
			annotated.getAttributeAsString("OVL_AF_AMR", null));
		Assert.assertEquals("[0.027777777777777776, 0.027777777777777776, 0.027777777777777776]",
			annotated.getAttributeAsString("OVL_AF_ASJ", null));
		Assert.assertEquals("[0.5571428571428572, 0.5571428571428572, 0.5571428571428572]",
			annotated.getAttributeAsString("OVL_AF_EAS", null));
		Assert.assertEquals("[0.0, 0.0, 0.0]", annotated.getAttributeAsString("OVL_AF_FIN", null));
		Assert.assertEquals("[0.02346938775510204, 0.02346938775510204, 0.02346938775510204]",
			annotated.getAttributeAsString("OVL_AF_NFE", null));
		Assert.assertEquals("[0.061224489795918366, 0.061224489795918366, 0.061224489795918366]",
			annotated.getAttributeAsString("OVL_AF_OTH", null));
		Assert.assertEquals("[0.5571428571428572, 0.5571428571428572, 0.5571428571428572]",
			annotated.getAttributeAsString("OVL_AF_POPMAX", null));
		Assert.assertNull(annotated.getAttributeAsString("OVL_AF_SAS", null));

		Assert.assertEquals("[602]", annotated.getAttributeAsString("OVL_AN_AFR", null));
		Assert.assertEquals("[7104]", annotated.getAttributeAsString("OVL_AN_ALL", null));
		Assert.assertEquals("[72]", annotated.getAttributeAsString("OVL_AN_AMR", null));
		Assert.assertEquals("[36]", annotated.getAttributeAsString("OVL_AN_ASJ", null));
		Assert.assertEquals("[70]", annotated.getAttributeAsString("OVL_AN_EAS", null));
		Assert.assertEquals("[714]", annotated.getAttributeAsString("OVL_AN_FIN", null));
		Assert.assertEquals("[1960]", annotated.getAttributeAsString("OVL_AN_NFE", null));
		Assert.assertEquals("[98]", annotated.getAttributeAsString("OVL_AN_OTH", null));
		Assert.assertEquals("[70, 602]", annotated.getAttributeAsString("OVL_AN_POPMAX", null));
		Assert.assertEquals("[0]", annotated.getAttributeAsString("OVL_AN_SAS", null));

		Assert.assertEquals("[206, 206, 206]", annotated.getAttributeAsString("OVL_HET_AFR", null));
		Assert.assertEquals("[289, 289, 289]", annotated.getAttributeAsString("OVL_HET_ALL", null));
		Assert.assertEquals("[1, 1, 1]", annotated.getAttributeAsString("OVL_HET_AMR", null));
		Assert.assertEquals("[1, 1, 1]", annotated.getAttributeAsString("OVL_HET_ASJ", null));
		Assert.assertEquals("[29, 29, 29]", annotated.getAttributeAsString("OVL_HET_EAS", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HET_FIN", null));
		Assert.assertEquals("[46, 46, 46]", annotated.getAttributeAsString("OVL_HET_NFE", null));
		Assert.assertEquals("[6, 6, 6]", annotated.getAttributeAsString("OVL_HET_OTH", null));
		Assert.assertEquals("[39, 39, 39]", annotated.getAttributeAsString("OVL_HET_POPMAX", null));
		Assert.assertNull(annotated.getAttributeAsString("OVL_HET_SAS", null));

		Assert.assertEquals("[2, 2, 2]", annotated.getAttributeAsString("OVL_HOM_AFR", null));
		Assert.assertEquals("[7, 7, 7]", annotated.getAttributeAsString("OVL_HOM_ALL", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HOM_AMR", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HOM_ASJ", null));
		Assert.assertEquals("[5, 5, 5]", annotated.getAttributeAsString("OVL_HOM_EAS", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HOM_FIN", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HOM_NFE", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HOM_OTH", null));
		Assert.assertEquals("[0, 0, 0]", annotated.getAttributeAsString("OVL_HOM_POPMAX", null));
		Assert.assertNull(annotated.getAttributeAsString("OVL_HOM_SAS", null));

		Assert.assertEquals("[EAS, EAS, EAS]", annotated.getAttributeAsString("OVL_POPMAX", null));

	}

}
